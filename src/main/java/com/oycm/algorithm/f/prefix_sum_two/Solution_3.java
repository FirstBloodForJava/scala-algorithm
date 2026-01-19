package com.oycm.algorithm.f.prefix_sum_two;

public class Solution_3 {

    /**
     * 3070. 元素和小于等于 k 的子矩阵的数目 1499
     *
     * @param grid
     * @param k
     * @return 包含 grid 左上角元素、元素和小于或等于 k 的 子矩阵的数目
     */
    public int countSubmatrices(int[][] grid, int k) {
        /*
        本质求矩阵前缀和小于等于 k 的数量
         */
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
                if (s[i + 1][j + 1] <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
