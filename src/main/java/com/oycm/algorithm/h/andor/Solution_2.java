package com.oycm.algorithm.h.andor;


public class Solution_2 {

    /**
     * 1318. <a href="https://leetcode.cn/problems/minimum-flips-to-make-a-or-b-equal-to-c/description/">或运算的最小翻转次数</a> 1383
     * <p>
     * 对 a 或 b 的二进制位进行翻转最少次数 a | b == c
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minFlips(int a, int b, int c) {
        /*
        a & ~c 1 的个数 是 1 翻转为 0 的次数
        b & ~c 1 的个数 是 1 翻转为 0 的次数
        怎么计算 a | b 中 0 需要翻转为 1 的次数 (a | b) ^ c 1 的个数, 这里会多算 a, b 1 翻转成 0 的个数

        ~(a | b) & c 结果中 1 的个数 就是 0 需要翻转成 1 的次数
         */
        int rev = ~c;
        return Integer.bitCount(a & rev) + Integer.bitCount(b & rev) + Integer.bitCount((a | b) ^ c);
    }

}
