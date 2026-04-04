package com.oycm.month2026.april;

public class Solution_4 {

    /**
     * 2087. <a href="https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/description/">网格图中机器人回家的最小代价</a> 1744
     * m x n 的网格图
     *
     * @param startPos length = 2; (startPos[0], startPos[1]) 表示机器人的坐标
     * @param homePos  length = 2; (homePos[0], homePos[1]) 表示家的坐标
     * @param rowCosts length = m; rowCosts[r] 机器人往 上 或者往 下 移动到第 r 行 的格子
     * @param colCosts length = n; 机器人往 左 或者往 右 移动到第 c 列 的格子
     * @return
     */
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        /*
        就是计算 机器人坐标到 家坐标 最短移动多少行，多少列的代价和
        homePos[0] >= startPos[0] 从上往下移动 rowSum[homePos[0]+1] - rowSum[startPos[0] + 1] 前缀和就是行移动代价
        homePos[0] < startPos[0] 从下往往移动 rowSum[startPos[0]] - rowSum[homePos[0]]
        列的和同理
        或者直接循环遍历计算
         */
        /*
        题解分析
        homePos[0] > startPos[0] 计算的数组的区间 [startPos[0] + 1, homePos[0]]
        homePos[0] < startPos[0] 计算的数去的区间 [homePos[0], startPos[0] - 1]
        两个区间都没有取 startPos[0], 只要先减去 起始点的值，再根据最小值最大值区间遍历即可
        先减后加
         */
        return getSum(startPos[0], homePos[0], rowCosts) + getSum(startPos[1], homePos[1], colCosts);
    }

    public int getSum(int start, int end, int[] costs) {
        if (start == end) return 0;
        int sum = 0;
        boolean moveDown = start < end;
        while (start != end) {
            if (moveDown) {
                sum += costs[++start];
            } else {
                sum += costs[--start];
            }
        }
        return sum;
    }

    public int getSum2(int start, int end, int[] costs) {
        int sum = -costs[start];
        int min = Math.min(start, end);
        int max = Math.max(start, end);
        for (int i = min; i <= max; i++) {
            sum += costs[i];
        }
        return sum;
    }


}
