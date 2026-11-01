package com.oycm.month2026.october;

public class Solution_31 {

    /**
     * 3665. <a href="https://leetcode.cn/problems/twisted-mirror-path-count/description/">统计镜子反射路径数目</a> 1883
     *
     * @param grid
     * @return
     */
    public int uniquePaths(int[][] grid) {
        int mod = 1000000007;
        int m = grid.length;
        int n = grid[0].length;
        int[][][] f = new int[m + 1][n + 1][2];
        /*
        f[i][j][0] 表示从坐标 (i-1, j-1) 出发，向右移动的路径数量
        f[i][j][1] 表示从坐标 (i-1, j-1) 出发，向下移动的路径数量
         */
        f[0][1][0] = f[0][1][1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // (i, j) = (i, j-1) 向右到达 + (i-1, j) 向下到达
                    // 每月遇到镜子前，除了第一列其它都是 0
                    f[i + 1][j + 1][0] = (f[i + 1][j][0] + f[i][j + 1][1]) % mod;
                    f[i + 1][j + 1][1] = f[i + 1][j + 1][0];
                } else {
                    // (i, j) 向右路径，等价上一个向下方向
                    f[i + 1][j + 1][0] = f[i][j + 1][1];
                    // (i, j) 向下路径，等价上一个向右方向
                    f[i + 1][j + 1][1] = f[i + 1][j][0];
                }
            }
        }

        return f[m][n][0];
    }

}
