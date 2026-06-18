package com.oycm.dp.h.interval_dp;

import java.util.Arrays;

public class Solution_3 {

    /**
     * 3040. <a href="https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-ii/description/">相同分数的最大操作数目 II</a> 1709
     *
     * @param nums
     * @return
     */
    public int maxOperations(int[] nums) {
        /*
        给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
            选择 nums 中最前面两个元素并且删除它们。
            选择 nums 中最后两个元素并且删除它们。
            选择 nums 中第一个和最后一个元素并且删除它们。
        一次操作的 分数 是被删除元素的和。
        在确保 所有操作分数相同(每次选中的元素和固定) 的前提下，请你求出 最多 能进行多少次操作。
        请你返回按照上述要求 最多 可以进行的操作次数。
         */
        /*
        记忆化搜索可以复用，因为不同的 target 不会递归到同一个的 (i, j) 上
         */
        int n = nums.length;
        this.memo = new int[n][n];
        this.nums = nums;
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int res1 = dfs(2, n - 1, nums[0] + nums[1]);
        int res2 = dfs(1, n - 2, nums[0] + nums[n - 1]);
        int res3 = dfs(0, n - 3, nums[n - 2] + nums[n - 1]);

        return Math.max(Math.max(res1, res2), res3) + 1;
    }

    int[] nums;
    int[][] memo;

    public int dfs(int i, int j, int target) {
        if (i >= j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int res = 0;

        if (nums[i] + nums[i + 1] == target) {
            res = Math.max(res, dfs(i + 2, j, target) + 1);
        }
        // 前后
        if (nums[i] + nums[j] == target) {
            res = Math.max(res, dfs(i + 1, j - 1, target) + 1);
        }

        if (nums[j - 1] + nums[j] == target) {
            res = Math.max(res, dfs(i, j - 2, target) + 1);
        }

        return memo[i][j] = res;
    }
}
