package com.oycm.hot100.dp;

import java.util.Arrays;

public class Solution_85 {

    /**
     * 322. <a href="https://leetcode.cn/problems/coin-change/description/">零钱兑换</a>
     *
     * @param coins  coins.length [1, 12]; coins[i] [1, 1<<31 - 1]
     * @param amount [0, 1e4]
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        /*
        给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
        计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1。
        你可以认为每种硬币的数量是无限的。
         */
        /*
        可重复选，和完全平方数像，但是这里会存在无法组合的情况
        coins 先排序。
        dfs(i, amount) 表示从 i 个不同硬币中选出金额为 amount 的最小硬币数量
            第 i 个硬币不选，dfs(i-1, amount)
            第 i 个硬币选，dfs(i, amount - coins[i]) coins[i] > amount，一定不能选。
        递归边界 i = 0 时，amount == 0，则返回 0，否则返回 max
         */
        Arrays.sort(coins);
        int n = coins.length;
        int[][] memo = new int[n + 1][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int ans = dfs(n, amount, coins, memo);
        return ans == Integer.MAX_VALUE / 2 ? -1 : ans;
    }

    public int dfs(int i, int amount, int[] coins, int[][] memo) {
        if (i == 0) return amount == 0 ? 0 : Integer.MAX_VALUE / 2;
        if (memo[i][amount] != -1) return memo[i][amount];

        if (coins[i - 1] > amount) return memo[i][amount] = dfs(i - 1, amount, coins, memo);

        return memo[i][amount] = Math.min(dfs(i - 1, amount, coins, memo), 1 + dfs(i, amount - coins[i - 1], coins, memo));
    }

    public int coinChange_dp(int[] coins, int amount) {
        /*
        dfs(i, amount) =
            min(dfs(i, amount - coins[i]) + 1, dfs(i-1, amount))
        选一个硬币之后，继续重复选，这个硬币硬币不选的方案数
        f[i][amount] = min(f[i][amount-x] + 1, f[i-1][amount])
         */
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        Arrays.fill(f[0], Integer.MAX_VALUE / 2);
        f[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= amount; c++) {
                if (c < coins[i]) {
                    // 硬币比金额还大，硬币不能选
                    f[i + 1][c] = f[i][c];
                } else {
                    f[i + 1][c] = Math.min(f[i][c], f[i + 1][c - coins[i]] + 1);
                }
            }
        }

        int ans = f[n][amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }


    public int coinChange_dp_optimize(int[] coins, int amount) {
        /*
        dfs(i, amount) =
            min(dfs(i, amount - coins[i]) + 1, dfs(i-1, amount))
        选一个硬币之后，继续重复选，这个硬币硬币不选的方案数
        f[i][amount] = min(f[i][amount-x] + 1, f[i-1][amount])
         */
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int x : coins) {
            for (int i = x; i <= amount; i++) {
                f[i] = Math.min(f[i], f[i - x] + 1);
            }
        }

        int ans = f[amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

}
