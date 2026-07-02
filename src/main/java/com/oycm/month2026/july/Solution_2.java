package com.oycm.month2026.july;

import java.util.*;

public class Solution_2 {

    /**
     * 3286. 穿越网格图的安全路径
     * <br>
     * 3286. <a href="https://leetcode.cn/problems/find-a-safe-walk-through-a-grid/description/">穿越网格图的安全路径</a> 1608
     *
     * @param grid
     * @param health
     * @return
     */
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        /*
        给你一个 m x n 的二进制矩形 grid 和一个整数 health 表示你的健康值。
        你开始于矩形的左上角 (0, 0) ，你的目标是矩形的右下角 (m - 1, n - 1) 。
        你可以在矩形中往上下左右相邻格子移动，但前提是你的健康值始终是 正数。
        对于格子 (i, j) ，如果 grid[i][j] = 1 ，那么这个格子视为 不安全 的，会使你的健康值减少 1。
        如果你可以到达最终的格子，请你返回 true ，否则返回 false。
        注意 ，当你在最终格子的时候，你的健康值也必须为 正数。
         */
        /*
        以 (0, 0) 开始，做一个多 health bfs 搜索，看到达 (m-1, n-1) 时，health 是否大于 0
         */
        /*int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.size();
        int n = grid.get(0).size();
        List<int[]> q = new ArrayList<>();
        q.add(new int[]{0, 0, health + (grid.get(0).get(0) == 1 ? -1 : 0)});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        while (!q.isEmpty()) {
            List<int[]> temp = q;
            q = new ArrayList<>();
            for (int[] p : temp) {
                int i = p[0], j = p[1], h = p[2];
                if (i == m - 1 && j == n - 1 && h > 0) return true;
                for (int[] d : dirs) {
                    int x = i + d[0], y = j + d[1];
                    if (0 <= x && x < m && 0 <= y && y < n && !visited[x][y] && h - grid.get(x).get(y) > 0) {
                        visited[x][y] = true;
                        q.add(new int[]{x, y, h - grid.get(x).get(y)});
                    }
                }
            }
            // 先走 h 更大的
            q.sort((a, b) -> b[2] - a[2]);
        }

        return false;*/
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.size();
        int n = grid.get(0).size();
        Integer[][] a = new Integer[m][];
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            a[i] = grid.get(i).toArray(new Integer[0]);
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        dis[0][0] = a[0][0];
        Deque<int[]> q = new ArrayDeque<>();
        q.addFirst(new int[]{0, 0});
        while (true) {
            int[] p = q.pollFirst();
            int i = p[0], j = p[1];
            if (dis[i][j] >= health) return false;

            if (i == m - 1 && j == n - 1) return true;
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (0 <= x && x < m && 0 <= y && y < n) {
                    int cost = a[x][y];
                    if (dis[i][j] + cost < dis[x][y]) {
                        dis[x][y] = dis[i][j] + cost;
                        if (cost == 0) {
                            q.addFirst(new int[]{x, y});
                        } else {
                            q.addLast(new int[]{x, y});
                        }
                    }
                }
            }
        }
    }

}
