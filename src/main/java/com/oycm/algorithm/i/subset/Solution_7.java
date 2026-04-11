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

class Solution_7_GosperHack {

    public int maximumRows(int[][] matrix, int numSelect) {
        /*
        n = 7, numSelect = 4, 快速枚举用 4 个 1 的下一个值 0001111
        求 0011110 下一个值
            1. 将最右侧的 01 转换成 10
            2. 将该位置后的所有 1 移动到最右边
        第一点, 可以 0011110 + lowbit(0011110) 得到 0100000
        0011110
        0100000
        观察到 0011110 + lowbit 后的值和原值 异或后，可得到 0111110(左边多了 1 个 1), 将后面的 3 个一移动到最右边
            0111110 / lowbit =>  011111(去掉 lowbit 后面的 0) 再左移 两位
        计算下一个值的公式
            left = x + lowbit(x) = x + (x & -x)
            right = (left ^ x) / lowbit(x) >> 2
         */
        int m = matrix.length, n = matrix[0].length;
        // mask[i] 值表示第 i 行，二进制从低位到高位，为 1 的下标表示 j 列为 1
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mask[i] |= matrix[i][j] << j;
            }
        }

        int ans = 0;
        int subset = (1 << numSelect) - 1;
        while (subset < (1 << n)) {
            int cnt = 0;
            for (int row : mask) {
                if ((row & subset) == row) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            // 计算下一个 numSelect 长的值
            int lb = subset & -subset;
            int x = subset + lb;
            subset = ((subset ^ x) / lb >> 2) | x;
        }
        return ans;
    }

}