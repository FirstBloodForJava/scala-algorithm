package com.oycm.algorithm.h.basic;


public class Solution_4 {

    /**
     * 461. <a href="https://leetcode.cn/problems/hamming-distance/description/">汉明距离</a>
     *
     * 汉明距离: 两个数字对应二进制位不同的位置的数目
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        /*
        异或运算后的 1 的个数
         */
        return Integer.bitCount(x ^ y);
    }
}
