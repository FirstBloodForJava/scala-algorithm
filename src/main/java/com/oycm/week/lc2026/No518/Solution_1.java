package com.oycm.week.lc2026.No518;

public class Solution_1 {

    /**
     * 恰好有 K 对相等相邻字符的循环移位数量
     *
     * @param s
     * @param k
     * @return
     */
    public int countRotations(String s, int k) {
        /*
        给你一个长度为 n 的字符串 s 和一个整数 k。
        s 的一次 循环移位 可以通过以下方式得到：选择 s 的一个长度在 0 到 n - 1（包含两端）之间的 前缀 ，并将其移动到字符串末尾，同时保持所有字符的相对顺序不变。
        对于 s 的 每一种 循环移位，定义其 得分 为满足以下条件的下标 i 的数量：0 <= i < n - 1，且位置 i 和 i + 1 处的字符相同。
        返回得分等于 k 的循环移位数量。
        字符串的 前缀 是指从字符串开头开始，并延伸到字符串中某个位置的子串。
        子串 是字符串中一段连续的字符序列，可以为空。
         */
        /*
        O(n) 定长滑动窗口做法
        先计算整个字符串得分
         */
        int n = s.length();
        int score = 0;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                score++;
            }
        }
        s = s + s;
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = n; i < s.length(); i++) {
            if (cs[i - n] == cs[i - n + 1]) {
                score--;
            }
            if (cs[i] == cs[i - 1]) {
                score++;
            }
            if (score == k) {
                count++;
            }
        }
        return count;
    }

}
