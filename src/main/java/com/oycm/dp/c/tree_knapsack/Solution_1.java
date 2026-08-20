package com.oycm.dp.c.tree_knapsack;

public class Solution_1 {

    /**
     * 3562. <a href="https://leetcode.cn/problems/maximum-profit-from-trading-stocks-with-discounts/">折扣价交易股票的最大利润</a> 2458
     *
     * @param n
     * @param present
     * @param future
     * @param hierarchy
     * @param budget
     * @return
     */
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        /*
        给你一个整数 n，表示公司中员工的数量。每位员工都分配了一个从 1 到 n 的唯一 ID ，其中员工 1 是 CEO，是每一个员工的直接或间接上司。
        另给你两个下标从 1 开始的整数数组 present 和 future，两个数组的长度均为 n，具体定义如下：
            present[i] 表示第 i 位员工今天可以购买股票的 当前价格。
            future[i] 表示第 i 位员工明天可以卖出股票的 预期价格。
        公司的层级关系由二维整数数组 hierarchy 表示，其中 hierarchy[i] = [ui, vi] 表示员工 ui 是员工 vi 的直属上司。
        此外，再给你一个整数 budget，表示可用于投资的总预算。
        公司有一项折扣政策：如果某位员工的直属上司购买了公司的股票，那么该员工可以以 半价 购买股票（即 floor(present[v] / 2)）。
        请返回在不超过给定预算的情况下可以获得的 最大利润 。
        注意：
            每只股票最多只能购买一次。
            不能使用股票未来的收益来增加投资预算，购买只能依赖于 budget。
         */
        //  待学习
        return 0;
    }

}
