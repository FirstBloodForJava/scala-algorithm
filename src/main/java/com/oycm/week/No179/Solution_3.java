package com.oycm.week.No179;

public class Solution_3 {

    public int minCost(int[][] grid) {
        /*
        异或路径最小，不能和求乘法一样, 记录 最小最大值
         */
        int m = grid.length, n = grid[0].length;

        // 0 <= grid[i][j] <= 1023
        int MAX = 1 << 10;
        boolean[][][] dp = new boolean[m][n][MAX];

        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                for (int x = 0; x < MAX; x++) {
                    if (i > 0 && dp[i - 1][j][x]) {
                        dp[i][j][x ^ grid[i][j]] = true;
                    }

                    if (j > 0 && dp[i][j - 1][x]) {
                        dp[i][j][x ^ grid[i][j]] = true;
                    }
                }
            }
        }

        for (int x = 0; x < MAX; x++) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}
