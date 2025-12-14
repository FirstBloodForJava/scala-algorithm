package com.oycm.algorithm.f.two;

public class Solution_1 {


}

/**
 * 304. <a href="https://leetcode.cn/problems/range-sum-query-2d-immutable/description/">二维区域和检索 - 矩阵不可变</a>
 *
 */
class NumMatrix {
    int[][] sums;

    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        sums = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sums[i + 1][j + 1] = sums[i + 1][j] + sums[i][j + 1] - sums[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int r1, int c1, int r2, int c2) {
        return sums[r2 + 1][c2 + 1] - sums[r1][c2 + 1] - sums[r2 + 1][c1] + sums[r1][c1];
    }
}
