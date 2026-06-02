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

    public int minFallingPathSum_dp(int[][] matrix) {
        /*
        自顶到底的 dfs 转换成 递推，不是很好写，转换成自底到顶
        dfs(i, j) = min(
            dfs(i-1, j-1),
            dfs(i-1, j),
            dfs(i-1, j+1)
        ) + matrix[i][j]
        递归边界
            j < 0 || j >= n，返回无穷大
            i == 0, 返回 matrix[i][j]
        翻译成递推：
        f[i][j] = min(
            f[i-1][j-1],
            f[i-1][j],
            f[i-1][j+1]
        ) + matrix[i][j]
        为了避免负数下标，f[i][j] 定义为 f[i][j+1]
        空间优化，计算 f[i][j+1] 只需要三个变量，上一行当前位置 左中右，中间会覆盖，这一行又会只有左边的数据，所有需要一个临时变量记录左边的数据
         */
        int n = matrix.length;
        int[] f = new int[n + 2];
        System.arraycopy(matrix[0], 0, f, 1, n);
        f[0] = f[n + 1] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int pre = f[0];
            for (int j = 0; j < n; j++) {
                int tmp = pre;
                pre = f[j + 1];
                f[j + 1] = Math.min(Math.min(tmp, f[j + 2]), f[j + 1]) + matrix[i][j];
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, f[i]);
        }
        return ans;
    }

}
