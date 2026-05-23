package com.oycm.dp.c.complete_backpack;

import java.util.Arrays;

public class Solution_1 {

    /**
     * 322. <a href="https://leetcode.cn/problems/coin-change/description/">零钱兑换</a>
     *
     * @param coins  coins.length [1, 12]
     * @param amount [0, 1e4]
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        /*
        给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
        计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
        你可以认为每种硬币的数量是无限的。
         */
        /*
        dfs(i, s) 表示从 nums 中选择一些数（可重复选），和恰好为 s 的最小选择数
            不选，则看 dfs(i-1, s) 最小选择数量；
            选，则看 dfs(i, s-nums[i]) + 1 有哪些选法；
        递归边界 i < 0，如果 s = 0 返回 0，否则返回 ∞
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
        int ans = dfs(n - 1, amount, coins, memo);
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    public int dfs(int i, int c, int[] coins, int[][] memo) {
        if (i < 0) return c == 0 ? 0 : Integer.MAX_VALUE / 2;
        if (memo[i][c] != -1) return memo[i][c];
        if (coins[i] > c) {
            // 只能不选
            return memo[i][c] = dfs(i - 1, c, coins, memo);
        }
        return memo[i][c] = Math.min(dfs(i - 1, c, coins, memo), 1 + dfs(i, c - coins[i], coins, memo));
    }

}
