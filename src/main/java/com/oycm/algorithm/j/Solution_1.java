package com.oycm.algorithm.j;

public class Solution_1 {

    /**
     * 3537. <a href="https://leetcode.cn/problems/fill-a-special-grid/description/">填充特殊网格</a> 1542
     *
     * @param n
     * @return
     */
    public int[][] specialGrid(int n) {
        /*
        2^N x 2^N, 从 0 到 2^2N - 1 的整数填充网格的特殊网格条件：
            右上角象限 中的 所有数字 都小于 右下角象限 中的 所有数字。
            右下角象限 中的 所有数字 都小于 左下角象限 中的 所有数字。
            左下角象限 中的 所有数字 都小于 左上角象限 中的 所有数字。
            每个象限也都是一个特殊网格。
            任何 1x1 的网格都是特殊网格
        右上角顺时针旋转，数字从小到大
        4 1
        3 2
         */
        /*
        先划分一个最大的四象限，2^2n / 4 = 2^2(n-1)
        右上角填的范围 [0, 2^2(n-1) - 1]
        右下角填的范围 [2^2(n-1), 2 * 2^2(n-1) - 1]
        左下角填的范围 [2 * 2^2(n-1), 3 * 2^2(n-1) - 1]
        左上角填的范围 [3 * 2^2(n-1), 2^2n - 1]
         */
        /*
        题解思路：定义 dfs(u, d, l, r) 表示待填充 行号 [u, d), 列号在 [l, r) 的子矩阵
            子矩阵可继续划分长为 m = (d - u) / 2 子矩阵，按 上面的顺序填写，
            右上角 dfs(u, u + m, l + m, r);
            右下角 dfs(u + m, d, l + m, r);
            左下角 dfs(u + m, d, l, l + m);
            左上角 dfs(u, u + m, l, l + m);
        只有一个格子时，填值 d - u == 1, a[d][l] = val++;
         */
        int[][] a = new int[1 << n][1 << n];
        dfs(0, a.length, 0, a.length, a);
        return a;
    }

    private int val = 0;

    public void dfs(int u, int d, int l, int r, int[][] a) {
        if (d - u == 1) {
            a[u][l] = val++;
            return;
        }
        int m = (d - u) / 2;
        dfs(u, u + m, l + m, r, a);
        dfs(u + m, d, l + m, r, a);
        dfs(u + m, d, l, l + m, a);
        dfs(u, u + m, l, l + m, a);
    }
}
