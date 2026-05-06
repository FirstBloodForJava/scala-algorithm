package com.oycm.dp.a.climb_stairs;

public class Solution_3 {

    /**
     * 3693. <a href="https://leetcode.cn/problems/climbing-stairs-ii/description/">爬楼梯 II</a> 1560
     *
     * @param n
     * @param costs
     * @return 返回到达第 n 级台阶所需的 最小 总成本。
     */
    public int climbStairs(int n, int[] costs) {
        /*
        你正在爬一个有 n + 1 级台阶的楼梯，台阶编号从 0 到 n。
        长度为 n 的 下标从 1 开始 的整数数组 costs，其中 costs[i] 是第 i 级台阶的成本。
        从第 i 级台阶，你 只能 跳到第 i + 1、i + 2 或 i + 3 级台阶。
        从第 i 级台阶跳到第 j 级台阶的成本定义为： costs[j] + (j - i)^2
        你从第 0 级台阶开始，初始 cost = 0。
         */
        /*
        dfs(i) 表示从第 0 级台阶爬到第 i 级台阶的最低花费：
           如果从下标 i-1 到达第 i 级台阶，那么需要知道爬到第 i-1 阶楼梯的最低花费，问题缩小成，爬到第 n-1 阶楼梯的最低花费；
           如果从下标 i-2 到达第 i 级台阶，那么需要知道爬到第 i-2 阶楼梯的最低花费，问题缩小成，爬到第 n-2 阶楼梯的最低花费；
           如果从下标 i-3 到达第 i 级台阶，那么需要知道爬到第 i-3 阶楼梯的最低花费，问题缩小成，爬到第 n-3 阶楼梯的最低花费；
        dfs(i) = min(dfs(i-1) + 1, dfs(i-2) + 4 + dfs(i-3) + 9) + costs[i]
         */
        int f0 = 0, f1 = 0, f2 = 0;
        for (int x : costs) {
            int f = Math.min(Math.min(f0 + 9, f1 + 4), f2 + 1) + x;
            f0 = f1;
            f1 = f2;
            f2 = f;
        }
        return f2;
    }

    public int dfs(int i, int[] costs, int[] memo) {
        if (i <= 0) {
            return 0;
        }
        if (memo[i] != 0) {
            return memo[i];
        }

        int res = Integer.MAX_VALUE;
        for (int j = Math.max(i - 3, 0); j < i; j++) {
            res = Math.min(res, dfs(j, costs, memo) + (i - j) * (i - j));
        }
        res += costs[i - 1];
        return memo[i] = res;
    }

}
