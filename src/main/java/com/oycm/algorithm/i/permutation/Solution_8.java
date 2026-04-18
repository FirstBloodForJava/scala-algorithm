package com.oycm.algorithm.i.permutation;

import java.util.*;

public class Solution_8 {

    /**
     * 1307. <a href="https://leetcode.cn/problems/verbal-arithmetic-puzzle/description/">口算难题</a> 2250
     *
     * @param words  words.length [2, 5]; words[i].length [1, 7]
     * @param result results.length [1, 7]
     * @return 方程可解，返回 true，否则返回 false
     */
    public boolean isSolvable(String[] words, String result) {
        /*
        一个方程，左边用 words 表示，右边用 result 表示。
        需要根据以下规则检查方程是否可解：
            每个字符都会被解码成一位数字（0 - 9）。
            每对不同的字符必须映射到不同的数字。A -> 1, 其它的字母就不能映射到 1
            每个 words[i] 和 result 都会被解码成一个没有前导零的数字。
            左侧数字之和（words）等于右侧数字（result）。
        words, result 表达式中使用的不同字符数最大为 10
         */
        /*
        先找 words, result 中不同字符的数量记为 m，相当于 10 个数字中 在 m 中的排列, 前面几个添加不为 0 的限制
        怎么把排列的结果，映射到字母上, 有限制的一起放前面
        A(m, 10) * m * L, L 表示所有字母的长度
         */
        // 0-9 有哪些可选
        boolean[] enable = new boolean[10];
        // 计算不同字符数
        Set<Character> mapping = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                mapping.add(c);
            }
        }
        for (char c : result.toCharArray()) {
            mapping.add(c);
        }
        List<Character> list = new ArrayList<>();
        mapping.remove(result.charAt(0));
        list.add(result.charAt(0));
        for (String word : words) {
            if (mapping.remove(word.charAt(0))) {
                list.add(word.charAt(0));
            }
        }
        int boundary = list.size();
        list.addAll(mapping);
        return dfs(0, new int[list.size()], list, words, result, enable, boundary);
    }

    public boolean dfs(int i, int[] path, List<Character> list, String[] words, String result, boolean[] enable, int boundary) {
        if (i == list.size()) {
            // 计算
            int left = 0;
            Map<Character, Integer> mapping = new HashMap<>();
            for (int j = 0; j < list.size(); j++) {
                mapping.put(list.get(j), path[j]);
            }
            for (String word : words) {
                left += getWordValue(word, mapping);
            }

            return left == getWordValue(result, mapping);
        }

        for (int j = i < boundary ? 1 : 0; j < 10; j++) {
            // 前面选过了
            if (enable[j]) {
                continue;
            }
            enable[j] = true;
            path[i] = j;
            if (dfs(i + 1, path, list, words, result, enable, boundary)) {
                return true;
            }
            enable[j] = false;
        }

        return false;
    }

    public int getWordValue(String word, Map<Character, Integer> mapping) {
        int val = 0;
        for (char c : word.toCharArray()) {
            val = val * 10 + mapping.get(c);
        }
        return val;
    }

    public static void main(String[] args) {
        Solution_8 solution = new Solution_8();
        System.out.println(solution.isSolvable(new String[]{"SIX","SEVEN","SEVEN"}, "TWENTY"));

        Solution_8_1 solution1 = new Solution_8_1();
        System.out.println(solution1.isSolvable(new String[]{"SIX","SEVEN","SEVEN"}, "TWENTY"));
    }
}

class Solution_8_1 {

    class LetterCoefficient {
        private char letter;
        private int coefficient;

        public LetterCoefficient(char letter, int coefficient) {
            this.letter = letter;
            this.coefficient = coefficient;
        }

        public char getLetter() {
            return letter;
        }

        public int getCoefficient() {
            return coefficient;
        }
    }

    Set<Character> leading = new HashSet<>();
    boolean[] used = new boolean[10];
    Map<Character, Integer> coefficients = new HashMap<>();
    int positiveCount, negativeCount;
    List<LetterCoefficient> positiveList = new ArrayList<>();
    List<LetterCoefficient> negativeList = new ArrayList<>();
    List<Integer> positiveBounds = new ArrayList<>();
    List<Integer> negativeBounds = new ArrayList<>();

    public boolean isSolvable(String[] words, String result) {
        for (String word : words) {
            handleNum(word, 1);
        }
        handleNum(result, -1);
        Set<Map.Entry<Character, Integer>> entries = coefficients.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            char letter = entry.getKey();
            int coefficient = entry.getValue();
            LetterCoefficient lc = new LetterCoefficient(letter, coefficient);
            if (coefficient > 0) {
                positiveList.add(lc);
            } else if (coefficient < 0) {
                negativeList.add(lc);
            }
        }
        positiveCount = positiveList.size();
        negativeCount = negativeList.size();
        // 字母系数按 绝对值 从大到小 排序
        positiveList.sort((a, b) -> b.getCoefficient() - a.getCoefficient());
        negativeList.sort(Comparator.comparingInt(LetterCoefficient::getCoefficient));
        // 不看符号 能取的最大值
        preBounds(positiveBounds, positiveList);
        preBounds(negativeBounds, negativeList);
        return backtrack(0, 0, 0);
    }

    public void preBounds(List<Integer> bounds, List<LetterCoefficient> letterCoefficientList) {
        for (int i = 0; i < letterCoefficientList.size(); i++) {
            int bound = 0;
            for (int j = i, val = 9; j < letterCoefficientList.size(); j++, val--) {
                bound += letterCoefficientList.get(j).getCoefficient() * val;
            }
            bounds.add(bound);
        }
        bounds.add(0);
    }

    public void handleNum(String word, int sign) {
        int wordLength = word.length();
        if (wordLength > 1) {
            leading.add(word.charAt(0));
        }
        for (int i = wordLength - 1, unit = sign; i >= 0; i--, unit *= 10) {
            char letter = word.charAt(i);
            coefficients.put(letter, coefficients.getOrDefault(letter, 0) + unit);
        }
    }

    public boolean backtrack(int total, int positiveIndex, int negativeIndex) {
        if (positiveIndex == positiveCount && negativeIndex == negativeCount) {
            return total == 0;
        }
        // 当前先选那边的数 正数还是负数
        boolean positive;
        if (negativeIndex == negativeCount) {
            if (total > 0) {
                return false;
            }
            positive = true;
        } else if (positiveIndex == positiveCount) {
            if (total < 0) {
                return false;
            }
            positive = false;
        } else {
            positive = positiveList.get(positiveIndex).getCoefficient() + negativeList.get(negativeIndex).getCoefficient() >= 0;
        }
        List<LetterCoefficient> currList = positive ? positiveList : negativeList;
        int currIndex = positive ? positiveIndex : negativeIndex;
        List<Integer> bounds = positive ? negativeBounds : positiveBounds;
        int boundIndex = positive ? negativeIndex : positiveIndex;
        int positiveIncrease = positive ? 1 : 0;
        int negativeIncrease = positive ? 0 : 1;
        LetterCoefficient lc = currList.get(currIndex);
        char letter = lc.getLetter();
        int coefficient = lc.getCoefficient();
        int currBound = -bounds.get(boundIndex) - total;
        int minVal = leading.contains(letter) ? 1 : 0;
        int maxVal = Math.min(currBound / coefficient, 9);
        for (int i = minVal; i <= maxVal; i++) {
            if (!used[i]) {
                used[i] = true;
                int newTotal = total + coefficient * i;
                if (backtrack(newTotal, positiveIndex + positiveIncrease, negativeIndex + negativeIncrease)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }

}

