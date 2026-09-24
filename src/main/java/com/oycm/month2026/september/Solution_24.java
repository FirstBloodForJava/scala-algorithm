package com.oycm.month2026.september;

public class Solution_24 {

    /**
     * 3550. <a href="https://leetcode.cn/problems/smallest-index-with-digit-sum-equal-to-index/description/">数位和等于下标的最小下标</a> 1200
     *
     * @param nums
     * @return
     */
    public int smallestIndex(int[] nums) {
        /*
        给你一个整数数组 nums 。
        返回满足 nums[i] 的数位和（每一位数字相加求和）等于 i 的 最小 下标 i 。
        如果不存在满足要求的下标，返回 -1 。
         */
        int n = Math.min(nums.length, 28);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int d = 0;
            while (x > 0) {
                d += x % 10;
                x /= 10;
            }
            if (d == i) {
                return i;
            }
        }

        return -1;
    }
}
