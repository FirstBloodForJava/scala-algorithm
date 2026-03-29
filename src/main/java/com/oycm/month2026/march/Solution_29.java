package com.oycm.month2026.march;

import java.util.Arrays;

public class Solution_29 {

    /**
     * 2839. <a href="https://leetcode.cn/problems/check-if-strings-can-be-made-equal-with-operations-i/description/">判断通过操作能否让字符串相等 I</a> 1285
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean canBeEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;
        char[] c1 = s1.toCharArray();
        for (int i = 0; i < s2.length() - 2; i++) {
            if (c1[i] == s2.charAt(i)) {
                continue;
            } else if (c1[i + 2] == s2.charAt(i)) {
                c1[i + 2] = c1[i];
            } else {
                return false;
            }
        }
        for (int i = s2.length() - 2; i < s2.length(); i++) {
            if (c1[i] != s2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    static class Solution_29_1 {
        public boolean canBeEqual(String s1, String s2) {
            /*
            间距为 2 的字符，可以通过以下交换变成任意格式
            x - y - z
            y - x - z
            y - z - x
            z - y - x
            z - x - y
            x - z - y
            只有 s1 和 s2 奇偶下标的字符数量一致，都能交换后相等
             */
            int[][] c1 = new int[2][26];
            int[][] c2 = new int[2][26];
            for (int i = 0; i < s1.length(); i++) {
                c1[i % 2][s1.charAt(i) - 'a']++;
                c2[i % 2][s2.charAt(i) - 'a']++;
            }
            return Arrays.deepEquals(c1, c2);
        }
    }

}
