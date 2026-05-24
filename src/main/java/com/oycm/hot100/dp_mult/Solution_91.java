package com.oycm.hot100.dp_mult;

public class Solution_91 {

    /**
     * 62. <a href="https://leetcode.cn/problems/unique-paths/description/">不同路径</a>
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        /*
        一个机器人位于一个 m x n 网格的左上角 (0, 0)。
        机器人每次只能 向下或者向右 移动一步。机器人试图达到网格的右下角 (m-1, n-1)。
        问总共有多少条不同的路径？
         */
        /*
        dfs(i, j) 表示从 (0, 0) 到 i, j 所有的不同路径和
            当前 (i, j) 可以从 (i-1, j), (i, j-1) 到达，所以：
            dfs(i, j) = dfs(i-1, j) + dfs(i, j-1)
        递归边界 i = 0 || j = 0 返回 1，因为第 0 行和第 0 列，只能向右，向下移到到达，且只有一种路径
         */
        int[][] memo = new int[m][n];

        return dfs(m - 1, n - 1, memo);
    }

    public int dfs(int i, int j, int[][] memo) {
        if (i == 0 || j == 0) {
            return 1;
        }
        if (memo[i][j] > 0) return memo[i][j];

        return memo[i][j] = dfs(i - 1, j, memo) + dfs(i, j - 1, memo);
    }

}
