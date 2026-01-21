package com.oycm.datastructure.stack.monotonic.rectangle;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_3 {

    /**
     * 85. <a href="https://leetcode.cn/problems/maximal-rectangle/description/">最大矩形</a>
     *
     * @param matrix 仅包含 0 或 1 的矩阵
     * @return 只包含 1 的最大矩形
     */
    public int maximalRectangle(char[][] matrix) {
        /*
        转换成 m 个 长 为 n 的 柱状图, 求 最大面积
         */
        int ans = 0, m = matrix.length, n = matrix[0].length;
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    mat[i][j] = i > 0 ? mat[i - 1][j] + 1 : 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            ans = Math.max(ans, largestRectangleArea(mat[i]));
        }

        return ans;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int right = 0; right <= n; right++) {
            int h = right < n ? heights[right] : -1;
            while (stack.size() > 1 && h <= heights[stack.peek()]) {
                // 更新答案
                int i = stack.poll();
                int left = stack.peek();
                ans = Math.max(ans, heights[i] * (right - left - 1));
            }

            stack.push(right);
        }

        return ans;
    }

}
