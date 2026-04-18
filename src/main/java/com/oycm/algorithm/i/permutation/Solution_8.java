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
        System.out.println(solution.isSolvable(new String[]{"FIA","EDEBC"}, "FCJIFJ"));
    }
}
