package com.oycm.dp.f.max_profit;

public class Solution_7 {

    /**
     * 714. <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/">买卖股票的最佳时机含手续费</a>
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        /*
        给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
        你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
        返回获得利润的最大值。
        注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
         */
        int f0 = 0, f1 = Integer.MIN_VALUE / 2;
        for (int p : prices) {
            // 当前未持有：什么都不做/卖出
            int newF0 = Math.max(f0, f1 + p - fee);
            // 当前持有：什么都不做/买入
            f1 = Math.max(f1, f0 - p);
            f0 = newF0;
        }

        return f0;
    }

}
