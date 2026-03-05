package com.oycm.month2026.march;

public class Solution_5 {

    /**
     * 1758. <a href="https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string/description/">生成交替二进制字符串的最少操作数</a> 1354
     *
     * @param s
     * @return
     */
    public int minOperations(String s) {
        /*
        下标从 0 开始
        偶数下标对应偶数值, 偶奇偶奇
        偶数下标对应奇数值, 奇偶奇偶
         */
        int even = 0, odd = 0;
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c - '0' != i % 2) {
                odd++;
            } else {
                even++;
            }
        }
        return Math.min(even, odd);

    }

}
