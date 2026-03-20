package com.oycm.month2026.march;

import java.util.Arrays;

public class Solution_20 {

    /**
     * 3567. <a href="https://leetcode.cn/problems/minimum-absolute-difference-in-sliding-submatrix/description/">子矩阵的最小绝对差</a> 1568
     *
     * @param grid m x n 整数矩阵
     * @param k
     * @return
     */
    public int[][] minAbsDiff(int[][] grid, int k) {
        /*
        求 矩阵 grid 中的每个连续的 k x k 子矩阵，计算其中任意两个 不同值 之间的 最小绝对差
        如果子矩阵中的所有元素都相同，则 最小绝对差 为 0

        暴力: 找出每个子矩阵所有不同元素，排序后 间隔遍历求最小值
        如果 k == 1, 初始化数组就是答案

        时间复杂度 (m - k + 1) * (n - k + 1) * k^2 log k^2
         */
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        if (k == 1) {
            return ans;
        }
        int[] temp = new int[k * k];
        for (int i = 0; i < m - k + 1; i++) {
            for (int j = 0; j < n - k + 1; j++) {
                int idx = 0;
                for (int l = i; l < i + k; l++) {
                    for (int o = j; o < j + k; o++) {
                        temp[idx++] = grid[l][o];
                    }
                }
                Arrays.sort(temp);
                int min = Integer.MAX_VALUE;
                for (int l = 1; l < temp.length; l++) {
                    if (temp[l] > temp[l - 1]) {
                        min = Math.min(min, temp[l] - temp[l - 1]);
                    }
                }

                if (min < Integer.MAX_VALUE) {
                    ans[i][j] = min;
                }

            }
        }
        return ans;
    }

}
