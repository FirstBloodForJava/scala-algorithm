package com.oycm.month2026.august;

import java.util.HashMap;
import java.util.Map;

public class Solution_12 {

    /**
     * 2958. <a href="https://leetcode.cn/problems/length-of-longest-subarray-with-at-most-k-frequency/description/">最多 K 个重复元素的最长子数组</a> 1535
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubarrayLength(int[] nums, int k) {
        /*
        给你一个整数数组 nums 和一个整数 k 。
        一个元素 x 在数组中的 频率 指的是它在数组中的出现次数。
        如果一个数组中所有元素的频率都 小于等于 k ，那么我们称这个数组是 好 数组。
        请你返回 nums 中 最长好 子数组的长度。
        子数组 指的是一个数组中一段连续非空的元素序列。
         */
        /*
        不定长滑动窗口求最长
         */
        int ans = 0;
        int l = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int r = 0; r < nums.length; r++) {
            cnt.merge(nums[r], 1, Integer::sum);
            while (cnt.get(nums[r]) > k) {
                cnt.merge(nums[l], -1, Integer::sum);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

}
