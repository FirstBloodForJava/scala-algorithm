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
        dfs(i, s) = Math.min(dfs(i-1, s), dfs(i, s-nums[i]) + 1)
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

class Solution_322_1 {
    public int coinChange(int[] coins, int amount) {
        /*
        dfs(i, s) = Math.min(dfs(i-1, s), dfs(i, s-nums[i]) + 1)
        递归翻译成递推
        f[i][s] = Math.min(f[i-1][s], f[i][s-nums[i]] + 1)
        f[i+1][s] = Math.min(f[i][s], f[i+1][s-nums[i]] + 1)
        f[0][0] = 0, f[0][1, s] 都是无穷大
         */
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        Arrays.fill(f[0], Integer.MAX_VALUE / 2);
        f[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int s = 0; s <= amount; s++) {
                if (coins[i] > s) {
                    f[i + 1][s] = f[i][s];
                } else {
                    f[i + 1][s] = Math.min(f[i][s], f[i + 1][s - coins[i]] + 1);
                }

            }
        }
        int ans = f[n][amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }
}

class Solution_322_2 {

    public int coinChange(int[] coins, int amount) {
        /*
        dfs(i, s) = Math.min(dfs(i-1, s), dfs(i, s-nums[i]) + 1)
        递归翻译成递推
        f[i][s] = Math.min(f[i-1][s], f[i][s-nums[i]] + 1)
        f[i+1][s] = Math.min(f[i][s], f[i+1][s-nums[i]] + 1)
        f[0][0] = 0, f[0][1, s] 都是无穷大
        和 0-1 背包相似，f[i+1] 只会用到 f[i] f[i+1] 左边, [0, i-1] 不会被使用
         */
        int n = coins.length;
        int[][] f = new int[2][amount + 1];
        Arrays.fill(f[0], Integer.MAX_VALUE / 2);
        f[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int s = 0; s <= amount; s++) {
                if (coins[i] > s) {
                    f[(i + 1) % 2][s] = f[i % 2][s];
                } else {
                    f[(i + 1) % 2][s] = Math.min(f[i % 2][s], f[(i + 1) % 2][s - coins[i]] + 1);
                }

            }
        }
        int ans = f[n % 2][amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }
}

class Solution_322_3 {
    public int coinChange(int[] coins, int amount) {
        /*
        dfs(i, s) = Math.min(dfs(i-1, s), dfs(i, s-nums[i]) + 1)
        递归翻译成递推
        f[i][s] = Math.min(f[i-1][s], f[i][s-nums[i]] + 1)
        f[i+1][s] = Math.min(f[i][s], f[i+1][s-nums[i]] + 1)
        f[0][0] = 0, f[0][1, s] 都是无穷大
        和 0-1 背包相似，f[i+1] 只会用到 f[i] f[i+1] 左边, [0, i-1] 不会被使用
        f[i]   = {1, 2, 3}
        f[i+1] = {}
        f[i+1] 数组更新过程中，当 s 大于等于 x=coins[i] 时，需要用到上一行的 f[i][s] 和当前行的 s-x
        上一层只会在当前 s 使用到，大于 s 的会用到当前层左边即上一层，可以使用一个数组来记录答案
         */
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int x : coins) {
            for (int s = x; s <= amount; s++) {
                f[s] = Math.min(f[s], f[s - x] + 1);
            }
        }

        int ans = f[amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }
}
