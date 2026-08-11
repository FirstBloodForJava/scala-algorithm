package com.oycm.dp.c.backpack;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 3877. <a href="https://leetcode.cn/problems/minimum-removals-to-achieve-target-xor/description/">达到目标异或值的最少删除次数</a> 1745
     *
     * @param nums
     * @param target
     * @return
     */
    public int minRemovals(int[] nums, int target) {
        /*
        给你一个整数数组 nums 和一个整数 target。
        你可以从 nums 中移除 任意 数量的元素（可能为零）。
        返回使剩余元素的 按位异或和 等于 target 所需的 最小 移除次数。如果无法达到 target，则返回 -1。
        空数组的按位异或和为 0。
         */
        /*
        移除最小次数，可以转换成：最多可以选多少数（最长子序列），使得异或和为 target。
        先求 nums 最大值，整个 nums 能取得的最大值是否大于等于 target。
        dfs(i, j) 表示和恰好为 j 的子序列最长长度。
            dfs(i, j) = max(dfs(i-1, j), dfs(i-1, j ^ nums[i]) + 1) 不选/选 取最大
        递归边界 dfs(-1, 0) 返回 0; dfs(-1, j) j > 0，返回 min
         */
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int m = 32 - Integer.numberOfLeadingZeros(max);
        if (1 << m <= target) {
            return -1;
        }
        int n = nums.length;
        int l = 1 << m;
        int[][] f = new int[n + 1][l];
        for (int[] row : f) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        f[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                f[i + 1][j] = Math.max(f[i][j], f[i][j ^ nums[i]] + 1);
            }
        }

        if (f[n][target] < 0) return -1;

        return n - f[n][target];
    }

    public int dfs(int i, int j, int[] nums, int[][] memo) {
        if (i < 0) {
            return j == 0 ? 0 : Integer.MIN_VALUE;
        }
        if (memo[i][j] != -1) return memo[i][j];

        return memo[i][j] = Math.max(dfs(i - 1, j, nums, memo), dfs(i - 1, j ^ nums[i], nums, memo) + 1);
    }

}
