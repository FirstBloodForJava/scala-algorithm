package com.oycm.week.lc2026.No504;

import java.util.ArrayList;
import java.util.List;

public class Solution_3 {

    public int maximumSaleItems(int[][] items, int budget) {
        /*
        给你一个二维整数数组 items，其中 items[i] = [factori, pricei] 表示下标为 i 的物品。同时给你一个整数 budget。
        每种物品都有无限个可供购买。你可以购买任意数量的任意物品，但购买物品的总花费最多为 budget。
        购买物品后，你可以根据以下规则获得免费的物品：
            购买的每一份物品 i 最多 可以让你获得 一份 免费的其他物品 j。
            免费物品必须满足 i != j 且 factori 可以整除 factorj。
            对于每个有序对 (i, j)，无论你购买了多少个物品 i，你从物品 i 的购买中 最多只能一次 免费获得物品 j。
            如果免费物品 j 是通过购买不同种类的物品获得的，那么同一种物品 j 可以被免费获得多次。
        返回你在购买物品花费最多为 budget 的前提下，能够获得的物品最大总数，包括购买的物品和免费的物品。
         */
        /*
        1 <= items.length <= 1e5
        1 <= factori <= items.length
        1 <= pricei <= 1e9
        1 <= budget <= 1e9
         */
        /*
        背包容量过大，不适合 dp
        题解思路：
        对于物品 i，计算满足 factorj 是 factori 倍数物品 j 的数量（不包含 i）cnt，那么：
            前 i 次购买能获得 2 个物品；
            继续购买，只能获得 1 个物品；
        设最便宜的物品价格未 mnPrice，分类讨论：
            如果 price >= mnPrice * 2，购买物品最多只能获得 2 个，不如直接购买 mnPrice 价格物品；
            如果 price < mnPrice * 2，每次购买 cnti 次，获得两个物品，为了最大化物品的数量，贪心地，按照价格从低到高购买，所有的 cnt 购买后，购买最低的物品
         */
        int mn = Integer.MAX_VALUE;
        for (int[] p : items) {
            mn = Math.min(mn, p[1]);
        }
        int n = items.length;
        int[] factors = new int[n + 1];
        for (int[] p : items) {
            factors[p[0]]++;
        }
        int[] factorCnt = new int[n + 1];
        List<int[]> q = new ArrayList<>();
        for (int[] p : items) {
            int factor = p[0], price = p[1];
            if (price >= mn * 2) continue;
            if (factorCnt[factor] == 0) {
                for (int j = factor; j <= n; j += factor) {
                    factorCnt[factor] += factors[j];
                }
            }
            if (factorCnt[factor] > 1) {
                q.add(new int[]{price, factorCnt[factor] - 1});
            }
        }
        q.sort((a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int[] p : q) {
            if (p[0] > budget) break;
            int c = Math.min(p[1], budget / p[0]);
            ans += c * 2;
            budget -= c * p[0];
        }

        return ans + budget / mn;
    }
}
