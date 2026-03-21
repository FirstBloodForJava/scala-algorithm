package com.oycm.month2026.march;

public class Solution_21 {

    /**
     * 3643. <a href="https://leetcode.cn/problems/flip-square-submatrix-vertically/description/">垂直翻转子矩阵</a> 1235
     *
     * @param grid 整数矩阵
     * @param x
     * @param y
     * @param k
     * @return
     */
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        /*
        grid[x][y] 左上角, 长为 k 的子矩阵
        垂直翻转子矩阵的 行
         */
        int top = x, bottom = x + k -1;
        while (top < bottom) {
            // 交换行
            for (int j = y; j < y + k; j++) {
                int temp = grid[top][j];
                grid[top][j] = grid[bottom][j];
                grid[bottom][j] = temp;
            }
            top++;
            bottom--;
        }

        return grid;
    }
}
