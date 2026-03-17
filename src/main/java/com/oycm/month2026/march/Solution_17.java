package com.oycm.month2026.march;

import com.oycm.DataCreateUtils;

import java.util.Arrays;

public class Solution_17 {

    /**
     * 1727. <a href="https://leetcode.cn/problems/largest-submatrix-with-rearrangements/description/">重新排列后的最大子矩阵</a> 1927
     *
     * @param matrix
     * @return
     */
    public int largestSubmatrix(int[][] matrix) {
        /*
        解题思路: height[j] 数组表示 matrix[i][j] 上有多少个连续 1 矩形
         */
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            // 计算 (i,j) 位置上连续矩形数
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            // 把 heights 重新排序, 倒序排序
            int[] temp = heights.clone();
            Arrays.sort(temp);
            for (int j = 0; j < n; j++) {
                // 最低的矩形为高，高增长，边上缩短
                ans = Math.max(ans, temp[j] * (n - j));
            }

        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(new Solution_17().largestSubmatrix(DataCreateUtils.twoDimensionInts("[[0,0,1],[1,1,1],[1,0,1]]")));
    }

}
