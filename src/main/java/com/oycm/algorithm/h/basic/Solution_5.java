package com.oycm.algorithm.h.basic;


public class Solution_5 {

    /**
     * 2220. <a href="https://leetcode.cn/problems/minimum-bit-flips-to-convert-number/description/">转换数字的最少位翻转次数</a> 1282
     * <p>
     * 位翻转：将整数二进制一个位进行翻转 1 变成 0 或 0 变成 1
     *
     * @param start
     * @param goal
     * @return 求 将 start 转变成 goal 的最少翻转次数
     */
    public int minBitFlips(int start, int goal) {
        /*
        start 和 goal 不同元素的个数
         */
        return Integer.bitCount(start ^ goal);
    }
}
