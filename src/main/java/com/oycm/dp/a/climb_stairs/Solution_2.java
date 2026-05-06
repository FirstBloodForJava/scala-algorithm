package com.oycm.dp.a.climb_stairs;

public class Solution_2 {

    /**
     * 746. <a href="https://leetcode.cn/problems/min-cost-climbing-stairs/description/">使用最小花费爬楼梯</a> 1358
     *
     * @param cost 整数数组, cost.length = n
     * @return 计算并返回达到楼梯顶部的最低花费
     */
    public int minCostClimbingStairs(int[] cost) {
        /*
        cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
        可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
         */
        /*
        dfs(i) 表示从下标 0 或下标 1 开始爬到第 i 阶楼梯的最低花费
           如果从下标 n-1 到达楼梯顶部，那么需要知道爬到第 n-1 阶楼梯的最低花费，问题缩小成，爬到第 n-1 阶楼梯的最低花费；
           如果从下标 n-2 到达楼梯顶部，那么需要知道爬到第 n-2 阶楼梯的最低花费，问题缩小成，爬到第 n-2 阶楼梯的最低花费；
        dfs(i) = Math.min(dfs(i-1) + cost[i-1], dfs(i-2) + cost[i-2])
        递归边界
            dfs(0) = 0;
            dfs(1) = 0;
         */
        int f0 = 0, f1 = 0;
        // i + 1 表示到达的台阶
        for (int i = 1; i < cost.length; i++) {
            int f = Math.min(f0 + cost[i - 1], f1 + cost[i]);
            f0 = f1;
            f1 = f;
        }
        return f1;
    }

    public int dfs(int i, int[] cost, int[] memo) {
        if (i <= 1) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        return memo[i] = Math.min(dfs(i - 1, cost, memo) + cost[i - 1], dfs(i - 2, cost, memo) + cost[i - 2]);
    }


}
