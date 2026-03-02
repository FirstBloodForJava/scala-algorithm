package com.oycm.month2026.march;

public class Solution_1 {

    /**
     * 1689. <a href="https://leetcode.cn/problems/partitioning-into-minimum-number-of-deci-binary-numbers/description/">十-二进制数的最少数目</a> 1355
     *
     * 十-二进制数：十进制数字不含任何前导零，且每一位上的数字不是 0 就是 1
     * @param n 十进制整数的字符串
     * @return 最少数目的 十-二进制数 和 为 n
     */
    public int minPartitions(String n) {
        /*
        脑筋急转弯：求 字符串中最大的数字
         */
        int ans = 0;
        for (char c : n.toCharArray()) {
            ans = Math.max(ans, c);
        }
        return ans - '0';
    }

}
