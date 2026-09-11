package com.oycm.dp.f.max_profit;

import java.util.Arrays;

public class Solution_3 {

    /**
     * 123. <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/">买卖股票的最佳时机 III</a>
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /*
        给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
        设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
        注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
         */
        /*
        定义：卖出算才算作一次交易
        dfs(i, k, 0/1) 第 i 天结束，交易 k 次，未持有/持有 最大收益
        递归边界：
            k < 0; min
            i < 0 && flag = 0; 0
            i < 0 && flag = 1; min
         */
        int n = prices.length;
        int[][][] memo = new int[n][3][2];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }

        return dfs(n - 1, 2, 0, prices, memo);
    }

    public int dfs(int i, int k, int flag, int[] prices, int[][][] memo) {
        if (k < 0) {
            return Integer.MIN_VALUE;
        }
        if (i < 0) {
            return flag == 1 ? Integer.MIN_VALUE : 0;
        }
        if (memo[i][k][flag] != Integer.MIN_VALUE) {
            return memo[i][k][flag];
        }
        if (flag == 1) {
            // 持有
            return memo[i][k][flag] = Math.max(dfs(i - 1, k, flag, prices, memo), dfs(i - 1, k, 0, prices, memo) - prices[i]);
        } else {
            return memo[i][k][flag] = Math.max(dfs(i - 1, k, flag, prices, memo), dfs(i - 1, k - 1, 1, prices, memo) + prices[i]);
        }

    }
}
