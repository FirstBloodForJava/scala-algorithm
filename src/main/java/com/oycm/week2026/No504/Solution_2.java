package com.oycm.week2026.No504;

public class Solution_2 {
    public int maximumSaleItems(int[][] items, int budget) {
        /*
        给你一个二维整数数组 items，其中 items[i] = [factori, pricei] 表示下标为 i 的物品。同时给你一个整数 budget。
        每种物品都有无限个可供购买。你可以购买任意数量的任意物品，但购买物品的总花费最多为 budget。
        购买物品后，你可以根据以下规则获得免费的物品：
            如果你购买了若干个物品 i，所有满足 j != i 且 factori 可以整除 factorj 的物品 j ，你都能免费 获得一份。
            重复购买物品i 不能 再获取额外的免费物品。
            如果免费物品 j 是通过购买不同种类的物品获得的，那么同一种物品 j 可以被免费获得多次。
        返回你在购买物品花费最多为 budget 的前提下，能够获得的物品最大总数，包括购买的物品和免费的物品。
         */
        /*
        1 <= items.length <= 1000
        1 <= factori, pricei <= 1500
        1 <= budget <= 1500
         */
        /*
        对于物品 i，统计 factorj % factori == 0 的数量（包含 i）cnti，那么 cnti 就是首次购买物品 i 所获得物品数量。
        如果物品只能购买一次，则是标准的 0-1 背包问题。
        f[i] 表示已用容量 i，所获得的物品数量，剩余的 budget - i 全部用来购买价格最低的物品，不断求最大值
         */
        /*
        计算因子数量优化
         */
        int[] f = new int[budget + 1];
        int mn = Integer.MAX_VALUE;
        int mxFactor = 0;
        for (int[] p : items) {
            mn = Math.min(mn, p[1]);
            mxFactor = Math.max(mxFactor, p[0]);
        }
        // 物品因子数量
        int[] factors = new int[mxFactor + 1];
        for (int[] p : items) {
            factors[p[0]]++;
        }
        // 待计算因子数量
        int[] factorCnt = new int[mxFactor + 1];
        int sumPrice = 0;
        for (int[] p : items) {
            int factor = p[0], price = p[1];
            mn = Math.min(mn, price);
            // 该因子未计算才处理
            if (factorCnt[factor] == 0) {
                for (int i = factor; i <= mxFactor ; i+=factor) {
                    factorCnt[factor] += factors[i];
                }
            }
            int cnt = factorCnt[factor];
            // 前面已遍历价格至多为 sumPrice，sumPrice < budget 时的价格无法凑出来
            sumPrice = Math.min(sumPrice + price, budget);
            for (int i = sumPrice; i >= price; i--) {
                f[i] = Math.max(f[i], f[i - price] + cnt);
            }
        }
        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            ans = Math.max(ans, f[i] + (budget - i) / mn);
        }

        return ans;
    }
}
