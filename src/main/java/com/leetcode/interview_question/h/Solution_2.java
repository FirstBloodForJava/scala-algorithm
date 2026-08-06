package com.leetcode.interview_question.h;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 面试题 08.02. <a href="https://leetcode.cn/problems/robot-in-a-grid-lcci/description/">迷路的机器人</a>
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        /*
        设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。
        机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
        设计一种算法，寻找机器人从左上角移动到右下角的路径。
        网格中的障碍物和空位置分别用 1 和 0 来表示。
        返回一条可行的路径，路径由经过的网格的行号和列号组成。
         */
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Boolean[][] memo = new Boolean[m][n];
        dfs(0, 0, obstacleGrid, ans, memo);
        return ans;
    }

    public boolean dfs(int i, int j, int[][] grid, List<List<Integer>> ans, Boolean[][] memo) {
        if (i == grid.length || j == grid[0].length || grid[i][j] == 1) {
            return false;
        }
        // 前面到达过该点
        if (memo[i][j] != null) {
            if (memo[i][j]) {
                // 前面范围过，能直接到达
                ans.add(List.of(i, j));
                return true;
            } else {
                return false;
            }
        }

        ans.add(List.of(i, j));
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            // 到达终点
            return true;
        }

        if (dfs(i, j + 1, grid, ans, memo) || dfs(i + 1, j, grid, ans, memo)) {
            memo[i][j] = true;
            return memo[i][j];
        }
        // 回溯
        ans.remove(ans.size() - 1);
        memo[i][j] = false;
        return memo[i][j];

    }

}
