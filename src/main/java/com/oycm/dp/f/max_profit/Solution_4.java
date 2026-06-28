package com.oycm.dp.f.max_profit;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 188. <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/">买卖股票的最佳时机 IV</a>
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        /*
        给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
        设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
        注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
         */
        int n = prices.length;
        this.prices = prices;
        memo = new int[n][k + 1][2];
        for (int[][] grow : memo) {
            for (int[] row : grow) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }
        return dfs(n - 1, k, 0);
    }

    int[] prices;
    int[][][] memo;

    public int dfs(int i, int j, int f) {
        if (j < 0) return Integer.MIN_VALUE / 2;
        if (i < 0) return f == 1 ? Integer.MIN_VALUE / 2 : 0;
        if (memo[i][j][f] != Integer.MIN_VALUE) return memo[i][j][f];
        /*
        注意，如果有对 最小值 减半处理，在 买入/卖出 都可以进行减少操作。因为 j-1 减少后，再减去一个负数，会溢出，导致此次计算合法。
        但是如果没有对 最小值 减半处理，只能在 卖出 进行减少操作。
         */
        if (f == 1) {
            return memo[i][j][f] = Math.max(dfs(i - 1, j, 1), dfs(i - 1, j - 1, 0) - prices[i]);
        }
        return memo[i][j][f] = Math.max(dfs(i - 1, j, 0), dfs(i - 1, j, 1) + prices[i]);

    }

}
