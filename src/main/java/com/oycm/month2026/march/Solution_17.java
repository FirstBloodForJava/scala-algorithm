package com.oycm.month2026.march;

import com.oycm.utils.DataCreateUtils;

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

    static class Solution_17_1 {

        public int largestSubmatrix(int[][] matrix) {

            /*
            题解思路: 第 i-1 行 到 i 行, heights 高度 如何变化？
                如果 matrix[i][j] == 0, heights[j] = 0; 0 会排在大于 0 的高度前面
                如果 matrix[i][j] == 1, heights[j]++; 对于增加 1 高度, 相对大小是不变的, 无需再次排序
             */
            int m = matrix.length, n = matrix[0].length;
            int[] heights = new int[n];
            int ans = 0;
            // 从小到大解法
            /*
            // 高度从小到大排序后的 列号
            int[] idx = new int[n];
            for (int i = 0; i < idx.length; i++) {
                idx[i] = i;
            }
            // 用来记录 1 的 列号
            int[] ones = new int[n];

            for (int[] row : matrix) {
                int zero = 0;
                int one = 0;
                for (int j : idx) {
                    if (row[j] == 0) {
                        heights[j] = 0;
                        idx[zero++]  = j;
                    } else {
                        heights[j]++;
                        ones[one++] = j;
                    }
                }
                // height[idx[i]] 是递增的
                for (int i = zero; i < n; i++) {
                    idx[i] = ones[i - zero];
                    ans = Math.max(ans, (n - i) * heights[idx[i]]);
                }
            }
            */
            // 高度从大到小排序后的 列号
            int[] idx = new int[n];
            for (int i = 0; i < idx.length; i++) {
                idx[i] = i;
            }
            for (int[] row : matrix) {
                int one = 0;
                for (int i = 0; i < idx.length; i++) {
                    int j = idx[i];
                    if (row[j] == 0) {
                        heights[j] = 0;
                    } else {
                        heights[j]++;
                        // 交换
                        /*
                        hei = [3, 2, 1, 0, 0, 0] 当前一层数量
                        idx = [1, 2, 3, 5, 4, 0] hei[idx[i]] 是升序
                        row = [1, 1, 0, 0, 1, 1] 下一行 row[j] 的情况
                        当遇到非 0 时，和前面的 idx[one] 交换位置, 需要下标遍历 idx, 会有自己和自己交换的情况
                         */
                        int temp = idx[one];
                        idx[one] = j;
                        idx[i] = temp;
                        one++;
                    }
                }
                for (int i = 0; i < one; i++) {
                    ans = Math.max(ans, (i + 1) * heights[idx[i]]);
                }
            }

            return ans;

        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_17().largestSubmatrix(DataCreateUtils.twoDimensionInts("[[0,0,1],[1,1,1],[1,0,1]]")));
        System.out.println(new Solution_17_1().largestSubmatrix(DataCreateUtils.twoDimensionInts("[[0,0,1],[1,1,1],[1,0,1]]")));
    }

}
