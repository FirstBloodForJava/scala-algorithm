#!/bin/bash
# weekly-report helper
# Usage: bash scripts/weekly_report.sh <since-date> <until-date>
set -e

SINCE="$1"
UNTIL="$2"
CHECKLIST="md/算法分类题单.md"
BASE="src/main/java/com/oycm"
TMP="/tmp/weekly_report_$$"
mkdir -p "$TMP"

if [ -z "$SINCE" ] || [ -z "$UNTIL" ]; then
    echo "Usage: $0 <since-date> <until-date>" >&2
    exit 1
fi

# Convert date YYYY-MM-DD to days since epoch (pure bash, no date -d)
date_to_days() {
    local y=${1:0:4} m=${1:5:2} d=${1:8:2}
    # Remove leading zeros
    m=$((10#$m)); d=$((10#$d))
    # Convert month to days (approximate, good enough for relative comparison)
    local days=$(( (y - 1970) * 365 + (y - 1969) / 4 - (y - 1901) / 100 + (y - 1601) / 400 ))
    local mdays=(0 31 59 90 120 151 181 212 243 273 304 334)
    days=$(( days + ${mdays[$((m-1))]} + d - 1 ))
    # Adjust for leap year if after Feb
    if [ $m -gt 2 ]; then
        local leap=0
        if [ $((y % 4)) -eq 0 ] && [ $((y % 100)) -ne 0 ] || [ $((y % 400)) -eq 0 ]; then leap=1; fi
        days=$(( days + leap ))
    fi
    echo $days
}

TODAY=$(date +%Y-%m-%d)
TODAY_DAYS=$(date_to_days "$TODAY")

resolve_pkg() {
    case "$1" in
        dp.*|string.*) echo "$1" ;;
        monotonic.*) echo "datastructure.stack.$1" ;;
        binary_tree.*|heap.*|linked.*|stack.*|tree.*|trie.*|union_find.*) echo "datastructure.$1" ;;
        *) echo "algorithm.$1" ;;
    esac
}

# -----------------------------------------------------------
# Part 1: Parse checklist
# -----------------------------------------------------------
CAT_FILE="$TMP/categories.txt"
> "$CAT_FILE"

parent_section=""
parent_subsection=""

while IFS= read -r line; do
    line="${line%$'\r'}"

    if [[ "$line" =~ ^##[[:space:]]+(.*) ]]; then
        parent_section="${BASH_REMATCH[1]}"
        parent_section="${parent_section%% \`*}"
        parent_subsection=""
        continue
    fi

    if [[ "$line" =~ ^###[[:space:]]+(.*) ]]; then
        parent_subsection="${BASH_REMATCH[1]}"
        parent_subsection="${parent_subsection%% \`*}"
        continue
    fi

    # Only process leaf lines with code spans
    if [[ ! "$line" =~ \`.*\` ]] || [[ ! "$line" == *"- ["* ]]; then
        continue
    fi

    # Skip bold parent lines if they have children (deeper indent after them)
    # But keep bold lines that are leaf nodes (like "分组循环" which is standalone)
    if [[ "$line" =~ \*\*[^*]+\*\*[[:space:]]*\` ]]; then
        # Check if this is a parent: look at next 5 lines for deeper indentation
        # Simple heuristic: if line is bold and NOT deeply indented itself, it MIGHT be a leaf
        # Actually just keep all bold lines that have a learning date (= real leaf)
        if [[ ! "$line" =~ 学习时间 ]]; then
            continue
        fi
    fi

    # Extract code span
    tmp="${line#*\`}"
    pkg_short="${tmp%%\`*}"
    [ -z "$pkg_short" ] && continue

    # Checkbox state
    cb="[ ]"
    [[ "$line" == *"- [x]"* ]] && cb="[x]"

    # Learning date
    learn_date=""
    if [[ "$line" =~ 学习时间[[:space:]]*\`([0-9]{4}-[0-9]{2}-[0-9]{2})\` ]]; then
        learn_date="${BASH_REMATCH[1]}"
    fi

    # Resolve full package
    full_pkg=$(resolve_pkg "$pkg_short")

    # Build display name from line text: strip markdown artifacts
    display="${line#*] }"           # Remove "- [ ] " or "  - [x] " prefix
    display="${display%% \`*}"       # Remove code span and everything after
    display="${display//\*\*/}"      # Strip bold markers

    # Build hierarchy
    hier=""
    [ -n "$parent_section" ] && hier="$parent_section"
    [ -n "$parent_subsection" ] && hier="$hier > $parent_subsection"
    hier="$hier > $display"
    hier="${hier# > }"

    echo "$hier|$full_pkg|$cb|$learn_date" >> "$CAT_FILE"
done < <(tr -d '\r' < "$CHECKLIST")

# -----------------------------------------------------------
# Part 2: Git changes
# -----------------------------------------------------------
GIT_FILE="$TMP/git_files.txt"
> "$GIT_FILE"

# Get changed files in period
git log --since="$SINCE 00:00:00" --until="$UNTIL 23:59:59" \
    --format="" --name-only -- "src/main/java/" 2>/dev/null | \
    grep -E '\.(java|scala)$' | grep -v 'package-info' | sort -u > "$GIT_FILE" || true

# Map each changed file to a category
CHANGED_FILE="$TMP/changed.txt"
> "$CHANGED_FILE"

while IFS= read -r file; do
    [ -z "$file" ] && continue
    fdir=$(dirname "$file")
    echo "$fdir"
done < "$GIT_FILE" | sort -u > "$TMP/changed_dirs.txt"

# Match changed dirs against category packages
while IFS='|' read -r hier full_pkg cb learn_date; do
    pkg_dir="$BASE/$(echo "$full_pkg" | tr '.' '/')"
    while IFS= read -r changed_dir; do
        [ -z "$changed_dir" ] && continue
        if [[ "$changed_dir" == "$pkg_dir"* ]]; then
            echo "CHANGED|$hier|$full_pkg|$learn_date" >> "$CHANGED_FILE"
            break
        fi
    done < "$TMP/changed_dirs.txt"
done < "$CAT_FILE"

sort -u "$CHANGED_FILE" > "${CHANGED_FILE}.tmp" 2>/dev/null && mv "${CHANGED_FILE}.tmp" "$CHANGED_FILE" 2>/dev/null || true

# -----------------------------------------------------------
# Part 3: Statistics
# -----------------------------------------------------------
total=0; mastered=0; learning=0; pending=0
while IFS='|' read -r hier full_pkg cb learn_date; do
    total=$((total + 1))
    if [ "$cb" = "[x]" ]; then mastered=$((mastered + 1))
    elif [ -n "$learn_date" ]; then learning=$((learning + 1))
    else pending=$((pending + 1))
    fi
done < "$CAT_FILE"

# -----------------------------------------------------------
# Part 4: Review matrix
# -----------------------------------------------------------
REVIEW_FILE="$TMP/review.txt"
> "$REVIEW_FILE"

while IFS='|' read -r hier full_pkg cb learn_date; do
    [ "$cb" = "[x]" ] && continue
    [ -z "$learn_date" ] && continue

    days_since=$(( TODAY_DAYS - $(date_to_days "$learn_date") ))

    tier=""
    if [ $days_since -ge 30 ]; then tier="🔴"
    elif [ $days_since -ge 15 ]; then tier="🟡"
    elif [ $days_since -ge 7 ]; then tier="🟢"
    else tier="🔵"
    fi

    echo "$tier|$hier|$full_pkg|$learn_date|$days_since" >> "$REVIEW_FILE"
done < "$CAT_FILE"

# Sort: 🔴 first, then 🟡, then 🟢, then 🔵; within each, oldest first
sort -t'|' -k1,1 -k5,5nr "$REVIEW_FILE" > "${REVIEW_FILE}.sorted" 2>/dev/null
mv "${REVIEW_FILE}.sorted" "$REVIEW_FILE" 2>/dev/null || true

# -----------------------------------------------------------
# Output
# -----------------------------------------------------------
echo "=== PERIOD ==="
echo "PERIOD|$SINCE|$UNTIL"
echo "=== STATISTICS ==="
echo "STATS|$total|$mastered|$learning|$pending"
echo "=== CHANGED ==="
cat "$CHANGED_FILE" 2>/dev/null || echo "CHANGED|(无变更)|-|-"
echo "=== REVIEW ==="
cat "$REVIEW_FILE"

# Cleanup
rm -rf "$TMP"
