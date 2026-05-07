package com.oycm.dp.a.climb_stairs;

public class Solution_4 {

    /**
     * 377. <a href="https://leetcode.cn/problems/combination-sum-iv/description/">组合总和 Ⅳ</a> 1600
     *
     * @param nums   不同 整数组成的数组
     * @param target 目标整数
     * @return 从 nums 中找出并返回总和为 target 的元素组合的个数。
     */
    public int combinationSum4(int[] nums, int target) {
        /*
        题解思路：本质是 70 的爬楼梯，每次从 nums 中选一个数，作为往上爬的台阶数，计算爬 target 个台阶有多少种方案
         */
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int x : nums) {
                if (x <= i) {
                    dp[i] += dp[i - x];
                }
            }
        }

        return dp[target];
    }

    public int dfs(int i, int[] nums, int[] memo) {
        if (i == 0) {
            return 1;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = 0;
        for (int x : nums) {
            if (i >= x) {
                res += dfs(i - x, nums, memo);
            }
        }
        return memo[i] = res;
    }

}
