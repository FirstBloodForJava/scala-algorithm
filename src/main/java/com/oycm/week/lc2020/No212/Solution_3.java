package com.oycm.week.lc2020.No212;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_3 {

    /**
     * 1631. 最小体力消耗路径
     * <br>
     * 1631. <a href="https://leetcode.cn/problems/path-with-minimum-effort/description/">最小体力消耗路径</a> 1948
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        /*
        你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。
        一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1)。
        你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
        一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
        请你返回从左上角走到右下角的最小 体力消耗值。
         */
        /*
        求最小化的最大值。
        消耗的体力值越大，越能到达终点。二分答案：
            如果 mid 体力值能到达终点，则说明体力值比较大，减小体力值查找
            如果 mid 体力值不能到达终点，则说明体力值比较小，增大体力值查找
            直到 mid 减少 1 无法到达终点，次数就是最小化的最大值
         */
        int l = -1, r = 1000000;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(heights, mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    private final static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean check(int[][] heights, int limit) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] vis = new boolean[m][n];
        vis[0][0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            if (i == m - 1 && j == n - 1) return true;
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (0 <= x && x < m && 0 <= y && y < n && !vis[x][y] && Math.abs(heights[x][y] - heights[i][j]) <= limit) {
                    vis[x][y] = true;
                    q.add(new int[]{x, y});
                }
            }
        }

        return false;
    }

}
