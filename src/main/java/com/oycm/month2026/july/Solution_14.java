package com.oycm.month2026.july;

import java.util.Arrays;

public class Solution_14 {

    /**
     * 3336. 最大公约数相等的子序列数量
     * <br>
     * 3336. <a href="https://leetcode.cn/problems/find-the-number-of-subsequences-with-equal-gcd/description/">最大公约数相等的子序列数量</a> 2403
     *
     * @param nums
     * @return
     */
    public int subsequencePairCount(int[] nums) {
        /*
        给你一个整数数组 nums。
        请你统计所有满足以下条件的 非空 子序列 对 (seq1, seq2) 的数量：
            子序列 seq1 和 seq2 不相交，意味着 nums 中 不存在 同时出现在两个序列中的下标。
            seq1 元素的 GCD 等于 seq2 元素的 GCD。
        返回满足条件的子序列对的总数。
        由于答案可能非常大，请返回其对 109 + 7 取余 的结果。
         */
        /*
        1 <= nums.length <= 200
        1 <= nums[i] <= 200
         */
        /*
        题解
         */
        int n = nums.length;
        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        int[][][] memo = new int[n][m + 1][m + 1];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1); // -1 表示没有计算过
            }
        }
        return (dfs(n - 1, 0, 0, nums, memo) - 1 + mod) % mod;
    }

    private static final int mod = 1_000_000_007;

    private int dfs(int i, int j, int k, int[] nums, int[][][] memo) {
        if (i < 0) {
            return j == k ? 1 : 0;
        }
        if (memo[i][j][k] < 0) {
            long res = (long) dfs(i - 1, j, k, nums, memo) +
                    dfs(i - 1, gcd(j, nums[i]), k, nums, memo) +
                    dfs(i - 1, j, gcd(k, nums[i]), nums, memo);
            memo[i][j][k] = (int) (res % mod);
        }
        return memo[i][j][k];
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
