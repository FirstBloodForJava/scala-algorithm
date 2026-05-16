package com.oycm.hot100.graph;

import java.util.ArrayList;
import java.util.List;

public class Solution_52 {

    /**
     * 994. <a href="https://leetcode.cn/problems/rotting-oranges/description/">腐烂的橘子</a> 1433
     *
     * @param grid m x n 网格
     * @return
     */
    public int orangesRotting(int[][] grid) {
        /*
        m x n 网格 grid 中，每个单元格可以有以下三个值之一：
            值 0 代表空单元格；
            值 1 代表新鲜橘子；
            值 2 代表腐烂的橘子。
        每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
         */
        /*
        bfs，找出最开始腐烂的橘子，满足以下条件，则加入下一次腐烂
            未越界，到达的是新鲜橘子，加入下一轮 bfs
         */
        int goods = 0;
        int m = grid.length, n = grid[0].length;
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    goods++;
                } else if (grid[i][j] == 2) {
                    q.add(new int[]{i, j});
                }
            }
        }
        int cnt = 0;
        if (goods == 0) {
            return cnt;
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!q.isEmpty()) {
            List<int[]> t = new ArrayList<>();
            for (int[] cord : q) {
                for (int[] dir : dirs) {
                    int x = cord[0] + dir[0];
                    int y = cord[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        t.add(new int[]{x, y});
                        goods--;
                    }
                }
            }
            if (!t.isEmpty()) {
                cnt++;
            }
            q = t;
        }

        return goods > 0 ? -1 : cnt;
    }

}
