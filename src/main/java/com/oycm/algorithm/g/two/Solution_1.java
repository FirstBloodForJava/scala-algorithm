package com.oycm.algorithm.g.two;

public class Solution_1 {

    /**
     * 2536. <a href="https://leetcode.cn/problems/increment-submatrices-by-one/description/">子矩阵元素加 1</a> 1583
     *
     * @param n
     * @param queries
     * @return
     */
    public int[][] rangeAddQueries(int n, int[][] queries) {

        // 二维差分数组
        int[][] diff = new int[n + 2][n + 2];
        for (int[] query : queries) {
            int r1 = query[0] + 1, c1 = query[1] + 1;
            int r2 = query[2] + 2, c2 = query[3] + 2;
            diff[r1][c1]++;
            diff[r2][c2]++;
            diff[r1][c2]--;
            diff[r2][c1]--;
        }
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diff[i + 1][j + 1] += diff[i + 1][j] + diff[i][j + 1] - diff[i][j];
                ans[i][j] = diff[i + 1][j + 1];
            }
        }

        return ans;

    }

    public int[][] nAddOne(int n, int[][] queries) {

        // 创建二维差分数组
        int[][] diff = new int[n + 1][n + 1];

        // 2. 处理每个 query
        for (int[] q : queries) {
            int r1 = q[0];
            int c1 = q[1];
            int r2 = q[2] + 1;
            int c2 = q[3] + 1;
            diff[r1][c1]++;
            diff[r1][c2]--;
            diff[r2][c1]--;
            diff[r2][c2]++;
        }

        // 3. 还原最终矩阵（二维前缀和）
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int top = i > 0 ? ans[i - 1][j] : 0;
                int left = j > 0 ? ans[i][j - 1] : 0;
                int diag = (i > 0 && j > 0) ? ans[i - 1][j - 1] : 0;

                ans[i][j] = diff[i][j] + top + left - diag;
            }
        }

        return ans;

    }

}
