package com.oycm.dp.e.best_divide;

public class Solution_8 {

    /**
     * 1043. <a href="https://leetcode.cn/problems/partition-array-for-maximum-sum/description/">分隔数组以得到最大和</a> 1916
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        /*
        给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。
        分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
        返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
         */
        int n = arr.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // [j, i] 最多 k 个元素 i - j + 1 <= k => i - j < k
            for (int j = i, mx = 0; j >= 0 && i - j < k; j--) {
                mx = Math.max(mx, arr[j]);
                f[i + 1] = Math.max(f[i + 1], f[j] + (i - j + 1) * mx);
            }
        }
        return f[n];
    }

    public int dfs(int i, int k, int[] arr, int[] memo) {
        if (i == 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = 0, mx = 0;
        // 最长为 k，分为一组
        for (int j = i - 1; j >= Math.max(0, i - k); j--) {
            mx = Math.max(mx, arr[j]);
            res = Math.max(res, dfs(j, k, arr, memo) + (i - j) * mx);
        }
        return memo[i] = res;

    }
}
