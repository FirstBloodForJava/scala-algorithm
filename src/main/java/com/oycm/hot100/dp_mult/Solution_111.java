package com.oycm.hot100.dp_mult;

public class Solution_111 {

    /**
     * 221. <a href="https://leetcode.cn/problems/maximal-square/description/">最大正方形</a>
     *
     * @param matrix 仅包含 0 或 1 的矩阵
     * @return 只包含 1 的最大正方形面积
     */
    public int maximalSquare(char[][] matrix) {
        /*
        在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
         */
        /*
        动态规划：
        设右下角在 (i, j) 全是 1 的正方形最大变成为 f[i][j]，在计算 f[i][j] 时，
        以及计算了 f[i-1][j], f[i][j-], f[i-1][j-1]，发现只有取最短边 + 1 就是 f[i][j]
         */
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i][j + 1], dp[i + 1][j])) + 1;
                    ans = Math.max(ans, dp[i + 1][j + 1]);
                }
            }
        }
        return ans * ans;
    }

    public int maximalSquare_optimize(char[][] matrix) {
        /*
        空间优化
        f[i+1][j+1] 计算只用到
            f[i][j]   上一行结果
            f[i][j+1] 上一行结果
            f[i+1][j] 当前行计算的
         */
        int ans = 0;
        int n = matrix[0].length;
        int[] f = new int[n + 1];
        for (char[] row : matrix) {
            int pre = 0;
            for (int j = 0; j < n; j++) {
                int temp = f[j + 1];
                if (row[j] == '1') {
                    f[j + 1] = Math.min(pre, Math.min(f[j], f[j + 1])) + 1;
                    ans = Math.max(ans, f[j + 1]);
                } else {
                    // 这里的 pre 可以不设置 temp，因为这里 f[j+1] = 0，最小值一定是 0
                    f[j + 1] = 0;
                }
                pre = temp;
            }
        }

        return ans * ans;
    }

}
