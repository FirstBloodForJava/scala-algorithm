package com.oycm.week.No498;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_3 {

    /**
     * 3905. <a href="https://leetcode.cn/problems/multi-source-flood-fill/description/">多源图像渲染</a>
     * <p>
     * 两个整数 n 和 m，分别表示一个网格的行数和列数
     *
     * @param n       行数
     * @param m       列数
     * @param sources sources[i] = [r, c, color] 表示单元格 (r, c) 初始被涂上颜色 color。所有其他单元格初始均未着色，用 0 表示。
     * @return
     */
    public int[][] colorGrid(int n, int m, int[][] sources) {
        /*
        在每一单位时间中，所有当前已着色的单元格都会将其颜色向上下左右四个方向扩散到所有相邻的 未着色 单元格。所有扩散同时发生。
        如果 多个 颜色在同一时间步到达同一个未着色单元格，该单元格将采用具有 最大 值的颜色。
        这个过程持续进行，直到没有更多的单元格可以被着色。
        返回一个二维整数数组，表示网格的最终状态，其中每个单元格包含其最终的颜色。
         */
        /*
        用一个优先队列记录需要涂色的点，先遍历颜色最大的点，优先遍历未涂色的点
         */
        int[][] mat = new int[n][m];
        Arrays.sort(sources, (a, b) -> b[2] - a[2]);
        List<int[]> q = new ArrayList<>();
        for (int[] source : sources) {
            mat[source[0]][source[1]] = source[2];
            q.add(source);
        }

        while (!q.isEmpty()) {
            List<int[]> next = new ArrayList<>();
            for (int[] row : q) {
                int r = row[0], c = row[1], color = row[2];
                for (int[] dir : dirs) {
                    int x = r + dir[0], y = c + dir[1];
                    if (0 <= x && x < n && 0 <= y && y < m && mat[x][y] == 0) {
                        mat[x][y] = color;
                        next.add(new int[]{x, y, color});
                    }
                }

            }
            q = next;
        }

        return mat;
    }

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
