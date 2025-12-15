package com.oycm.algorithm.h.basic;

public class Solution_16 {

    /**
     * 191. <a href="https://leetcode.cn/problems/number-of-1-bits/description/">位1的个数</a>
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}
