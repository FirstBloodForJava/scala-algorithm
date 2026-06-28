package com.oycm.dp.f.max_profit;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 122. <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/">买卖股票的最佳时机 II</a>
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /*
        给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
        在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。
        然而，你可以在 同一天 多次买卖该股票，但要确保你持有的股票不超过一股。
        返回 你能获得的 最大 利润。
         */
        /*
        dfs(i, 0/1) 表示第 i 天结束是，未持有/持有 股票的最大利润。
        第 i-1 天的结束，就是第 i 天的开始。
        dfs(i-1, *) 也表示第 i 天开始时的最大利润
         */
        int n = prices.length;
        int[][] memo = new int[n][2];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return dfs(n - 1, 0, prices, memo);
    }

    public int dfs(int i, int flag, int[] prices, int[][] memo) {
        if (i < 0) return flag == 1 ? Integer.MIN_VALUE : 0;
        if (memo[i][flag] != Integer.MIN_VALUE) return memo[i][flag];
        if (flag == 1) {
            /*
            第 i 天持有股票的最大利润：
                当天不操作，第 i-1 持有股票的最大利润；
                当天买入股票，第 i-1 卖出股票的最大利润；
            两者取最大值
             */
            return memo[i][flag] = Math.max(dfs(i - 1, flag, prices, memo), dfs(i - 1, 0, prices, memo) - prices[i]);
        } else {
            /*
            第 i 天未持有股票的最大利润：
                当天不操作，第 i-1 未持有股票的最大利润；
                当天卖出股票，第 i-1 持有股票的最大利润；
            两者取最大值
             */
            return memo[i][flag] = Math.max(dfs(i - 1, flag, prices, memo), dfs(i - 1, 1, prices, memo) + prices[i]);
        }
    }

    public int maxProfit_dp_1(int[] prices) {
        /*
        dfs(i, 0) = max( dfs(i-1, 0), dfs(i-1,1) + prices[i] )
        dfs(i, 1) = max( dfs(i-1, 1), dfs(i-1,0) - prices[i] )
        f[i][0] = max( f[i-1][0], f[i-1][1] + prices[i] )
        f[i][1] = max( f[i-1][1], f[i-1][0] - prices[i] )
         */
        int n = prices.length;
        int[][] f = new int[n + 1][2];
        f[0][1] = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            f[i + 1][0] = Math.max(f[i][0], f[i][1] + prices[i]);
            f[i + 1][1] = Math.max(f[i][1], f[i][0] - prices[i]);
        }

        return f[n][0];
    }

    public int maxProfit_dp_2(int[] prices) {
        /*
        dfs(i, 0) = max( dfs(i-1, 0), dfs(i-1,1) + prices[i] )
        dfs(i, 1) = max( dfs(i-1, 1), dfs(i-1,0) - prices[i] )
        f[i][0] = max( f[i-1][0], f[i-1][1] + prices[i] )
        f[i][1] = max( f[i-1][1], f[i-1][0] - prices[i] )
        f[i] 计算只用到了 f[i-1] 的两个变量
         */
        int n = prices.length;
        int f0 = 0, f1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int tempF0 = Math.max(f0, f1 + prices[i]);
            f1 = Math.max(f1, f0 - prices[i]);
            f0 = tempF0;
        }

        return f0;
    }

}
