package com.oycm.month2026.march;

import com.oycm.DataCreateUtils;

import java.util.Arrays;

public class Solution_16 {

    /**
     * 1878. <a href="https://leetcode.cn/problems/get-biggest-three-rhombus-sums-in-a-grid/description/">矩阵中最大的三个菱形和</a> 1898
     *
     * @param grid
     * @return
     */
    public int[] getBiggestThree(int[][] grid) {
        /*
        题解思路: 斜向前缀和
         */
        int m = grid.length;
        int n = grid[0].length;
        /*
        左上角 -> 右下角 对角线的前缀和
        diagSum[i+1][j+1] 表示最上边或最左边出发, 向右下角出发到 (i,j) 这条线段的 ↘ 前缀和
            diagSum[i + 1][j + 1] = diagSum[i][j] + grid[i][j];
        (x,y) 开始的连续 k 个元素和为 diagSum[x+k][y+k] - diagSum[x][y], 未包含右下角的端点
         */
        int[][] diagSum = new int[m + 1][n + 1]; //
        /*
        右上角 -> 左下角 对角线的前缀和
        antiSum[i + 1][j] 表示最上边或最右边出发，向左下角出发到 (i,j) 这条线段的 ↙ 前缀和
            右边到左边, 所以上一层的 antiSum[i][j + 1]
            antiSum[i + 1][j] = antiSum[i][j + 1] + grid[i][j]
         */
        int[][] antiSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                diagSum[i + 1][j + 1] = diagSum[i][j] + v;
                antiSum[i + 1][j] = antiSum[i][j + 1] + v;
            }
        }

        // 枚举菱形正中心 (i,j)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(grid[i][j]); // 一个数也算菱形
                // 枚举菱形顶点到正中心的距离 k，注意菱形顶点不能出界
                // i-k >= 0 且 i+k <= m-1，所以 k <= min(i, m-1-i)，对于 j 同理
                int mx = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));
                for (int k = 1; k <= mx; k++) {
                    int a = queryDiagonal(diagSum, i - k, j, k); // 菱形右上的边
                    int b = queryDiagonal(diagSum, i, j - k, k); // 菱形左下的边
                    /*
                    前面计算 a, 包含了 (i-k, j); 计算 b, 包含了 (i, j - k), 左上这条边 需要计算从线段下一个点开始, 且要少计算一个元素
                    c (i-k, j) 下一个点 x++, y-- => (i-k+1, j-1)
                     */
                    int c = queryAntiDiagonal(antiSum, i - k + 1, j - 1, k - 1); // 菱形左上的边
                    /*
                    d (i, j+k) 有 k+1 个数
                     */
                    int d = queryAntiDiagonal(antiSum, i, j + k, k + 1); // 菱形右下的边
                    update(a + b + c + d);
                }
            }
        }

        int[] ans = new int[]{x, y, z};
        int len = 3;
        while (ans[len - 1] == 0) { // 不同的和少于三个
            len--;
        }
        return Arrays.copyOf(ans, len);
    }

    private int x, y, z;

    private void update(int v) {
        if (v > x) {
            z = y;
            y = x;
            x = v;
        } else if (v < x && v > y) {
            z = y;
            y = v;
        } else if (v < y && v > z) {
            z = v;
        }
    }

    private int queryDiagonal(int[][] diagSum, int x, int y, int k) {
        return diagSum[x + k][y + k] - diagSum[x][y];
    }

    private int queryAntiDiagonal(int[][] antiSum, int x, int y, int k) {
        return antiSum[x + k][y + 1 - k] - antiSum[x][y + 1];
    }


    public static void main(String[] args) {
        new Solution_16().getBiggestThree(DataCreateUtils.twoDimensionInts("[[1,2,3],[4,5,6],[7,8,9]]"));
    }
}
