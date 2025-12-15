package com.oycm.algorithm.h.basic;

public class Solution_14 {

    /**
     * 231. <a href="https://leetcode.cn/problems/power-of-two/description/">2 的幂</a>
     *
     * @param n 有正整数和负整数
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        /*
        n < 0 不是 2 的幂次
        1 的 个数
        或
        n & (n-1) == 0
         */
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
