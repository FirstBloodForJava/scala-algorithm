package com.oycm.hot100.dp_mult;

import java.util.Arrays;

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

class Solution_64_1 {

    public int minPathSum(int[][] grid) {
        /*
        dfs(i, j) = min(dfs(i-1, j), dfs(i, j-1)) + grid[i][j]
        f[i][j] = min(f[i-1][j], f[i][j-1]) + grid[i][j];
        f[i+1][j+1] = min(f[i][j+1], f[i+1][j]) + grid[i][j];
        f[0][j], j [1, n-1] 初始化成 Integer.Max_VALUE，表示无效路径
        f[i][0], i [1, m-1] 初始化成 Integer.Max_VALUE，表示无效路径
        才能使用直接使用上面的判断，
            如果 i = 0 f[i+1][j+1] = grid[i][j] + f[i+1][j];
            如果 j = 0 f[i+1][j+1] = grid[i][j] + f[i][j+1];
         */
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    f[i + 1][j + 1] = grid[i][j] + f[i + 1][j];
                } else if (j == 0) {
                    f[i + 1][j + 1] = grid[i][j] + f[i][j + 1];
                } else {
                    f[i + 1][j + 1] = Math.min(f[i][j + 1], f[i + 1][j]) + grid[i][j];
                }
            }
        }

        return f[m][n];
    }
}

class Solution_64_2 {
    public int minPathSum(int[][] grid) {
        /*

         */
        int n = grid[0].length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        /*
        f[1] 初始化为 0，就不用后面 f[0] 的修改
         */
        f[0] = 0;
        for (int[] row : grid) {
            for (int j = 0; j < row.length; j++) {
                f[j + 1] = Math.min(f[j], f[j + 1]) + row[j];
            }
            f[0] = f[1];
        }

        return f[n];
    }
}