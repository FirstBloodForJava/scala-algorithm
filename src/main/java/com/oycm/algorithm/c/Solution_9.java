package com.oycm.algorithm.c;


public class Solution_9 {

    /**
     * 674. <a href="https://leetcode.cn/problems/longest-continuous-increasing-subsequence/description/">最长连续递增序列</a>
     *
     * @param nums 未经排序的整数数组
     * @return 找出最长的升序子数组 长度
     */
    public int findLengthOfLCIS(int[] nums) {
        int ans = 0;
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            cnt++;
            ans = Math.max(cnt, ans);
            if (i < n - 1 && nums[i] >= nums[i+1]) {
                cnt = 0;
            }
        }

        return ans;
    }
}
