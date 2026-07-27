package com.oycm.month2026.july;

public class Solution_27 {

    /**
     * 1464. <a href="https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array/description/">数组中两元素的最大乘积</a>
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        /*
        给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
        请你计算并返回该式的最大值。
         */
        /*
        nums[i] > 0，维护最大的两个数
         */
        int max1 = 0;
        int max2 = 0;
        for (int x : nums) {
            if (x > max1) {
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max2 = x;
            }
        }
        return (max1 - 1) * (max2 - 1);
    }

}
