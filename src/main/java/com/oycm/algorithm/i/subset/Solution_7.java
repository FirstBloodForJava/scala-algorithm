package com.oycm.algorithm.i.subset;

public class Solution_7 {

    /**
     * 2397. <a href="https://leetcode.cn/problems/maximum-rows-covered-by-columns/description/">被列覆盖的最多行数</a> 1719
     *
     * @param matrix    m x n 的二进制矩阵, matrix[i][j] 值要么是 0, 要么是 1
     * @param numSelect 从 matrix 中选择的 不同 列的数量
     * @return 从矩阵中选出 numSelect 个列，使集合覆盖的行数最大化
     */
    public int maximumRows(int[][] matrix, int numSelect) {
        /*
        假设 s 是选择的列的集合，对于矩阵中的某一行 row，满足以下条件，则表示这一行被集合覆盖：
            满足 matrix[row][col] == 1 的每个单元格 matrix[row][col]（0 <= col <= n - 1），col 均存在于 s 中；
            或者 row 中不存在值为 1 的单元格
         */
        /*
        要选 numSelect 列, 加一个参数, 表示不选的数量
         */
        int m = matrix.length, n = matrix[0].length;
        if (n == numSelect) return m;
        int[] rowCnt = new int[m];
        for (int i = 0; i < matrix.length; i++) {
            for (int x : matrix[i]) {
                rowCnt[i] += x;
            }
            if (rowCnt[i] == 0) ans++;
        }
        dfs(matrix, rowCnt, 0, n - numSelect);
        return ans;
    }

    int ans = 0;

    public void dfs(int[][] matrix, int[] rowCnt, int i, int notSelect) {
        // 只能选 numSelect
        if (i + notSelect == matrix[0].length) {
            return;
        }
        // 不选
        if (notSelect > 0) {
            dfs(matrix, rowCnt, i + 1, notSelect - 1);
        }
        // 选
        int temp = 0;
        for (int j = 0; j < matrix.length; j++) {
            rowCnt[j] -= matrix[j][i];
            if (rowCnt[j] == 0) temp++;
        }
        ans = Math.max(temp, ans);
        dfs(matrix, rowCnt, i + 1, notSelect);
        // 恢复现场
        for (int j = 0; j < matrix.length; j++) {
            rowCnt[j] += matrix[j][i];
        }
    }

}
