package com.oycm.dp.b.basic;

public class Solution_5 {

    /**
     * 3393. <a href="https://leetcode.cn/problems/count-paths-with-the-given-xor-value/description/">统计异或值为给定值的路径数目</a> 1573
     *
     * @param grid
     * @param k
     * @return
     */
    public int countPathsWithXorValue(int[][] grid, int k) {
        /*
        给你一个大小为 m x n 的二维整数数组 grid 和一个整数 k。
        你的任务是统计满足以下 条件 且从左上格子 (0, 0) 出发到达右下格子 (m - 1, n - 1) 的路径数目：
            每一步你可以向右或者向下走，也就是如果格子存在的话，可以从格子 (i, j) 走到格子 (i, j + 1) 或者格子 (i + 1, j) 。
            路径上经过的所有数字 XOR 异或值必须 等于 k。
         */
        /*
        1 <= m == grid.length <= 300
        1 <= n == grid[r].length <= 300
        0 <= grid[r][c] < 16
        0 <= k < 16
         */
        /*
        f[i][j] = f[i][j-1] + f[i-1][j]
         */
        int mod = 1000000007;
        int m = grid.length;
        int n = grid[0].length;
        int[][][] f = new int[m + 1][n + 1][16];
        int pre = grid[0][0];
        f[1][1][pre] = 1;
        for (int j = 1; j < n; j++) {
            f[1][j + 1][pre ^ grid[0][j]] = f[1][j][pre];
            pre ^= grid[0][j];

        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = 0; x < 16; x++) {
                    f[i + 1][j + 1][x ^ grid[i][j]] = (f[i + 1][j][x] + f[i][j + 1][x]) % mod;
                }
            }
        }

        return f[m][n][k];
    }

}
