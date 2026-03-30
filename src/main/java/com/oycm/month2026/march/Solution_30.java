package com.oycm.month2026.march;

public class Solution_30 {

    /**
     * 2840. <a href="https://leetcode.cn/problems/check-if-strings-can-be-made-equal-with-operations-ii/description/">判断通过操作能否让字符串相等 II</a> 1486
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkStrings(String s1, String s2) {
        /*
        可以对两个字符串中的 任意一个 执行以下操作 任意 次：
            选择两个下标 i 和 j ，满足 i < j 且 j - i 是 偶数，可以交换下标对应的字符
         */
        /*
        偶数下标对, 奇数下标对 可任意进行交换
         */
        int[][] c1 = new int[26][2];
        for (int i = 0; i < s1.length(); i++) {
            c1[s1.charAt(i) - 'a'][i % 2]++;
            c1[s2.charAt(i) - 'a'][i % 2]--;
        }
        for (int[] row : c1) {
            if (row[0] != 0 || row[1] != 0) {
                return false;
            }
        }
        return true;
    }

}
