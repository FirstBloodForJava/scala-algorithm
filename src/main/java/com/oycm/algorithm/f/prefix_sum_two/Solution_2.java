package com.oycm.algorithm.f.prefix_sum_two;

public class Solution_2 {

    /**
     * 1314. <a href="https://leetcode.cn/problems/matrix-block-sum/description/">矩阵区域和</a> 1484
     *
     * @param mat
     * @param k
     * @return
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        /*
        本质是求 矩阵 左上角 max(0, i-k), max(0, j-k), 右下角 (min(m, i+k), min(n, i+k)) 元素和

         */
        int m = mat.length;
        int n = mat[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + mat[i][j];
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r1 = Math.max(0, i - k), c1 = Math.max(0, j - k);
                int r2 = Math.min(m - 1, i + k) + 1, c2 = Math.min(n - 1, j + k) + 1;
                ans[i][j] = s[r2][c2] - s[r2][c1] - s[r1][c2] + s[r1][c1];
            }
        }

        return ans;
    }
}
