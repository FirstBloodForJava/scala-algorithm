package com.oycm.dp.b.basic;

import java.util.Arrays;

public class Solution_6 {

    /**
     * 931. <a href="https://leetcode.cn/problems/minimum-falling-path-sum/description/">下降路径最小和</a> 1573
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        /*
        给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
        下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
        在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
        具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
         */
        /*
        1 <= n == matrix.length == matrix[i].length <= 100
        -100 <= matrix[i][j] <= 100
         */
        /*
        定义 dfs(i, j) 表示从 (i, j) 到达最后一行的路径最小和，
            拆分成子问题 (i+1, j-1), (i+1, j), (i+1, j+1)
        dfs(i, j) = min(
            dfs(i+1, j-1),
            dfs(i+1, j),
            dfs(i+1, j+1)
        ) + matrix[i][j]
         */
        int n = matrix.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dfs(0, i, matrix, memo));
        }
        return ans;
    }

    public int dfs(int i, int j, int[][] matrix, int[][] memo) {
        if (j < 0 || j >= matrix.length) return Integer.MAX_VALUE;
        if (i == matrix.length - 1) return matrix[i][j];
        if (memo[i][j] != Integer.MAX_VALUE) return memo[i][j];
        return memo[i][j] = matrix[i][j] + Math.min(Math.min(dfs(i + 1, j - 1, matrix, memo), dfs(i + 1, j + 1, matrix, memo)),
                dfs(i + 1, j, matrix, memo));
    }

}
