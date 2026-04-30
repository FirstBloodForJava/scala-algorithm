package com.oycm.month2026.april;

import java.util.Arrays;

public class Solution_30 {

    /**
     * 3742. <a href="https://leetcode.cn/problems/maximum-path-score-in-a-grid/description/">网格中得分最大的路径</a> 1804
     *
     * @param grid m x n 的网格, grid[i][j] 值为 0、1 或 2
     * @param k
     * @return
     */
    public int maxPathScore(int[][] grid, int k) {
        /*
        从左上角 (0, 0) 出发，目标是到达右下角 (m - 1, n - 1)，只能向 右 或 下 移动。
        每个单元格根据其值对路径有以下贡献：
            值为 0 的单元格：分数增加 0，花费 0。
            值为 1 的单元格：分数增加 1，花费 1。
            值为 2 的单元格：分数增加 2，花费 1。
        返回在总花费不超过 k 的情况下可以获得的 最大分数 ，如果不存在有效路径，则返回 -1。
         */
        /*
        可以 dfs 搜索 (0, 0) -> (m - 1, n - 1) 所有路径的分送及花费，花费 <= k 则更新答案
         */
        int m = grid.length;
        int n = grid[0].length;
        int[][][] memo = new int[m][n][k + 1];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        int ans = dfs(m - 1, n - 1, k, grid, memo);
        return ans < 0 ? -1 : ans;
    }

    public int dfs(int i, int j, int k, int[][] grid, int[][][] memo) {
        if (i < 0 || j < 0 || k < 0) return Integer.MIN_VALUE;
        if (i == 0 && j == 0) {
            return 0;
        }
        if (memo[i][j][k] != -1) return memo[i][j][k];
        int x = grid[i][j];
        int newK = x > 0 ? k - 1 : k;
        return memo[i][j][k] = Math.max(dfs(i - 1, j, newK, grid, memo), dfs(i, j - 1, newK, grid, memo)) + x;
    }


}
