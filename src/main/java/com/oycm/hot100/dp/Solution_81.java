package com.oycm.hot100.dp;

public class Solution_81 {

    /**
     * 70. <a href="https://leetcode.cn/problems/climbing-stairs/description/">爬楼梯</a>
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /*
        假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
        每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
         */
        /*
        dfs(i) 表示爬 i 个台阶的方案数。
            dfs(i-1) 表示爬 i-1 个台阶的方案数，最后一步爬 1 个台阶；
            dfs(i-2) 表示爬 i-2 个台阶的方案数，最后一部爬 2 个台阶；
        原问题可以缩小为相似的子问题，可以使用递归解决。
        递归边界 i <= 1 返回 1，不能递归 0 才返回，因为台阶数量为 1 时，只有一种方案，如果不结束递归，则 dfs(1) = dfs(0) + dfs(-1) = 1 + 1 = 2
         */
        int f0 = 0;
        int f1 = 1;
        for (int i = 1; i <= n; i++) {
            int f = f0 + f1;
            f0 = f1;
            f1 = f;
        }
        return f1;
    }

    public int dfs (int i, int[] memo) {
        if (i <= 1) return 1;
        // 方案数不可能为 0，所以不用初始化 memo 为其它值
        if (memo[i] != 0) return memo[i];
        return memo[i] = dfs(i - 1, memo) + dfs(i - 2, memo);
    }

}
