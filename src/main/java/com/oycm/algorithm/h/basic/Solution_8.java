package com.oycm.algorithm.h.basic;


public class Solution_8 {

    /**
     * 1009. <a href="https://leetcode.cn/problems/complement-of-base-10-integer/description/">十进制整数的反码</a> 1235
     *
     * @param n 非负整数
     * @return
     */
    public int bitwiseComplement(int n) {
        return ~n & ((1 << 32 - Integer.numberOfLeadingZeros(n)) - 1);
    }
}
