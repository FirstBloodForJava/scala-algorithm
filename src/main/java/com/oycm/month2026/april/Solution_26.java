package com.oycm.month2026.april;

public class Solution_26 {

    /**
     * 1559. <a href="https://leetcode.cn/problems/detect-cycles-in-2d-grid/description/">二维网格图中探测环</a> 1838
     *
     * @param grid
     * @return
     */
    public boolean containsCycle(char[][] grid) {
        /*
        对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 相同的值 。
        一个环是一条开始和结束于同一个格子的长度 大于等于 4 的路径。
         */
        /*
        dfs 过程中，会标记访问过的各自，该怎么判断是否可以形成环？
        题解思路：规定不能立刻回头，记录上一个位置的坐标，并禁止移动到上一步
         */
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 剪枝优化：相同字符前面访问过，形成不了环，继续访问也是一样
                if (!vis[i][j] && dfs(i, j, -1, -1, grid, vis)) {
                    return true;
                }
            }
        }

        return false;
    }

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean dfs(int i, int j, int fi, int fj, char[][] grid, boolean[][] vis) {
        vis[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if ((x != fi || y != fj) &&
                    0 <= x && x < grid.length && 0 <= y && y < grid[i].length &&
                    grid[i][j] == grid[x][y] &&
                    (vis[x][y] || dfs(x, y, i, j, grid, vis))
            ) {
                return true;
            }
        }
        return false;
    }
}
