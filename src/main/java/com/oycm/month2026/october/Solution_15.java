package com.oycm.month2026.october;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_15 {

    /**
     * 2913. <a href="https://leetcode.cn/problems/subarrays-distinct-element-sum-of-squares-i/description/">子数组不同元素数目的平方和 I</a> 1297
     *
     * @param nums
     * @return
     */
    public int sumCounts(List<Integer> nums) {
        /*
        给你一个下标从 0 开始的整数数组 nums 。
        定义 nums 一个子数组的 不同计数 值如下：
        令 nums[i..j] 表示 nums 中所有下标在 i 到 j 范围内的元素构成的子数组（满足 0 <= i <= j < nums.length ），那么我们称子数组 nums[i..j] 中不同值的数目为 nums[i..j] 的不同计数。
        请你返回 nums 中所有子数组的 不同计数 的 平方 和。
        子数组指的是一个数组里面一段连续 非空 的元素序列。
         */
        int n = nums.size();
        int ans = 0;
        // 依题意模拟
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j <= n; j++) {
                set.add(nums.get(j));
                ans += set.size() * set.size();
            }
        }
        return ans;
    }

}
