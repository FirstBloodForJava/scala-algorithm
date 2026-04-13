package com.oycm.month2026.april;

public class Solution_13 {

    /**
     * 1848. <a href="https://leetcode.cn/problems/minimum-distance-to-the-target-element/description/">到目标元素的最小距离</a> 1217
     *
     * @param nums 整数数组
     * @param target 目标值
     * @param start 计算值
     * @return nums[i] == target 的 最小值 abs(i-start)
     */
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length, ans = n;
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                ans = Math.min(ans, Math.abs(i - start));
            }
        }

        return ans;
    }

}
