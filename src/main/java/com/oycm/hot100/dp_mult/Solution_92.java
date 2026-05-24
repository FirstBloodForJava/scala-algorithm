package com.oycm.hot100.dp_mult;

public class Solution_92 {

    /**
     * 64. <a href="https://leetcode.cn/problems/minimum-path-sum/description/">最小路径和</a>
     *
     * @param grid grid[i][j] [0, 200]; 1 <= m, n <= 200
     * @return
     */
    public int minPathSum(int[][] grid) {
        /*
        给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
         */
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];

        return dfs(m - 1, n - 1, grid, memo);
    }

    public int dfs(int i, int j, int[][] grid, int[][] memo) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE / 2;
        }
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (memo[i][j] > 0) return memo[i][j];

        return memo[i][j] = Math.min(dfs(i - 1, j, grid, memo), dfs(i, j - 1, grid, memo)) + grid[i][j];
    }


}
