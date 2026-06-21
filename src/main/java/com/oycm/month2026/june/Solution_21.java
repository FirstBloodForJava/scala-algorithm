package com.oycm.month2026.june;

import java.util.Arrays;

public class Solution_21 {

    /**
     * 1833. <a href="https://leetcode.cn/problems/maximum-ice-cream-bars/description/">雪糕的最大数量</a> 1253
     *
     * @param costs
     * @param coins
     * @return
     */
    public int maxIceCream(int[] costs, int coins) {
        /*
        夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
        商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。
        Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
        注意：Tony 可以按任意顺序购买雪糕。
        给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
        你必须使用计数排序解决此问题。
        */
        /*
        排序后，先选价格最低的
         */
        Arrays.sort(costs);
        int res = 0;
        for (int i = 0; i < costs.length && coins > 0; i++) {
            coins -= costs[i];
            if (coins >= 0) {
                res++;
            }
        }

        return res;
    }

}
