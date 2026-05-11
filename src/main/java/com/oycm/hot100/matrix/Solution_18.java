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

    public void setZeroes_optimize1(int[][] matrix) {
        /*
        优化思路：把标记数据汇总到第一行和第一列
        用第一列的 matrix[i][0] 代替 row[i]，如果 i 行有 0，置 matrix[i][0] = 0;
        用第一行的 matrix[0][j] 代替 col[j]，如果 j 列有 0，置 matrix[0][j] = 0;
        再遍历 matrix，如果 matrix[i][0] == 0 或 matrix[0][j] == 0, matrix[i][j] 置为 0
        这样会有一个问题，如果第一行全是 1，第一列包含 0，会置 matrix[0][0] = 0。修改后，根据定义，会无误认为第一行和第一列都包含 0。
        解决思路：
            先记录初始的第一行和第一列是否包含 0，跳过第一行和第一列的处理，遍历完所有后，再单独处理第一行和第一列
         */
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        for (int x : matrix[0]) {
            if (x == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        // 标记
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRowHasZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }

    }

}
