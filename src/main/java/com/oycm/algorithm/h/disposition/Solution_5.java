package com.oycm.algorithm.h.disposition;

public class Solution_5 {

    /**
     * 3688. <a href="https://leetcode.cn/problems/bitwise-or-of-even-numbers-in-an-array/description/">偶数的按位或运算</a> 1205
     *
     * @param nums
     * @return 求 nums 偶数按位或运算的结果
     */
    public int evenNumberBitwiseORs(int[] nums) {
        /*
        把奇数看作 0
         */
        int ans = 0;
        for (int num : nums) {
            ans |= (num & 1) == 0 ? num : 0;
        }
        return ans;
    }

}
