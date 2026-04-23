package com.oycm.algorithm.i.search;

public class Solution_5 {

    /**
     * 1219. <a href="https://leetcode.cn/problems/path-with-maximum-gold/description/">黄金矿工</a>
     *
     * @param grid m * n 的网格
     * @return
     */
    public int getMaximumGold(int[][] grid) {
        /*
        为了使收益最大化，矿工需要按以下规则来开采黄金：
            每当矿工进入一个单元，就会收集该单元格中的所有黄金。
            矿工每次可以从当前位置向上下左右四个方向走。
            每个单元格只能被开采（进入）一次。
            不得开采（进入）黄金数目为 0 的单元格。
            矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
         */
        /*
        每个 grid[r][c] > 0, dfs 搜索能取到的最大值
         */
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    dfs(i, j, grid, 0);
                }
            }
        }
        return ans;

    }
    int ans = 0;
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void dfs(int i, int j, int[][] grid, int val) {
        int cur = grid[i][j];
        val += cur;
        ans = Math.max(ans, val);
        grid[i][j] = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (0 <= x && x < grid.length && 0 <= y && y < grid[i].length && grid[x][y] > 0) {
                dfs(x, y, grid, val);
            }
        }
        grid[i][j] = cur;
    }
}
