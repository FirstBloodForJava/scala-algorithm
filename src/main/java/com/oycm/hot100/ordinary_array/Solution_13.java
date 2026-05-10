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

    public int maxSubArray_dp(int[] nums) {
        /*
        定义 f[i] 为 i 结尾时的最大子数组和，分类讨论：
            nums[i] 独自成为子数组，f[i] = nums[i]；
            nums[i] 和前面的子数组拼接，f[i] = f[i-1] + nums[i]，只有当 f[i-1] >= 0 时，拼接才会变大
         */
        int ans = nums[0];
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1], 0) + nums[i];
            ans = Math.max(f[i], ans);
        }

        return ans;
    }


}
