package com.oycm.week.No179;

public class Solution_3 {

    /**
     * 3882. <a href="https://leetcode.cn/problems/minimum-xor-path-in-a-grid/">网格图中最小异或路径</a> 1771
     *
     * @param grid m * n 的二维整数数组, m,n [1, 1000]
     * @return
     */
    public int minCost(int[][] grid) {
        /*
        从 左上角 的单元格 (0, 0) 出发，想要到达 右下角 的单元格 (m - 1, n - 1)；
        每一步中，只能 向右或向下 移动；
        路径的 代价 定义为该路径上所有单元格（包括 起点和终点）的值的 按位异或，求最小路径
         */
        /*
        异或路径最小，不能和求乘法一样, 记录 最小最大值
         */
        int m = grid.length, n = grid[0].length;

        // 0 <= grid[i][j] <= 1023
        // 异或和不会 大于所有的 或值
        int MAX = 0;
        for (int[] row : grid) {
            for (int x : row) {
                MAX |= x;
            }
        }
        MAX++;
        boolean[][][] dp = new boolean[m][n][MAX];
        // true 表示前面到过的值
        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                for (int x = 0; x < MAX; x++) {
                    // 上面所有可能到过的地方都走一边
                    if (i > 0 && dp[i - 1][j][x]) {
                        dp[i][j][x ^ grid[i][j]] = true;
                    }

                    if (j > 0 && dp[i][j - 1][x]) {
                        dp[i][j][x ^ grid[i][j]] = true;
                    }
                }
            }
        }
        // 返回到过的最小值
        for (int x = 0; x < MAX; x++) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}
