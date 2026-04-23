package com.oycm.algorithm.i.search;

import com.oycm.utils.DataCreateUtils;

public class Solution_7 {

    /**
     * 980. <a href="https://leetcode.cn/problems/unique-paths-iii/description/">不同路径 III</a> 1830
     *
     * @param grid
     * @return
     */
    public int uniquePathsIII(int[][] grid) {
        /*
        在二维网格 grid 上，有 4 种类型的方格：
            1 表示起始方格。且只有一个起始方格。
            2 表示结束方格，且只有一个结束方格。
            0 表示我们可以走过的空方格。
            -1 表示我们无法跨越的障碍。
        返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
        每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
         */
        /*
        每个方格都需要通过一次
         */
        int m = grid.length, n = grid[0].length;
        int x = 0, y = 0;
        int x2 = 0, y2 = 0;
        int k = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                } else if (grid[i][j] == 2) {
                    x2 = i;
                    y2 = j;
                    grid[i][j] = 0;
                    k++;
                } else if (grid[i][j] == 0){
                    k++;
                }
            }
        }
        dfs(x, y, grid, k, x2, y2);
        return ans;
    }

    int ans = 0;

    public void dfs(int i, int j, int[][] grid, int k, int x2, int y2) {
        if (i == x2 && j == y2) {
            if (k == 0) {
                ans++;
            }
            return;
        }
        int temp = grid[i][j];
        grid[i][j] = -1;
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (0 <= x && x < grid.length && 0 <= y && y < grid[0].length && grid[x][y] == 0) {
                dfs(x, y, grid, k - 1,  x2, y2);
            }
        }
        grid[i][j] = temp;
    }

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) {
        Solution_7 solution = new Solution_7();
        System.out.println(solution.uniquePathsIII(DataCreateUtils.twoDimensionInts("[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]")));
    }
}
