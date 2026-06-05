package com.leetcode.interview.math_bit;

public class Solution_9 {

    /**
     * 3的幂
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        /*
        给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
        整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
         */
        /*
        -2^31 <= n <= 2^31 - 1
         */
        return n > 0 && 1162261467 % n == 0;
    }

}
