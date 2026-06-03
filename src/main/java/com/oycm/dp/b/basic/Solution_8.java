package com.oycm.dp.b.basic;

public class Solution_8 {

    /**
     * 2304. <a href="https://leetcode.cn/problems/minimum-path-cost-in-a-grid/description/">网格中的最小路径代价</a> 1658
     *
     * @param grid
     * @param moveCost
     * @return
     */
    public int minPathCost(int[][] grid, int[][] moveCost) {
        /*
        给你一个下标从 0 开始的整数矩阵 grid ，矩阵大小为 m x n ，由从 0 到 m * n - 1 的不同整数组成。
        你可以在此矩阵中，从一个单元格移动到 下一行 的任何其他单元格。
        如果你位于单元格 (x, y) ，且满足 x < m - 1 ，你可以移动到 (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1) 中的任何一个单元格。
        注意： 在最后一行中的单元格不能触发移动。
        每次可能的移动都需要付出对应的代价，代价用一个下标从 0 开始的二维数组 moveCost 表示，
        该数组大小为 (m * n) x n ，其中 moveCost[i][j] 是从值为 i 的单元格移动到下一行第 j 列单元格的代价。
        从 grid 最后一行的单元格移动的代价可以忽略。
        grid 一条路径的代价是：所有路径经过的单元格的 值之和 加上 所有移动的 代价之和 。从 第一行 任意单元格出发，返回到达 最后一行 任意单元格的最小路径代价。
         */
        /*
        dfs(i, j) 表示 (i, j) -> 最后一行的路径和
         */
        int m = grid.length;
        int n = grid[0].length;

        int ans = Integer.MAX_VALUE;
        int[][] memo = new int[m][n];
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, dfs(0, j, grid, moveCost, memo));
        }

        return ans;
    }

    public int dfs(int i, int j, int[][] grid, int[][] moveCost, int[][] memo) {
        if (i == grid.length - 1) return grid[i][j];
        if (memo[i][j] != 0) return memo[i][j];
        int res = Integer.MAX_VALUE;
        for (int k = 0; k < grid[0].length; k++) {
            // 移动到下一行第 k 列的代价
            res = Math.min(res, dfs(i + 1, k, grid, moveCost, memo) + moveCost[grid[i][j]][k]);
        }

        return memo[i][j] = res + grid[i][j];
    }

    public int minPathCost_dp(int[][] grid, int[][] moveCost) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] f = grid;
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                f[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    f[i][j] = Math.min(f[i][j], moveCost[x][k] + f[i + 1][k]);
                }
                f[i][j] += x;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int x : f[0]) {
            ans = Math.min(ans, x);
        }
        return ans;
    }

}
