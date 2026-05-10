package com.oycm.hot100.ordinary_array;

public class Solution_13 {

    /**
     * 53. <a href="https://leetcode.cn/problems/maximum-subarray/description/">最大子数组和</a>
     *
     * @param nums nums.length [1, 1e5]; nums[i] [-1e4, 1e4]
     * @return
     */
    public int maxSubArray(int[] nums) {
        /*
        给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
         */
        /*
        题解思路：前缀和+贪心
        维护前缀最小值，相当于买卖股票的最佳时机，买入卖出必须交易一次
         */
        int ans = Integer.MIN_VALUE;
        int minPre = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            ans = Math.max(ans, sum - minPre);
            minPre = Math.min(minPre, sum);
        }
        return ans;
    }



}
