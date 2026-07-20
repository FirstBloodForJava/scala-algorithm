package com.oycm.month2026.july;

import java.util.ArrayList;
import java.util.List;

public class Solution_20 {

    /**
     * 1260. <a href="https://leetcode.cn/problems/shift-2d-grid/description/">二维网格迁移</a> 1337
     *
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        /*
        给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
        每次「迁移」操作将会引发下述活动：
            位于 grid[i][j]（j < n - 1）的元素将会移动到 grid[i][j + 1]。
            位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
            位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
         */
        /*
        二维数组转换成一维数组，一维数组向右移动 k 次，再转换成二维数组。反转三次
        计算二维数组新的位置
         */
        int m = grid.length, n = grid[0].length;
        int size = m * n;
        k %= size;
        reverse(grid, n, 0, size - 1);
        reverse(grid, n, 0, k - 1);
        reverse(grid, n, k, size - 1);

        List<List<Integer>> ans = new ArrayList<>();
        for (int[] row : grid) {
            List<Integer> list = new ArrayList<>();
            for (int x : row) {
                list.add(x);
            }
            ans.add(list);
        }
        return ans;
    }

    public void reverse(int[][] grid, int n, int l, int r) {
        while (l < r) {
            int i1 = l / n;
            int j1 = l % n;
            int i2 = r / n;
            int j2 = r % n;
            int temp = grid[i1][j1];
            grid[i1][j1] = grid[i2][j2];
            grid[i2][j2] = temp;
            l++;
            r--;
        }
    }

}
