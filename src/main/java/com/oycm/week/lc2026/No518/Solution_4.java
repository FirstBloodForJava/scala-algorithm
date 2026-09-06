package com.oycm.week.lc2026.No518;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_4 {

    /**
     * 至多 K 次转向的最小路径代价
     *
     * @param grid
     * @param k
     * @return
     */
    public int minCost(int[][] grid, int k) {
        /*
        给你一个大小为 m x n 的二维整数数组 grid，其中 grid[i][j] 表示访问单元格 (i, j) 的代价，另给你一个整数 k。
        你从左上角单元格 (0, 0) 出发，目标是到达右下角单元格 (m - 1, n - 1)。
        在每个单元格中，你可以向四个方向之一移动一步：上、下、左或右。
        路径的代价是所访问的所有单元格的值之和，包括起始单元格和目标单元格。如果一个单元格被多次访问，其值每次被访问时都会计入。
        返回在至多进行 k 次转向的情况下，到达 (m - 1, n - 1) 的最小可能路径代价。如果不存在这样的路径，返回 -1。
        当两次连续移动之间的方向发生改变时，就发生了一次转向。例如，先向右移动再向下移动算作一次转向，而连续向右移动则不算转向。
         */
        /*

         */
        int m = grid.length;
        int n = grid[0].length;
        if (k == 0 && m != 1 && n != 1) {
            return -1;
        }
        k = Math.min(k, m * n - 1);
        int[][][][] memo = new int[m][n][4][k + 1];
        for (int[][][] ints : memo) {
            for (int[][] mat : ints) {
                for (int[] row : mat) {
                    Arrays.fill(row, Integer.MAX_VALUE / 10);
                }
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int d = 0; d < 4; d++) {
            // 四个方向
            memo[0][0][d][0] = grid[0][0];
            pq.add(new int[]{grid[0][0], 0, 0, d, 0});
        }
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int i = cur[1], j = cur[2], d = cur[3], t = cur[4];
            // 不是最优到达，跳过
            if (cost != memo[i][j][d][t]) {
                continue;
            }
            for (int nd = 0; nd < 4; nd++) {
                int ni = i + ndRS[nd][0], nj = j + ndRS[nd][1];
                if (ni < 0 || ni >= m || nj < 0 || nj >= n) {
                    continue;
                }
                int nk = (nd == d) ? t : t + 1;
                if (nk > k) continue;
                int nc = cost + grid[ni][nj];
                if (nc < memo[ni][nj][nd][nk]) {
                    memo[ni][nj][nd][nk] = nc;
                    pq.add(new int[]{nc, ni, nj, nd, nk});
                }
            }
        }
        int ans = Integer.MAX_VALUE;

        for (int d = 0; d < 4; d++)
            for (int t = 0; t <= k; t++)
                ans = Math.min(ans, memo[m - 1][n - 1][d][t]);
        return ans;

    }

    private final static int[][] ndRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


}
