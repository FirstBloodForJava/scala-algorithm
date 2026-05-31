package com.oycm.dp.b.basic;

import java.util.Arrays;

public class Solution_1 {

    /**
     * 64. <a href="https://leetcode.cn/problems/minimum-path-sum/description/">最小路径和</a>
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        /*
        给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
        每次只能向下或者向右移动一步。
         */
        /*
        定义 dfs(m-1, n-1) 表示 grid 从 (0, 0) 到 (m-1, n-1) 最小路径和。
        每次只能向下或者向右移动一步。分类讨论：
            从左边 (m-1, n-2) 过来，需要知道 dfs(m-1, n-2) 的最小路径和，从左边过来路径和为：dfs(m-1, n-2) + grid[i][j]
            从上边 (m-2, n-1) 过来，需要知道 dfs(m-2, n-1) 的最小路径和，从上边过来路径和为：dfs(m-2, n-1) + grid[i][j]
        递归的参数只跟 (i, j) 有关，参数不变，多次调用不会影响结果，下次访问就可以不用递归调用
         */
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(m - 1, n - 1, grid, memo);
    }

    public int dfs(int i, int j, int[][] grid, int[][] memo) {
        if (i < 0 || j < 0) return Integer.MAX_VALUE;
        if (i == 0 && j == 0) return grid[0][0];
        if (memo[i][j] != -1) return memo[i][j];
        return memo[i][j] = Math.min(dfs(i, j - 1, grid, memo), dfs(i - 1, j, grid, memo)) + grid[i][j];
    }

    public int minPathSum_dp(int[][] grid) {
        /*
        dfs(i, j) = min(dfs(i-1, j), dfs(i, j-1)) + grid[i][j];
        翻译成递推
        f[i][j] = min(f[i-1][j], f[i][j-1]) + grid[i][j]
        为了避免负数下标，f[i+1][j+1] 表示 (0, 0) 到 (i, j) 最小路径和
        f[i+1][j+1] = min(f[i][j+1], f[i+1][j]) + grid[i][j], f[m][n] 就是答案，
        初始化 f[m+1][n+1]
        第一行 f[0] = Integer.MAX_VALUE; 第一列 f[i][0] = Integer.MAX_VALUE
         */
        /*
        空间优化：
        计算 (0, 0) 到 (i, j) 的最小路径和是，只会用到 f[i][j+1] 和 f[i+1][j]。
            f[i][j+1] 是上一行当前列，
            f[i+1][j] 是当前行前一列，
            直接把这些合并到一行记录，当前位置左边 f[i+1][j]，即 f[j]，上一行当前列 f[i][j+1]，即 f[j+1]
            f[j+1] = min(f[j], f[j+1]) + row[j]
         */
        /*
        原地修改：
        f[i][j] = min(f[i-1][j], f[i][j-1]) + grid[i][j]
        需要特殊处理 i = 0 或 j = 0 特殊行和特殊列
         */
        int m = grid.length, n = grid[0].length;
        int[] f = grid[0];
        for (int j = 1; j < n; j++) {
            f[j] += f[j - 1];
        }
        for (int i = 1; i < m; i++) {
            f[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                f[j] = Math.min(f[j], f[j - 1]) + grid[i][j];
            }
        }
        return f[n - 1];
    }
}
