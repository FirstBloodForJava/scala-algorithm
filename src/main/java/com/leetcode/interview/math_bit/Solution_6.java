package com.leetcode.interview.math_bit;

public class Solution_6 {

    /**
     * 位1的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        /*
        给定一个正整数 n，编写一个函数，获取一个正整数的二进制形式并返回其二进制表达式中 设置位 的个数（也被称为汉明重量）。
         */
        return Integer.bitCount(n);
    }

}
