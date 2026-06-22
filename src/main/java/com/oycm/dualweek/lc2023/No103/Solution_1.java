package com.oycm.dualweek.lc2023.No103;

public class Solution_1 {

    /**
     * 2656. <a href="https://leetcode.cn/problems/maximum-sum-with-exactly-k-elements/description/">K 个元素的最大和</a> 1213
     *
     * @param nums 1 <= nums.length <= 100; 1 <= nums[i] <= 100
     * @param k    1 <= k <= 100
     * @return
     */
    public int maximizeSum(int[] nums, int k) {
        /*
        给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你需要执行以下操作 恰好 k 次，最大化你的得分：
            从 nums 中选择一个元素 m 。
            将选中的元素 m 从数组中删除。
            将新元素 m + 1 添加到数组中。
            你的得分增加 m 。
            请你返回执行以上操作恰好 k 次后的最大得分。
         */
        /*
        要想得分最大，找出最大元素 m
            第一次得分 m，数组中最大元素 m+1
            第二次得分 m+1，数组中最大元素 m+2
        m + m-1 + m-k 的等差数列求和
         */
        int m = 0;
        for (int num : nums) {
            m = Math.max(m, num);
        }
        return k * m + k * (k - 1) / 2;
    }

}
