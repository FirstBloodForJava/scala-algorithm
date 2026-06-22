package com.oycm.week.lc2024.No394;

import java.util.Arrays;

public class Solution_3 {

    /**
     * 3122. <a href="https://leetcode.cn/problems/minimum-number-of-operations-to-satisfy-conditions/description/">使矩阵满足条件的最少操作次数</a> 1905
     *
     * @param grid 1 <= n, m <= 1000; 0 <= grid[i][j] <= 9
     * @return
     */
    public int minimumOperations(int[][] grid) {
        /*
        给你一个大小为 m x n 的二维矩形 grid 。
        每次 操作 中，你可以将 任一 格子的值修改为 任意 非负整数。
        完成所有操作后，你需要确保每个格子 grid[i][j] 的值满足：
            如果下面相邻格子存在的话，它们的值相等，也就是 grid[i][j] == grid[i + 1][j]（如果存在）。
            如果右边相邻格子存在的话，它们的值不相等，也就是 grid[i][j] != grid[i][j + 1]（如果存在）。
         */
        /*
        每列值都相同，相邻列值不同
        dfs(i, c) 表示第 i+1 列值为 c
         */
        int m = grid.length;
        int n = grid[0].length;

        // cnt[i][j] 表示第 i 列修改为 j 的操作次数
        int[][] cnt = new int[n][10];
        for (int[] row : cnt) {
            Arrays.fill(row, m);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cnt[j][grid[i][j]]--;
            }
        }
        int[][] f = new int[n][10];
        for (int i = 0; i < 10; i++) {
            f[0][i] = cnt[0][i];
        }
        int ans = n * m;
        for (int j = 1; j < n; j++) {

            for (int i = 0; i < 10; i++) {
                int mn = ans;
                for (int c = 0; c < 10; c++) {
                    // 枚举前一列不为 j 的修改最少次数
                    if (c != i) {
                        mn = Math.min(mn, f[j - 1][c]);
                    }
                }
                f[j][i] = mn + cnt[j][i];
            }
        }


        for (int x : f[n - 1]) {
            ans = Math.min(x, ans);
        }
        return ans;
    }

    /*int[][] grid;
    int[][] memo;
    int[][] cnt;

    public int dfs(int i, int c) {
        if (i < 0) return 0;


        return cnt[i][c] + dfs(i-1, c);
    }*/

}
