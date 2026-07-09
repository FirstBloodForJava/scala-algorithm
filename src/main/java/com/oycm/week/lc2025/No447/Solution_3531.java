package com.oycm.week.lc2025.No447;

import java.util.Arrays;

public class Solution_3531 {

    /**
     * 3531. 统计被覆盖的建筑
     * <br>
     * 3531. <a href="https://leetcode.cn/problems/count-covered-buildings/description/">统计被覆盖的建筑</a> 1519
     *
     * @param n
     * @param buildings
     * @return
     */
    public int countCoveredBuildings(int n, int[][] buildings) {
        /*
        给你一个正整数 n，表示一个 n x n 的城市，同时给定一个二维数组 buildings，其中 buildings[i] = [x, y] 表示位于坐标 [x, y] 的一个 唯一 建筑。
        如果一个建筑在四个方向（左、右、上、下）中每个方向上都至少存在一个建筑，则称该建筑 被覆盖 。
        返回 被覆盖 的建筑数量。
         */
        /*
        相同 x 的建筑，按 y 升序排序；
        相同 y 的建筑，按 x 升序排序；
        不需要分组排序，只统计每行每列的最小值，最大值
         */
        int[] rowMin = new int[n + 1];
        int[] rowMax = new int[n + 1];
        int[] colMin = new int[n + 1];
        int[] colMax = new int[n + 1];
        Arrays.fill(rowMin, n + 1);
        Arrays.fill(colMin, n + 1);
        for (int[] p : buildings) {
            int x = p[0], y = p[1];
            rowMin[x] = Math.min(rowMin[x], y);
            rowMax[x] = Math.max(rowMax[x], y);

            colMin[y] = Math.min(colMin[y], x);
            colMax[y] = Math.max(colMax[y], x);
        }
        int ans = 0;
        for (int[] p : buildings) {
            int x = p[0], y = p[1];
            if (rowMin[x] < y && y < rowMax[x] && colMin[y] < x && x < colMax[y]) ans++;
        }
        return ans;
    }

}
