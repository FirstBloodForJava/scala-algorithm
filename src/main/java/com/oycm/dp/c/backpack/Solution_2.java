package com.oycm.dp.c.backpack;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 494. <a href="https://leetcode.cn/problems/target-sum/description/">目标和</a>
     *
     * @param nums   nums.length [1, 20]; nums[i] [0, 1000]
     * @param target [-1000, 1000]
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        /*
        给你一个非负整数数组 nums 和一个整数 target。
        向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式：
            例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
        返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
         */
        /*
        nums 所有元素和记为 s，nums 中选择添加 + 的和记为 p，nums 中选择添加 - 的和记为 q；
        q = s - p; p = s - q;
        target = p - q = p - (s - p)
        target = p - q = s - q - q
        p = (s + target) / 2，问题转换成了在 nums 中选择一些数，使得其和为 (s+target) / 2 的方案数，本质就是 选/不选 求方案数。
        q = (s - target) / 2，问题转换成了在 nums 中选择一些数，使得其和为 (s-target) / 2 的方案数，本质就是 选/不选 求方案数。
        当 target > 0，求选择一些数和为 (s-target) / 2 的方案数，给这些数添加 -；
        当 target < 0，求选择一些数和为 (s+target) / 2 的方案数，给这些数添加 +；
        综上所述，可以选择 (s - |target|) / 2 作为背包容量
        如果 s - |target| < 0，由于 nums 都是非负数，无法选择一些数和为负数，直接返回 0；
        如果 s - |target| 是奇数，由于 nums 都是非负整数，无法选择一些数得到非整数，直接返回 0；
        dfs(i, s) 表示从 nums 中选择一些数，和恰好为 s 的方案数，对于第 i 个数，有以下操作
            不选，则看 dfs(i-1, s) 有哪些方案数；
            选，则看 dfs(i-1, s-nums[i]) 有哪些法案数；
        由于对于第 i 个 选/不选 是互斥的，dfs(i, s) = dfs(i-1, s) + dfs(i-1, s - nums[i])
        递归边界 i < 0，如果 s = 0 返回 1，否则返回 0
         */
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        s -= Math.abs(target);
        if (s < 0 || s % 2 == 1) return 0;
        int m = s / 2;
        int n = nums.length;
        int[][] memo = new int[n][m + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(n-1, m, nums, memo);
    }

    public int dfs(int i, int s, int[] nums, int[][] memo) {
        if (i < 0) return s == 0 ? 1 : 0;
        if (memo[i][s] != -1) return memo[i][s];
        int res = dfs(i-1, s, nums, memo);
        if (nums[i] <= s) {
            res += dfs(i - 1, s - nums[i], nums, memo);
        }
        return memo[i][s] = res;
    }

}
