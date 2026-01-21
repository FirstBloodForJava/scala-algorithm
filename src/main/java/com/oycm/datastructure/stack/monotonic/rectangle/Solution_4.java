package com.oycm.datastructure.stack.monotonic.rectangle;


public class Solution_4 {

    /**
     * 221. <a href="https://leetcode.cn/problems/maximal-square/description/">最大正方形</a>
     *
     * @param matrix 仅包含 0 或 1 的矩阵
     * @return 只包含 1 的最大正方形面积
     */
    public int maximalSquare(char[][] matrix) {
        /*
        和 85 类似, 计算答案时 取最短边
         */
        int ans = 0;
        int n = matrix[0].length;
        int[] heights = new int[n + 1];
        for (char[] row : matrix) {
            for (int i = 0; i < n; i++) {
                if (row[i] == '0') {
                    heights[i] = 0;
                } else {
                    heights[i]++;
                }
            }
            ans = Math.max(ans, largestRectangleArea(heights));
        }

        return ans;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int ans = 0;
        int[] st = new int[n];
        int top = -1;
        st[++top] = -1;
        for (int right = 0; right < n; right++) {
            int h = heights[right];
            while (top > 0 && h <= heights[st[top]]) {

                int i = st[top--];
                int left = st[top];
                // 更新答案
                int min = Math.min(heights[i], right - left - 1);
                ans = Math.max(ans, min * min);
            }
            st[++top] = right;
        }

        return ans;
    }

}
