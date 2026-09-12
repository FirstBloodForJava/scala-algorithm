package com.oycm.dp.f.max_profit;

public class Solution_5 {

    /**
     * 3573. <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-v/description/">买卖股票的最佳时机 V</a> 1777
     *
     * @param prices
     * @param k
     * @return
     */
    public long maximumProfit(int[] prices, int k) {
        /*
        给你一个整数数组 prices，其中 prices[i] 是第 i 天股票的价格（美元），以及一个整数 k。
        你最多可以进行 k 笔交易，每笔交易可以是以下任一类型：
            普通交易：在第 i 天买入，然后在之后的第 j 天卖出，其中 i < j。你的利润是 prices[j] - prices[i]。
            做空交易：在第 i 天卖出，然后在之后的第 j 天买回，其中 i < j。你的利润是 prices[i] - prices[j]。
        注意：你必须在开始下一笔交易之前完成当前交易。此外，你不能在已经进行买入或卖出操作的同一天再次进行买入或卖出操作。
        通过进行 最多 k 笔交易，返回你可以获得的最大总利润。
         */
        /*
        增加一个状态
         */
        long[][] f = new long[k + 2][3];
        for (int j = 1; j < k + 2; j++) {
            f[j][1] = Long.MIN_VALUE / 2;
        }
        f[0][0] = Long.MIN_VALUE / 2;
        for (int p : prices) {
            for (int j = k + 1; j > 0; j--) {
                // 未持有，三种情况：什么都不做，进行一次卖出（增加一次交易），进行一次做空（增加一次交易）
                f[j][0] = Math.max(f[j][0], Math.max(f[j][1] + p, f[j][2] - p));
                // 持有，两种情况：什么都不做，当前买入
                f[j][1] = Math.max(f[j][1], f[j - 1][0] - p);
                // 做空，两种情况：什么都不做，当前卖出
                f[j][2] = Math.max(f[j][2], f[j - 1][0] + p);
            }
        }

        return f[k + 1][0];
    }

}
