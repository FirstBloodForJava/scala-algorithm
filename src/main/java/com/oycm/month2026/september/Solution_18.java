package com.oycm.month2026.september;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_18 {

    /**
     * 1520. <a href="https://leetcode.cn/problems/maximum-number-of-non-overlapping-substrings/description/">最多的不重叠子字符串</a> 2363
     *
     * @param s
     * @return
     */
    public List<String> maxNumOfSubstrings(String s) {
        /*
        给你一个只包含小写字母的字符串 s ，你需要找到 s 中最多数目的非空子字符串，满足如下条件：
            这些字符串之间互不重叠，也就是说对于任意两个子字符串 s[i..j] 和 s[x..y] ，要么 j < x 要么 i > y 。
            如果一个子字符串包含字符 char ，那么 s 中所有 char 字符都应该在这个子字符串中。
        请你找到满足上述条件的最多子字符串数目。如果有多个解法有相同的子字符串数目，请返回这些子字符串总长度最小的一个解。可以证明最小总长度解是唯一的。
        请注意，你可以以 任意 顺序返回最优解的子字符串。
         */
        /*
        题解：
        主要要求的第二点，选择第 i 给字母，第 i 个字母所在区间 [l, r] 中包含的其它字母，需要玩外扩。
        构建有向图 + 区间合并（选择某个字母的最终区间）
         */
        // 记录每种字母出现位置
        int n = s.length();
        List<Integer>[] pos = new ArrayList[26];
        Arrays.setAll(pos, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            pos[s.charAt(i) - 'a'].add(i);
        }

        // 构建有向图
        List<Integer>[] g = new ArrayList[26];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < 26; i++) {
            if (pos[i].isEmpty()) {
                continue;
            }
            List<Integer> p = pos[i];
            int l = p.get(0);
            int r = p.get(p.size() - 1);
            for (int j = 0; j < 26; j++) {
                if (j == i) {
                    continue;
                }

                List<Integer> q = pos[j];
                // 查询第 i 个字母和第 j 个字母关系
                int k = lowerBound(q, l);
                // i 所在 [l, r] 包含 j
                if (k < q.size() && q.get(k) <= r) {
                    g[i].add(j);
                }
            }
        }

        // 遍历有向图
        List<int[]> intervals = new ArrayList<>();
        boolean[] vis = new boolean[26];
        for (int i = 0; i < 26; i++) {
            if (pos[i].isEmpty()) {
                continue;
            }

            Arrays.fill(vis, false);
            l = n;
            r = 0;
            // 合并区间
            dfs(i, g, pos, vis);
            intervals.add(new int[]{l, r});
        }

        List<String> ans = new ArrayList<>();
        intervals.sort((a, b) -> a[1] - b[1]);
        int pre = -1;
        for (int[] p : intervals) {
            int l = p[0];
            int r = p[1];
            if (l > pre) {
                pre = r;
                ans.add(s.substring(l, r + 1));
            }
        }

        return ans;
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = -1;
        int right = list.size();
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private int l, r;

    private void dfs(int cur, List<Integer>[] g, List<Integer>[] pos, boolean[] vis) {
        vis[cur] = true;
        List<Integer> p = pos[cur];
        l = Math.min(l, p.get(0));
        r = Math.max(r, p.get(p.size() - 1));
        for (int next : g[cur]) {
            if (!vis[next]) {
                dfs(next, g, pos, vis);
            }
        }
    }

    /**
     * 435. <a href="https://leetcode.cn/problems/non-overlapping-intervals/description/">无重叠区间</a>
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        /*
        给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
        注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
         */
        /*
        问题转换：计算最多可以选多少个互不重叠的区间，没选的区间就是需要移除的区间。
        所选区间中，选最左边的区间（右端点最小）。
        有 a1, a2, a3 区间相交，右端点 a1[1] < a2[1] < a3[1]，任选其中一个区间，就需要去掉其它相交区间，
        如果区间的右端点太大，还会影响后续其它区间的选择，相交区间，选择越小区间越优。
         */
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int ans = 0;
        int preR = Integer.MIN_VALUE;
        for (int[] p : intervals) {
            if (p[0] >= preR) {
                preR = p[1];
                ans++;
            }
        }
        return intervals.length - ans;
    }

}
