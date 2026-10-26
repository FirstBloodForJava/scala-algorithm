package com.oycm.month2026.october;

public class Solution_25 {

    /**
     * 1254. <a href="https://leetcode.cn/problems/number-of-closed-islands/description/">统计封闭岛屿的数目</a> 1659
     *
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        /*
        二维矩阵 grid 由 0 （土地）和 1 （水）组成。
        岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由 1 包围（左、上、右、下）的岛。
         */
        /*
        如果 0 能访问到边界，那个这个岛一定不是封闭的，否则，该岛屿就是封闭的。
        可以先把 不封闭的岛屿 标记为 1，再计算岛屿数量，就是答案
         */
        int m = grid.length, n = grid[0].length;
        if (m < 3 || n < 3) {
            return 0;
        }
        for (int i = 0; i < m; i++) {
            int step = i == 0 || i == m - 1 ? 1 : n - 1;
            for (int j = 0; j < n; j += step) {
                dfs(grid, i, j);
            }
        }
        int ans = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    // 此时一定是封闭岛屿
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    public static int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 0) {
            return;
        }
        grid[i][j] = 1;
        for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }

}
