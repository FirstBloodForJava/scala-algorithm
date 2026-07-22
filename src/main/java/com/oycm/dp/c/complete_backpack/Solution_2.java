package com.oycm.dp.c.complete_backpack;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 518. <a href="https://leetcode.cn/problems/coin-change-ii/description/">零钱兑换 II</a>
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        /*
        给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
        请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
        假设每一种面额的硬币有无限个。
        题目数据 保证 最终 结果符合 32 位 带符号整数。
         */
        /*
        求方案数。
        dfs(i, s) 表示从 nums 中选择一些数（可重复选），和恰好为 s 方案数
            不选，则看 dfs(i-1, s) 方案数；
            选，则看 dfs(i, s-nums[i]) 方案数；
        递归边界 i < 0，如果 s = 0 返回 1，否则返回 0
        dfs(i, s) = dfs(i-1, s), dfs(i, s-nums[i])
         */
        int n = coins.length;
        int[][] memo = new int[n][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        /*
        amount 最大为 1e4，最大选择次数为 1e4，远小于 Integer.MAX_VALUE / 2
        这里无法组成，返回的一定是 Integer.MAX_VALUE / 2
         */
        return dfs(n - 1, amount, coins, memo);
    }

    public int dfs(int i, int c, int[] coins, int[][] memo) {
        if (i < 0) return c == 0 ? 1 : 0;
        if (memo[i][c] != -1) return memo[i][c];
        if (coins[i] > c) {
            // 只能不选
            return memo[i][c] = dfs(i - 1, c, coins, memo);
        }
        return memo[i][c] = dfs(i - 1, c, coins, memo) + dfs(i, c - coins[i], coins, memo);
    }

}
