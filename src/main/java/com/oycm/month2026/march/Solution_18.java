package com.oycm.month2026.march;

public class Solution_18 {

    /**
     * 3070. <a href="https://leetcode.cn/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/description/">元素和小于等于 k 的子矩阵的数目</a>
     *
     * @param grid
     * @param k
     * @return
     */
    public int countSubmatrices(int[][] grid, int k) {
        /*
        cols[j] 表示 grid[i][j] 这一列从上到下之和
         */
        int n = grid[0].length;
        int ans = 0;
        int[] cols = new int[n];
        for (int[] row : grid) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                cols[j] += row[j];
                sum += cols[j];
                if (sum > k) {
                    break;
                }
                ans++;
            }
        }

        return ans;
    }

}
