package com.oycm.algorithm.h.basic;

public class Solution_18 {

    /**
     * 2595. <a href="https://leetcode.cn/problems/number-of-even-and-odd-bits/description/">奇偶位数</a> 1207
     *
     * @param n
     * @return
     */
    public int[] evenOddBit(int n) {
        /*
        0x55555555 都是 1 都是偶数下标
        0xaaaaaaaa
         */
        return new int[]{Integer.bitCount(n & 0x55555555), Integer.bitCount(n & 0xaaaaaaaa)};
    }
}
