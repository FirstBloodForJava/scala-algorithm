package com.oycm.month2026.march;

public class Solution_24 {

    /**
     * 2906. <a href="https://leetcode.cn/problems/construct-product-matrix/description/">构造乘积矩阵</a> 2075
     * <img src = "http://47.101.155.205/image-20260324192105347.png" alt = "dfs 过程说明">
     * @param grid n * m 的二维整数矩阵
     * @return
     */
    public int[][] constructProductMatrix(int[][] grid) {
        /*
        乘积矩阵: 每个元素 p[i][j], 它的值等于除了 grid[i][j] 外所有元素的乘积
         */
        /*
        题解思路，前后缀分解
        pre[i][j] 表示 (0, 0) 开始, 到 (i,j) 前一个元素的前缀积
        suf 表示 (i, j) 下一个 元素开始 到 (n-1, m-1) 的后缀乘积
        pre 可以从 (0,0) 左到右, 上到下 遍历
        suf 可以从 (n-1, m-1) 右到左, 下到上 遍历
        可以 初始化 p = suf, 先计算 suf, 用一个变量 记录 pre[i][j]

        为什么 leetcode 执行的代码，多次使用的变量作为静态变量使用，执行会会耗时更久？
         */
        int n = grid.length, m = grid[0].length;
        int[][] p = new int[n][m];
        long suf = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                p[i][j] = (int) suf;
                suf = suf * grid[i][j] % 12345;
            }
        }
        long pre = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) (p[i][j] * pre % 12345);
                pre = grid[i][j] * pre % 12345;
            }
        }

        return p;
    }

}
