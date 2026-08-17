package com.oycm.dp.c.complete_backpack;

import java.util.Arrays;

public class Solution_3 {

    /**
     * 279. <a href="https://leetcode.cn/problems/perfect-squares/description/">完全平方数</a>
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        /*
        给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
        完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
        例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
         */
        /*
        [1, sqrt(n)] 选一些平方数（可以重复选），和恰好为 n 的最小选择数量
        f[i][j] 表示从前 i 个数中选一些平方数（可以重复选）和恰好为 n 的最小选择数量
        f[i][j] =
            f[i-1][j], j < i^2;
            min(f[i-1][j], f[i-1][j-i^2] + 1), j >= i^2
        f[i][j] 只和 f[i-1] 数组有关，可以简化为
        f[j] = min(f[j], f[j-i^2]), i^2 <= j <= n
         */
        init();
        return f[n];
    }

    private static final int N = 10000;
    private static final int[] f = new int[N + 1];
    private static boolean initialized = false;

    public void init() {
        if (initialized) return;
        initialized = true;
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 1; i * i <= N; i++) {
            for (int j = i * i; j <= N; j++) {
                f[j] = Math.min(f[j], f[j - i * i] + 1);
            }
        }
    }

}
