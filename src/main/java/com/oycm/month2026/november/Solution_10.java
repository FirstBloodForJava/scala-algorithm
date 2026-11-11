package com.oycm.month2026.november;

public class Solution_10 {

    /**
     * 3789. <a href="https://leetcode.cn/problems/minimum-cost-to-acquire-required-items/description/">采购的最小花费</a> 1580
     *
     * @param cost1
     * @param cost2
     * @param costBoth
     * @param need1
     * @param need2
     * @return
     */
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        /*
        给你五个整数 cost1, cost2, costBoth, need1 和 need2。
        有三种类型的物品可以购买：
            类型 1 的物品花费 cost1，并仅满足类型 1 的需求 1 个单位。
            类型 2 的物品花费 cost2，并仅满足类型 2 的需求 1 个单位。
            类型 3 的物品花费 costBoth，同时满足类型 1 和类型 2 的需求各 1 个单位。
        你需要购买足够的物品，使得：
            满足类型 1 的总需求 至少 为 need1。
            满足类型 2 的总需求 至少 为 need2。
        返回满足这些需求的 最小 可能总花费。
         */
        // 各自买各自的
        long r1 = (long) cost1 * need1 + (long) cost2 * need2;
        if (need1 > need2) {
            int tmp = need1;
            need1 = need2;
            need2 = tmp;
            cost2 = cost1;
        }
        // 全部买两种
        long r2 = (long) costBoth * need2;
        // 两种合并买 costBoth < cost1 + cost2
        long rs = (long) costBoth * need1 + (long) (need2 - need1) * cost2;

        return Math.min(r1, Math.min(r2, rs));
    }

}
