package com.oycm.algorithm.h.basic;

public class Solution_15 {

    /**
     * <a href="https://leetcode.cn/problems/power-of-four/description/">342. 4的幂</a>
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        /*
        100, 10000,
        题解数学思路: 4^k % 3 == 1 => n % k = 1
         */
        return n > 0 && ((n & (n-1)) == 0) && (31 - Integer.numberOfLeadingZeros(n)) % 2 == 0;
    }
}
