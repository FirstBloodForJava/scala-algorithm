package com.oycm.hot100.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution_73 {

    /**
     * 84. <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/description/">柱状图中最大的矩形</a>
     *
     * @param heights 长度为 n 的数组, n 是正整数, 每根柱子彼此相邻, 且宽度为 1
     * @return 在柱状图中, 能勾勒出最大的矩形面积
     */
    public int largestRectangleArea(int[] heights) {
        /*
        选择 [i, j] 区间作为矩形的长, 面积是 (j - i + 1) * min 短板效应
        [2,1,5,6,2,3]
        面积最大的矩形高度一定是 heights 中的元素, 反证法, 如果高度不在 heights 中, 可以增加高到某根柱子的顶部, 底边长不变, 高增加, 面积变大, 和前面矛盾, 所以 高度一定在 heights 中
        枚举每个 h = heights[i], 作为矩形的高, 需要找出矩形的宽是多少:
            在 i 的左侧, 找出 小于 heights[i] 最近 下标 left, 如果不存在, 则为 -1, 左边柱子为 left + 1, left = -1 时, 表示为最左边的柱子
            在 i 的右侧, 找出 小于 heights[i] 最近 下标 right, 如果不存在, 则为 n, 右边柱子为 right - 1, right = n 时, 表示为最右边的柱子
            长为 (right - 1) - (left + 1) + 1 = right - left - 1;
        问题变成怎么快速计算: heights[i] 的 left 和 right
         */
        int n = heights.length;
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        // heights[i] 左边最近小于 heights[i] 的下标，和每日温度找下一个跟高温度下标有点像
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (right[i] - left[i] - 1));
        }

        return ans;
    }

    public int largestRectangleArea_2(int[] heights) {
        /*
        给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
        求在该柱状图中，能够勾勒出来的矩形的最大面积。
         */
        /*
        枚举所有 i 以 height[i] 为高的矩形能到达左右边界，这样就能把所有的面积计算出来
            left[i] 表示 i 左边 最近小于 height[i] 的下标；
            right[i] 表示 i 右边 最近小于 height[i] 的下标；
        如何快速计算出 left[i] 或 right[i] 暴力计算是 O(n^2)
         */
        /*
        left[i] 表示 i 左边 最近小于 height[i] 的下标；
        right[i] 表示 i 右边最近小于等于 height[i] 的下标
        right[i] 定义，[1,3,4,3,2] 第一个 height[i] = 3 计算比第一种定义要小，但是最终结果是一样的
         */
        Deque<Integer> st = new ArrayDeque<>();
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        /*
            [0, 1, 2, 3, 4]
            [1, 3, 4, 2, 2]
        l   [-1,0, 1, 0, 0]
        r   [0, 3, 3, 4, 0]
        所以 right 要初始赋值 n
         */
        Arrays.fill(right, n);
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                right[st.pop()] = i;
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (right[i] - left[i] - 1));
        }

        return ans;
    }

    public int largestRectangleArea_1(int[] heights) {
        /*
        给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
        求在该柱状图中，能够勾勒出来的矩形的最大面积。
         */
        /*
        枚举所有 i 以 height[i] 为高的矩形能到达左右边界，这样就能把所有的面积计算出来
            left[i] 表示 i 左边 最近小于 height[i] 的下标；
            right[i] 表示 i 右边 最近小于 height[i] 的下标；
        如何快速计算出 left[i] 或 right[i] 暴力计算是 O(n^2)
         */
        /*
        left[i] 表示 i 左边 最近小于 height[i] 的下标；
        right[i] 表示 i 右边最近小于等于 height[i] 的下标
        right[i] 定义，[1,3,4,3,2] 第一个 height[i] = 3 计算比第一种定义要小，但是最终结果是一样的
         */
        Deque<Integer> st = new ArrayDeque<>();
        int n = heights.length;
        /*
            [0, 1, 2, 3, 4]
            [1, 3, 4, 2, 2]
        l   [-1,0, 1, 0, 0]
        r   [0, 3, 3, 4, 0]
        所以 right 要初始赋值 n，不能这里是因为 i 右边的元素都 大于等于 height[i]
         */
        st.push(-1);
        int ans = 0;
        for (int r = 0; r <= n; r++) {
            // 当 r == n 时，栈中元素会全部取出来，right[i] 都是 n
            int h = r < n ? heights[r] : -1;
            while (st.size() > 1 && heights[st.peek()] >= h) {
                // i 就是右端点
                int i = st.poll();
                int l = st.peek();
                ans = Math.max(ans, heights[i] * (r - l - 1));
            }

            st.push(r);
        }

        return ans;
    }

}
