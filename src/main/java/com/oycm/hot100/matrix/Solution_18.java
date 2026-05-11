package com.oycm.hot100.matrix;

public class Solution_18 {

    /**
     * 73. <a href="https://leetcode.cn/problems/set-matrix-zeroes/description/">矩阵置零</a>
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        /*
        给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
         */
        /*
        长为 m, n 的一维数组，标记哪些行或列需要置为 0
         */
        int m = matrix.length, n = matrix[0].length;
        int[] row = new int[m];
        int[] col = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] != 0 || col[j] != 0) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

}
