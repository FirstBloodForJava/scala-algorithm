package com.oycm.month2026.march;

public class Solution_2 {

    /**
     * 1536. <a href="https://leetcode.cn/problems/minimum-swaps-to-arrange-a-binary-grid/description/">排布二进制网格的最少交换次数</a> 1881
     *
     * @param grid
     * @return
     */
    public int minSwaps(int[][] grid) {
        /*
        n x n 矩阵, 二进制网格, 可以选择网格的 相邻两行 进行交换
        进行最少的操作次数, 满足主对角线以上的格子全部都是 0, 无法满足则 返回 -1
        对角线 下标 [0,0], [1,1], ..., [n-1, n-1]

        两种指标
            tailZeros[i] 表示 grid[i] 每行末尾 0 数量, 交换后需满足 tailZeros[i] >= n - 1 - i;
            lastOneIdx[i] 表示 grid[i] 每行最后 1 出现的位置, 交换后需满足 lastOneIdx[i] <= i;
        两种模式选择 符合 i 条件的的最近 idx 进行交换
         */
        int ans = 0;
        int n = grid.length;
        int[] lastOneIdx = new int[n];
        for (int i = 0; i < n; i++) {
            lastOneIdx[i] = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    lastOneIdx[i] = j;
                    break;
                }
            }
        }
        out:
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                if (lastOneIdx[j] <= i) {
                    ans += j - i;
                    // [i, j-1] 全部右移一位
                    System.arraycopy(lastOneIdx, i, lastOneIdx, i + 1, j - i);
                    continue out;
                }
            }
            return -1;
        }

        return ans;
    }

    private int m1(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int[] tailZeros = new int[n];
        for (int i = 0; i < n; i++) {
            tailZeros[i] = n;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    tailZeros[i] = n - 1 - j;
                    break;
                }
            }
        }
        out:
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                int needZeros = n - 1 - i;
                if (tailZeros[j] >= needZeros) {
                    ans += j - i;
                    // [i, j-1] 全部右移一位
                    System.arraycopy(tailZeros, i, tailZeros, i + 1, j - i);
                    continue out;
                }
            }
            return -1;
        }

        return ans;
    }

}
