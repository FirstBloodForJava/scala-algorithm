package com.oycm.datastructure.stack.monotonic.contribution_approach;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1 {

    /**
     * 907. <a href="https://leetcode.cn/problems/sum-of-subarray-minimums/description/">子数组的最小值之和</a> 1976
     *
     * @param arr
     * @return 找到 arr 数组的连续子数组最小值的 总和
     */
    public static int sumSubarrayMins(int[] arr) {
        /*
        [3, 1, 2, 4]
        枚举 左边 [0, n-1] 开始, 子数组不断变长
            [3], [3, 1], [3, 1, 2], [3, 1, 2, 4] 这样暴力计算 时间复杂度是 o (n^2) 的 暴力的
        固定右边往左边延长
            [3]
            [1], [1, 3]
            [2], [2, 1], [2, 1, 3]
            [4], [4, 2], [4, 2, 1], [4, 2, 1, 3]
        题解思路: 选 arr[i] 为最小值, 子数组的个数
         */
        int mod = 1000000007, n = arr.length;
        long ans = 0;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            left[i] = st.isEmpty() ? i + 1 : i - st.peek();
            st.push(i);
        }
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            right[i] = st.isEmpty() ? n - i : st.peek() - i;
            st.push(i);
        }
        for (int i = 0; i < n; i++) {
            ans += (long) arr[i] * left[i] * right[i];
        }

        return (int) (ans % mod);
    }

    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[]{3, 1, 2, 4}));
    }

}
