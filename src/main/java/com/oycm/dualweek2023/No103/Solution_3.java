package com.oycm.dualweek2023.No103;

public class Solution_3 {

    /**
     * 2658. <a href="https://leetcode.cn/problems/maximum-number-of-fish-in-a-grid/description/">网格图中鱼的最大数目</a> 1490
     *
     * @param grid
     * @return
     */
    public int findMaxFish(int[][] grid) {
        /*
        给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，其中下标在 (r, c) 处的整数表示：
            如果 grid[r][c] = 0 ，那么它是一块 陆地。
            如果 grid[r][c] > 0 ，那么它是一块 水域 ，且包含 grid[r][c] 条鱼。
        一位渔夫可以从任意 水域 格子 (r, c) 出发，然后执行以下操作任意次：
            捕捞格子 (r, c) 处所有的鱼，或者
            移动到相邻的 水域 格子。
         */
        /*
        本质是求哪块水域的鱼最多
        水域访问后，把所有鱼捕捞，标记为 0（陆地）
         */
        int ans = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    ans = Math.max(ans, dfs(i, j, grid));
                }
            }
        }

        return ans;
    }

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int dfs(int i, int j, int[][] grid) {
        int sum = grid[i][j];
        grid[i][j] = 0;

        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] > 0) {
                sum += dfs(x, y, grid);
            }
        }

        return sum;
    }

}
