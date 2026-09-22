package com.oycm.month2026.september;

public class Solution_22 {

    /**
     * 3525. <a href="https://leetcode.cn/problems/find-x-value-of-array-ii/description/">求出数组的 X 值 II</a> 2645
     *
     * @param nums
     * @param k
     * @param queries
     * @return
     */
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        /*
        给你一个由 正整数 组成的数组 nums 和一个 正整数 k。同时给你一个二维数组 queries，其中 queries[i] = [indexi, valuei, starti, xi]。
        你可以对 nums 执行 一次 操作，移除 nums 的任意 后缀 ，使得 nums 仍然非空。
        给定一个 x，nums 的 x 值 定义为执行以上操作后剩余元素的 乘积 除以 k 的 余数 为 x 的方案数。
        对于 queries 中的每个查询，你需要执行以下操作，然后确定 xi 对应的 nums 的 x 值：
            将 nums[indexi] 更新为 valuei。仅这个更改在接下来的所有查询中保留。
            移除 前缀 nums[0..(starti - 1)]（nums[0..(-1)] 表示 空前缀 ）。
        返回一个长度为 queries.length 的数组 result，其中 result[i] 是第 i 个查询的答案。
        数组的一个 前缀 是从数组开始位置到任意位置的子数组。
        数组的一个 后缀 是从数组中任意位置开始直到结束的子数组。
        子数组 是数组中一段连续的元素序列。
        注意：操作中所选的前缀或后缀可以是 空的 。
         */
        // todo 线段的
        return null;
    }

}
