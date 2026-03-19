package com.oycm.month2026.march;

public class Solution_19 {

    /**
     * 3212. <a href="https://leetcode.cn/problems/count-submatrices-with-equal-frequency-of-x-and-y/description/">统计 X 和 Y 频数相等的子矩阵数量</a> 1673
     * <p>
     * grid[i][j] 可能是 'X'、'Y' 或 '.'
     * 求 满足以下条件的子矩阵数量:
     * 包含 grid[0][0]
     * 'X' 和 'Y' 的频数相等
     * 至少包含一个 'X'
     *
     * @param grid
     * @return
     */
    public int numberOfSubmatrices(char[][] grid) {
        /*
        X-88
        Y-89
        .-46
        和 3070 类似, 两种方式解答
        两个前缀和数组 或 两个维护 列数量的数组
         */
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        int[][] xPre = new int[m + 1][n + 1];
        int[][] yPre = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                xPre[i + 1][j + 1] = xPre[i + 1][j] + xPre[i][j + 1] - xPre[i][j];
                yPre[i + 1][j + 1] = yPre[i + 1][j] + yPre[i][j + 1] - yPre[i][j];
                if ('X' == grid[i][j]) {
                    xPre[i + 1][j + 1]++;
                } else if ('Y' == grid[i][j]) {
                    yPre[i + 1][j + 1]++;
                }
                if (xPre[i + 1][j + 1] > 0 && xPre[i + 1][j + 1] == yPre[i + 1][j + 1]) {
                    ans++;
                }
            }
        }

        return ans;
    }

    static class Solution_19_1 {

        public int numberOfSubmatrices(char[][] grid) {
            /*
            从左到右，从上到下，求 grid 每一列 X, Y 的数量
            题解思路优化, X-88; Y-89, 再加一个二维数组, 和 1 取交集，0 表示 X 数量，1 表示 y 数量
             */
            int ans = 0;
            int n = grid[0].length;
            int[][] cnt = new int[n][2];

            for (char[] row : grid) {
                int xs = 0, ys = 0;
                for (int j = 0; j < n; j++) {
                    if (row[j] != '.') {
                        cnt[j][row[j] & 1]++;
                    }
                    xs += cnt[j][0];
                    ys += cnt[j][1];
                    if (xs > 0 && xs == ys) {
                        ans++;
                    }
                }
            }

            return ans;
        }
    }
}
