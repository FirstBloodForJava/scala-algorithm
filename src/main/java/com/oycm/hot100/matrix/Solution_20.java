package com.oycm.hot100.matrix;

public class Solution_20 {

    /**
     * 48. <a href="https://leetcode.cn/problems/rotate-image/description/">旋转图像</a>
     *
     * @param matrix n × n 的二维矩阵 matrix
     */
    public void rotate(int[][] matrix) {
        /*
        将图像顺时针旋转 90 度结果，原地修改
         */
        /*
        横着看，第 i 行去了第 n-1-i 列
        竖着看，第 j 列去了第 j 行
        (i, j) => (j, n-1-i)
        (i, j) => (j, i) => (j,n-1-i)
        (i, j) => (j, i) 沿对角线翻转
        (j, i) => (j,n-1-i) 行翻转
         */
        int n = matrix.length;
        // 沿对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 行翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n-1-j] = temp;
            }
        }

    }

}
