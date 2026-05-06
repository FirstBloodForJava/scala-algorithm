package com.oycm.dp.a.climb_stairs;

public class Solution_1 {

    /**
     * 70. <a href="https://leetcode.cn/problems/climbing-stairs/description/">爬楼梯</a>
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /*
        需要 n 阶你才能到达楼顶。
        每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
         */
        /*
        定义 f(x) 表示 从 0 爬到 x 阶楼梯的方案数：
            如果最后一步爬了 1 个台阶，那么需要先爬到 x-1 个台阶，问题缩小到成：爬 n-1 个台阶的方案数；
            如果最后一步爬了 2 个台阶，那么需要先爬到 x-2 个台阶，问题缩小到成：爬 n-2 个台阶的方案数；
        f(n) = f(n-1) + f(n-1)
         */
        int f0 = 1;
        int f1 = 1;
        int f = f1;
        for (int i = 2; i <= n; i++) {
            f = f0 + f1;
            f0 = f1;
            f1 = f;
        }
        return f;
    }

    public int dfs(int n, int[] memo) {
        if (n <= 1) {
            return 1;
        }
        if (memo[n] != -1) return memo[n];
        return memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
    }

}
