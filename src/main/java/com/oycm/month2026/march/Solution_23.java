package com.oycm.month2026.march;

import com.oycm.DataCreateUtils;

public class Solution_23 {

    /**
     * 1594. <a href="https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix/description/">矩阵的最大非负积</a> 1807
     *
     * @param grid m x n 的矩阵 1 <= m, n <= 15, -4 <= grid[i][j] <= 4
     * @return
     */
    public int maxProductPath(int[][] grid) {
        /*
        位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动
        从左上角 (0, 0) 开始到右下角 (m - 1, n - 1) 结束的所有路径中，找出具有 最大非负积 的路径
         */
        /*
        long prod[][][] = new long[m][n][2];prod[i][j] 表示 (0,0) 到 (i,j) 路径的最大积 最小值/最大值
         */
        int m = grid.length, n = grid[0].length;
        long prod[][][] = new long[m][n][2];
        prod[0][0][0] = grid[0][0];
        prod[0][0][1] = grid[0][0];
        for (int i = 1; i < n; i++) {
            prod[0][i][0] = prod[0][i - 1][0] * grid[0][i];
            prod[0][i][1] = prod[0][i][0];
        }
        for (int i = 1; i < m; i++) {
            prod[i][0][0] = prod[i - 1][0][0] * grid[i][0];
            prod[i][0][1] = prod[i][0][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] < 0) {
                    // 最小值
                    prod[i][j][0] = Math.min(grid[i][j] * prod[i - 1][j][1], grid[i][j] * prod[i][j - 1][1]);
                    // 最大值
                    prod[i][j][1] = Math.max(grid[i][j] * prod[i - 1][j][0], grid[i][j] * prod[i][j - 1][0]);
                } else if (grid[i][j] > 0) {
                    // 最小值
                    prod[i][j][0] = Math.min(grid[i][j] * prod[i - 1][j][0], grid[i][j] * prod[i][j - 1][0]);
                    // 最大值
                    prod[i][j][1] = Math.max(grid[i][j] * prod[i - 1][j][1], grid[i][j] * prod[i][j - 1][1]);
                } else {
                    prod[i][j][0] = 0;
                    prod[i][j][1] = 0;
                }

            }
        }

        return prod[m - 1][n - 1][1] < 0 ? -1 : (int) (prod[m - 1][n - 1][1] % MOD);
    }

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println(new Solution_23().maxProductPath(DataCreateUtils.twoDimensionInts("[[1,4,4,0],[-2,0,0,1],[1,-1,1,1]]")));
    }

}
