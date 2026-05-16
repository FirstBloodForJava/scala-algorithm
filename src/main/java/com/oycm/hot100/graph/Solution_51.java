package com.oycm.hot100.graph;

public class Solution_51 {

    /**
     * 200. <a href="https://leetcode.cn/problems/number-of-islands/description/">岛屿数量</a>
     *
     * @param grid 二维网格图
     * @return
     */
    public int numIslands(char[][] grid) {
        /*
        grid 二维网格图，'1' 表示陆地，'0' 表示水
        计算网格中岛屿的数量。
        岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
        此外，你可以假设该网格的四条边均被水包围。
         */
        /*
        插旗标记法：访问一个岛屿，把访问过的地方，标记为 '2'，这样下次访问后面为 1 的就一定是新岛
        递归边界：超出网格图边界或非陆地
         */
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    // 找到了一个新的岛
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 先判断是否出界, 再判断是否是否为水或已经访问过
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        // 标记访问
        grid[i][j] = '2';
        dfs(grid, i, j - 1); // 往左走
        dfs(grid, i, j + 1); // 往右走
        dfs(grid, i - 1, j); // 往上走
        dfs(grid, i + 1, j); // 往下走
    }

}
