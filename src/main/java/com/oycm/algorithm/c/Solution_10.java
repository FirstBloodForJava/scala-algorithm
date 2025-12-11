package com.oycm.algorithm.c;


public class Solution_10 {

    /**
     * 3708. <a href="https://leetcode.cn/problems/longest-fibonacci-subarray/description/">最长斐波那契子数组</a> 1381
     * <p>
     * 斐波那契数组 ： nums[i] + nums[i+1] = nums[i+2]
     *
     * @param nums
     * @return 求 nums 中 最长的 斐波那契 数组长度
     */
    public int longestSubarray(int[] nums) {
        int ans = 0;
        int cnt = 2;
        int n = nums.length;
        for (int i = 2; i < n; i++) {
            if (nums[i - 2] + nums[i - 1] == nums[i]) {
                cnt++;
                ans = Math.max(cnt, ans);
            } else {
                cnt = 2;
            }
        }
        return ans;
    }

}
