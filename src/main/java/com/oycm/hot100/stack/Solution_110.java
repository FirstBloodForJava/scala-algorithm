package com.oycm.hot100.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_110 {

    /**
     * 85. <a href="https://leetcode.cn/problems/maximal-rectangle/description/">最大矩形</a>
     *
     * @param matrix 仅包含 0 或 1 的矩阵
     * @return 只包含 1 的最大矩形
     */
    public int maximalRectangle(char[][] matrix) {
        /*
        给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
         */
        /*
        转换成一个长为 m，高度不断变化矩形，求其最大矩形面积
         */
        int ans = 0, m = matrix.length, n = matrix[0].length;
        int[] height = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                height[j] = matrix[j][i] == '1' ? height[j] + 1 : 0;
            }
            ans = Math.max(ans, largestRectangleArea(height));
        }

        return ans;
    }

    public int largestRectangleArea(int[] height) {
        int n = height.length;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        int ans = 0;

        for (int r = 0; r <= n; r++) {
            int h = r < n ? height[r] : -1;
            while (st.size() > 1 && h <= height[st.peek()]) {
                int i = st.poll();
                int l = st.peek();
                ans = Math.max(ans, height[i] * (r - l - 1));
            }

            st.push(r);
        }

        return ans;
    }

}
