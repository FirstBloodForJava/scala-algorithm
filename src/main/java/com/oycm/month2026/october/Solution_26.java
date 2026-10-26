package com.oycm.month2026.october;

public class Solution_26 {

    /**
     * 3619. <a href="https://leetcode.cn/problems/count-islands-with-total-value-divisible-by-k/description/">总价值可以被 K 整除的岛屿数目</a> 1461
     *
     * @param grid
     * @param k
     * @return
     */
    public int countIslands(int[][] grid, int k) {
        /*
        给你一个 m x n 的矩阵 grid 和一个正整数 k。一个 岛屿 是由 正 整数（表示陆地）组成的，并且陆地间 四周 连通（水平或垂直）。
        一个岛屿的总价值是该岛屿中所有单元格的值之和。
        返回总价值可以被 k 整除 的岛屿数量。
         */
        /*
        标记法，同 岛屿数量 题目思路
         */
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && dfs(grid, i, j) % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    private long dfs(int[][] grid, int i, int j) {
        long sum = grid[i][j];
        grid[i][j] = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (0 <= x && x < grid.length && 0 <= y && y < grid[x].length && grid[x][y] > 0) {
                sum += dfs(grid, x, y);
            }

        }
        return sum;
    }

}
