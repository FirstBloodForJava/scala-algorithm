package com.oycm.week.No491;

public class Solution_2 {

    /**
     * 3857. <a href="https://leetcode.cn/problems/minimum-cost-to-split-into-ones/description/">拆分到 1 的最小总代价</a>
     *
     * 整数 x 拆分为两个正整数 a 和 b，使得 a + b = x, 此操作的代价是 a * b
     *
     * @param n 整数
     * @return 将整数 n 拆分为 n 个 1 所需的最小总代价
     */
    public int minCost(int n) {
        /*
        题解思路
         */
        return n * (n - 1) / 2;
    }

}
