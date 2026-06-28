package com.oycm.month2026.june;

public class Solution_28 {

    /**
     * 1846. <a href="https://leetcode.cn/problems/maximum-element-after-decreasing-and-rearranging/description/">减小和重新排列数组后的最大元素</a> 1454
     *
     * @param arr
     * @return
     */
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        /*
        给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
            arr 中 第一个 元素必须为 1。
            任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
        你可以执行以下 2 种操作任意次：
            减小 arr 中任意元素的值，使其变为一个 更小的正整数。
            重新排列 arr 中的元素，你可以以任意顺序重新排列。
         */
        /*
        1 <= arr.length <= 1e5
        1 <= arr[i] <= 1e9
         */
        /*
        初始化 pre = 0，将 arr 元素添加到小顶堆中。
        如果堆顶元素 - pre > 1，那么堆顶元素修改成 pre + 1；这个已经是数组中最小的元素，只能使其变小取得最大值，才能让其后面的元素变小时变的更大。
         */
        /*
        计数排序
         */
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int i : arr) {
            // 比 n 大的数，修改为 n
            cnt[Math.min(i, n)]++;
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans + cnt[i], i);
        }

        return ans;
    }

}
