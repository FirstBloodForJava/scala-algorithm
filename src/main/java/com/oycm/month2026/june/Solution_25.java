package com.oycm.month2026.june;

public class Solution_25 {

    /**
     * 3737. <a href="https://leetcode.cn/problems/count-subarrays-with-majority-element-i/description/">统计主要元素子数组数目 I</a> 1423
     *
     * @param nums
     * @param target
     * @return
     */
    public int countMajoritySubarrays(int[] nums, int target) {
        /*
        给你一个整数数组 nums 和一个整数 target。
        返回数组 nums 中满足 target 是 主要元素 的 子数组 的数目。
        一个子数组的 主要元素 是指该元素在该子数组中出现的次数 严格大于 其长度的 一半 。
        子数组 是数组中的一段连续且 非空 的元素序列。
         */
        /*
        1 <= nums.length <= 1000
        1 <= nums[i] <= 1e9
        1 <= target <= 1e9
         */
        /*
        滑动窗口 判断不出来
        暴力枚举所有的子数组
         */
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int heap = 0;
            // 所有子数组，判断 target 是否为众数
            for (int j = i; j < n; j++) {
                heap += target == nums[j] ? 1 : -1;
                if (heap > 0) ans++;
            }
        }

        return ans;
    }

}
