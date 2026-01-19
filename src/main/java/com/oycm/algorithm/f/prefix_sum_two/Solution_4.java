package com.oycm.algorithm.f.prefix_sum_two;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 1738. <a href="https://leetcode.cn/problems/find-kth-largest-xor-coordinate-value/description/">找出第 K 大的异或坐标值</a> 1671
     * <p>
     * 矩阵中坐标 (a, b) 的目标值可以通过所有 元素 matrix[i][j] 执行异或运算得到
     * 0 <= i <= a < m
     * 0 <= j <= b < n
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        /*
        (a, b) 目标值是不是可以看作 左上角是 (0, 0), 右下角是 (a, b) 矩阵异或前缀和
        s[i+1][j+1] = s[i+1][j] ^ s[i][j+1] ^ s[i][j] ^ matrix[i][j]
         */
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] s = new int[m + 1][n + 1];
        // 数组记录 坐标值
        int[] a = new int[m * n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j];
                a[idx++] = s[i + 1][j + 1];
            }
        }
        // 排序
        Arrays.sort(a);
        return a[idx - k];
    }
}
