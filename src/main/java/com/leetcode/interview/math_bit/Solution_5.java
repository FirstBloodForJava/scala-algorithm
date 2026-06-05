package com.leetcode.interview.math_bit;

public class Solution_5 {

    /**
     * 颠倒二进制位
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        /*
        颠倒给定的 32 位有符号整数的二进制位。
         */
        /*
        0 <= n <= 2^31 - 2
        n 为偶数
         */
        /*
        低位变高位，不断右移
         */
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= n & 1;
            n >>= 1;
        }

        return res;
    }

}
