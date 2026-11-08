package com.oycm.month2026.november;

public class Solution_8 {

    /**
     * 1267. <a href="https://leetcode.cn/problems/count-servers-that-communicate/description/">统计参与通信的服务器</a>
     *
     * @param grid
     * @return
     */
    public int countServers(int[][] grid) {
        /*
        这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
        如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
        请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
         */
        /*
        每行统计 1 的个数
        每列统计 1 的个数
         */
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && (rows[i] > 1 || cols[j] > 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
