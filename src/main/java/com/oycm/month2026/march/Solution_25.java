package com.oycm.month2026.march;

public class Solution_25 {

    /**
     * 3546. <a href="https://leetcode.cn/problems/equal-sum-grid-partition-i/description/">等和矩阵分割 I</a> 1411
     * <img src = "http://47.101.155.205/image-20260325151708939.png" >
     *
     * @param grid m x n 的正整数 矩阵 grid
     * @return 是否存在 一条水平或一条垂直分割线 将矩阵分割成两部分, 两个部分中所有元素的和 相等
     */
    public boolean canPartitionGrid(int[][] grid) {
        /*
        二维前缀和:
            m - 1 条 横线 分割
            n - 1 条 竖线 分割
        先算总和, 再算行和, 列和
        还可以通过 建一个 row[m] col[n] 的行和, 列和的数组, 减少循环的子树
         */
        int m = grid.length, n = grid[0].length;
        long[] rows = new long[m], cols = new long[n];
        long sum = 0;
        for (int i = 0; i < m; i++) {
            long row = 0;
            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
                row += grid[i][j];
                cols[j] += grid[i][j];
            }
            rows[i] = row;
        }
        // sum 不为 偶数
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum >> 1;
        long row = 0;
        for (int i = 0; i < m - 1; i++) {
            row += rows[i];
            if (row == sum) {
                return true;
            }
        }
        long col = 0;
        for (int j = 0; j < n - 1; j++) {
            col += cols[j];
            if (col == sum) {
                return true;
            }
        }

        return false;
    }

    static class Solution_25_1 {
        public boolean canPartitionGrid(int[][] grid) {
            /*
            先计算总和 total, 再计算每行的总和 是否满足 rowTotal = total - rowTotal
            再把 m * n 矩阵顺时针旋转 90 ° 变成 n * m
             */
            long sum = 0;
            for (int[] row : grid) {
                for (int x : row) {
                    sum += x;
                }
            }

            return check(grid, sum) || check(rotate(grid), sum);
        }

        public boolean check(int[][] grid, long total) {
            long row = 0;
            for (int i = 0; i < grid.length - 1; i++) {
                for (int x : grid[i]) {
                    row += x;
                }
                if (row * 2 == total) {
                    return true;
                }
            }

            return false;
        }

        public int[][] rotate(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] p = new int[n][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    p[j][m - 1 - i] = grid[i][j];
                }
            }
            return p;
        }
    }
}
