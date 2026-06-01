package com.oycm.dp.b.basic;

public class Solution_3 {

    /**
     * 63. <a href="https://leetcode.cn/problems/unique-paths-ii/description/">不同路径 II</a>
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /*
        给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。
        机器人每次只能向下或者向右移动一步。
        网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
        返回机器人能够到达右下角的不同路径数量。
         */
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] f = new int[n + 1];
        f[1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    // 遇到障碍物，到这里的路径数量置为 0
                    f[j + 1] = 0;
                } else {
                    f[j + 1] = f[j + 1] + f[j];
                }
            }
        }

        return f[n];
    }

}
