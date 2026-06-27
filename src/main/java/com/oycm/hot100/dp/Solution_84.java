package com.oycm.hot100.dp;

import java.util.Arrays;

public class Solution_84 {

    /**
     * 279.
     * <a href="https://leetcode.cn/problems/perfect-squares/description/">完全平方数</a>
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        /*
        给你一个整数 n ，返回 和为 n 的完全平方数的最少数量。
        完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
         */
        /*
        题解思路：定义 dfs(i, j) 表示从前 i 个完全平方数中选一些数（可以重复选），满足元素和恰好为 j 的最少选择次数。
        考虑第 i 个完全平方数 i*i 选/不选：
            不选，从前 i-1 个完全平方数中选一些数（可以重复选），满足元素和恰好为 j 的最少选择次数。
            选，从前 i 个完全平方数中选一些数（可以重复选），满足元素和恰好为 j-i*i 的最少选择次数。
         */
        return dfs((int) Math.sqrt(n), n);
    }

    static int[][] memo = new int[101][10001];

    static {
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
    }

    public int dfs(int i, int j) {
        if (i == 0) return j == 0 ? 0 : Integer.MAX_VALUE;
        if (memo[i][j] != -1) return memo[i][j];
        // 不能选
        if (j < i * i) return memo[i][j] = dfs(i - 1, j);

        return memo[i][j] = Math.min(dfs(i - 1, j), dfs(i, j - i * i) + 1);
    }


    public int numSquares_dp(int n) {
        /*
        1 <= n <= 1e4
         */
        init();
        return f[n];
    }

    static int[] f = new int[10001];
    static boolean initialized = false;

    public void init() {
        if (initialized) return;
        initialized = true;
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 1; i * i <= 10000; i++) {
            for (int x = i * i; x <= 10000; x++) {
                f[x] = Math.min(f[x], f[x - i * i] + 1);
            }
        }
    }

}
