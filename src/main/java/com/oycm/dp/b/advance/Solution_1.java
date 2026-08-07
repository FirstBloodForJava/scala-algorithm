package com.oycm.dp.b.advance;

public class Solution_1 {

    /**
     * 1594. <a href="https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix/description/">矩阵的最大非负积</a> 1807
     *
     * @param grid
     * @return
     */
    public int maxProductPath(int[][] grid) {
        /*
        给你一个大小为 m x n 的矩阵 grid 。
        最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
        在从左上角 (0, 0) 开始到右下角 (m - 1, n - 1) 结束的所有路径中，找出具有 最大非负积 的路径。
        路径的积是沿路径访问的单元格中所有整数的乘积。
        返回 最大非负积 对 1e9 + 7 取余 的结果。如果最大积为 负数 ，则返回 -1 。
        注意，取余是在得到最大积之后执行的。
         */
        /*
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 15
        -4 <= grid[i][j] <= 4
         */
        /*
        f[i][j][2] 表示从 (0, 0) 到 (i, j) 最小值和最大值
            (i, j) 可以从 (i-1, j) (i, j-1) 过来
         */
        int m = grid.length;
        int n = grid[0].length;
        long[] fMin = new long[n];
        long[] fMax = new long[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                // 递归边界，递归初始条件
                if (i == 0 && j == 0) {
                    fMin[j] = fMax[j] = x;
                    continue;
                }
                long resMin = Long.MAX_VALUE;
                long resMax = Long.MIN_VALUE;

                if (i > 0) {
                    // 从 (i-1, j) 移动到 (i, j) 向下，计算新的最小值，最大值
                    long mn = fMin[j] * x, mx = fMax[j] * x;
                    resMin = Math.min(mn, mx);
                    resMax = Math.max(mn, mx);
                }
                if (j > 0) {
                    // 从 (i, j-1) 移动到 (i, j) 向右，计算新的最小值，最大值
                    long mn = fMin[j - 1] * x, mx = fMax[j - 1] * x;
                    resMin = Math.min(resMin, Math.min(mn, mx));
                    resMax = Math.max(resMax, Math.max(mn, mx));
                }

                fMin[j] = resMin;
                fMax[j] = resMax;
            }
        }

        return fMax[n - 1] < 0 ? -1 : (int) (fMax[n - 1] % 1000000007);
    }


}
