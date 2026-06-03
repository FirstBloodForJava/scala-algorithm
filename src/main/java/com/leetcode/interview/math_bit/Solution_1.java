package com.leetcode.interview.math_bit;

public class Solution_1 {

    /**
     * 只出现一次的数字
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        /*
        给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
         */
        /*
        位运算的异或性质
         */
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }

}
