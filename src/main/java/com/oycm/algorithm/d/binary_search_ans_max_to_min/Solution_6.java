package com.oycm.algorithm.d.binary_search_ans_max_to_min;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_6 {

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        /*
        给你一个下标从 0 开始、大小为 n x n 的二维矩阵 grid ，其中 (r, c) 表示：
            如果 grid[r][c] = 1 ，则表示一个存在小偷的单元格
            如果 grid[r][c] = 0 ，则表示一个空单元格
        你最开始位于单元格 (0, 0) 。在一步移动中，你可以移动到矩阵中的任一相邻单元格，包括存在小偷的单元格。
        矩阵中路径的 安全系数 定义为：从路径中任一单元格到矩阵中任一小偷所在单元格的 最小 曼哈顿距离。
        返回所有通向单元格 (n - 1, n - 1) 的路径中的 最大安全系数。
        单元格 (r, c) 的某个 相邻 单元格，是指在矩阵中存在的 (r, c + 1)、(r, c - 1)、(r + 1, c) 和 (r - 1, c) 之一。
        两个单元格 (a, b) 和 (x, y) 之间的 曼哈顿距离 等于 | a - x | + | b - y | ，其中 |val| 表示 val 的绝对值。
         */
        /*
        只要经过了小偷所在的单元格，根据定义，安全系数为 0。
        可以先预处理所有坐标的 最小 曼哈顿距离。
        由于至少有一个小偷，无论小偷位于何处，安全系数都不会大于 n。
        定义 dis[i][j] 表示 (i, j) 到最近 1 的曼哈顿距离。
        对于 [0, min(dis[0][0], dis[n-1][n-1]] 二分答案，从 (0, 0) 开始移动，看是否能移动到 (n-1, n-1)
         */
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;
        int[][] dis = new int[n][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) > 0) {
                    q.add(new int[]{i, j});
                } else {
                    dis[i][j] = -1;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] < 0) {
                    dis[x][y] = dis[i][j] + 1;
                    q.add(new int[]{x, y});
                }
            }
        }
        int l = 0, r = Math.min(dis[0][0], dis[n - 1][n - 1]);
        while (l <= r) {
            int mid = (l + r) / 2;
            if (check(dis, mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    private final static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean check(int[][] dis, int limit) {
        int n = dis.length;
        boolean[][] vis = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        vis[0][0] = true;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            if (i == n - 1 && j == n - 1) return true;
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (0 <= x && x < n && 0 <= y && y < n && !vis[x][y] && dis[x][y] >= limit) {
                    q.add(new int[]{x, y});
                    vis[x][y] = true;
                }
            }
        }

        return false;
    }
}
