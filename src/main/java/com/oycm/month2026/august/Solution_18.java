package com.oycm.month2026.august;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_18 {

    /**
     * 3471. <a href="https://leetcode.cn/problems/find-the-largest-almost-missing-integer/description/">找出最大的几近缺失整数</a> 1308
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestInteger(int[] nums, int k) {
        /*
        给你一个整数数组 nums 和一个整数 k 。
        如果整数 x 恰好仅出现在 nums 中的一个大小为 k 的子数组中，则认为 x 是 nums 中的几近缺失（almost missing）整数。
        返回 nums 中 最大的几近缺失 整数，如果不存在这样的整数，返回 -1 。
        子数组 是数组中的一个连续元素序列。
         */
        /*
        分类讨论：n = nums.length, ans 为答案
        n = 1; ans = max(nums); 且只出现一次；
        n = k; ans = max(nums);
        n > k; ans = max(nums[0], nums[n-1]) 除了首尾，其余元素都会被两个长为 k 的子数组出现，两个元素不能在其它位置出现
         */
        int n = nums.length;
        int ans = -1;
        Map<Integer, Boolean> map = new HashMap<>();
        if (k == 1) {
            for (int x : nums) {
                map.put(x, !map.containsKey(x));
            }
        } else if (n == k) {
            ans = Arrays.stream(nums).max().getAsInt();
        } else if (nums[0] != nums[n - 1]) {
            map.put(nums[0], true);
            map.put(nums[n - 1], true);
            for (int i = 1; i < n - 1 && !map.isEmpty(); i++) {
                map.remove(nums[i]);
            }
        }
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                ans = Math.max(ans, entry.getKey());
            }
        }

        return ans;
    }

}
