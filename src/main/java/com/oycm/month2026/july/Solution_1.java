package com.oycm.month2026.july;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 2812. 找出最安全路径
     * <br>
     * 2812. <a href="https://leetcode.cn/problems/find-the-safest-path-in-a-grid/description/">找出最安全路径</a> 2154
     *
     * @param grid
     * @return
     */
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
        可以先预处理所有坐标的 最小 曼哈顿距离，dfs 求最大化最小值时间复杂度是 4^n
         */
        /*
        题解思路：多源 BFS + 倒序枚举答案 + 并查集
        dis[i][j] 是单元格 (i, j) 到最近的 1 的曼哈顿距离。
         */
        int n = grid.size();
        int[][] dis = new int[n][n];
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) > 0) {
                    // 1 作为源
                    q.add(new int[]{i, j});
                } else {
                    dis[i][j] = -1;
                }
            }
        }
        List<List<int[]>> groups = new ArrayList<>();
        groups.add(q);
        while (!q.isEmpty()) {
            List<int[]> temp = q;
            q = new ArrayList<>();
            for (int[] p : temp) {
                for (int[] d : DIRS) {
                    int x = p[0] + d[0], y = p[1] + d[1];
                    // dis[x][y] < 0 第一次能访问的，肯定是最小距离
                    if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] < 0) {
                        dis[x][y] = groups.size();
                        q.add(new int[]{x, y});
                    }
                }
            }
            groups.add(q);
        }
        // 初始化并查集
        fa = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            fa[i] = i;
        }
        for (int ans = groups.size() - 2; ans > 0; ans--) {
            for (int[] p : groups.get(ans)) {
                int i = p[0], j = p[1];
                for (int[] d : DIRS) {
                    int x = i + d[0], y = j + d[1];
                    if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] >= ans) {
                        // d 合并到 d+1
                        fa[find(i * n + j)] = find(x * n + y);
                    }
                }
            }
            // 在一个连通块
            if (find(0) == find(n * n - 1)) {
                return ans;
            }

        }

        return 0;
    }

    private final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int[] fa;

    private int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }

}
