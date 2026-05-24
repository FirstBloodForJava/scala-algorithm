package com.oycm.month2026.may;

public class Solution_24 {

    /**
     * 1340. <a href="https://leetcode.cn/problems/jump-game-v/description/">跳跃游戏 V</a> 1866
     *
     * @param arr arr.length [1, 1000]
     * @param d   [1, arr.length]
     * @return
     */
    public int maxJumps(int[] arr, int d) {
        /*
        给你一个整数数组 arr 和一个整数 d 。每一步你可以从下标 i 跳到：
            i + x ，其中 i + x < arr.length 且 0 < x <= d。
            i - x ，其中 i - x >= 0 且 0 < x <= d 。
        除此以外，你从下标 i 跳到下标 j 需要满足：arr[i] > arr[j] 且 arr[i] > arr[k] ，其中下标 k 是所有 i 到 j 之间的数字。
        你可以选择数组的任意下标开始跳跃。请你返回你 最多 可以访问多少个下标。
         */
        /*
        题解思路：暴力思路
        枚举 i 所有的情况
            向右跳，j = i+1, i+2, ... min(i+d, n-1)，直到 nums[j] >= nums[i]，停止枚举，枚举下一个 j 按规则跳跃；
            向左调，j = i-1, i-2, ... max(i-d, 0)，直到 nums[j] >= nums[i]，停止枚举，枚举下一个 j 按规则跳跃；
        arr[i] 最多访问下标为 x，从 arr[j] 跳到 arr[i]，还需要再计算 arr[i] 最多访问下标吗？
        不需要，i 能访问的下标数量，受 d 和 arr 数组影响，这两个参数没有变化，则不会影响结果，可以使用一个数组记录每个下标访问的最大数量，下次调用就可以直接返回。
         */
        int n = arr.length;
        int[] memo = new int[n];
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans = Math.max(ans, dfs(i, arr, d, memo));
        }

        return ans;
    }

    public int dfs(int i, int[] arr, int d, int[] memo) {
        if (memo[i] > 0) return memo[i];
        int res = 1;
        for (int j = i + 1; j <= Math.min(i + d, arr.length - 1) && arr[j] < arr[i]; j++) {
            res = Math.max(res, dfs(j, arr, d, memo) + 1);
        }

        for (int j = i - 1; j >= Math.max(i - d, 0) && arr[j] < arr[i]; j--) {
            res = Math.max(res, dfs(j, arr, d, memo) + 1);
        }
        return memo[i] = res;
    }

}
