package com.oycm.algorithm.h.basic;


public class Solution_1 {

    /**
     * 3370. <a href="https://leetcode.cn/problems/smallest-number-with-all-set-bits/">仅含置位位的最小整数</a> 1199
     *
     * 置位 位指的是二进制表示中值为 1 的位
     * @param n 正整数
     * @return 返回 大于等于 n 且二进制表示仅包含 置位 位的 最小 整数 x
     */
    public int smallestNumber(int n) {
        /*
        本质求 n 的二进制最高位
         */
        return (1 << (32 - Integer.numberOfLeadingZeros(n))) - 1;
    }
}
