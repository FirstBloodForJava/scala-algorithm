package com.oycm.dp.b.basic;

public class Solution_7 {

    /**
     * 3603. <a href="https://leetcode.cn/problems/minimum-cost-path-with-alternating-directions-ii/description/">交替方向的最小路径代价 II</a> 1639
     *
     * @param m
     * @param n
     * @param waitCost
     * @return
     */
    public long minCost(int m, int n, int[][] waitCost) {
        /*
        给你两个整数 m 和 n，分别表示网格的行数和列数。
        进入单元格 (i, j) 的成本定义为 (i + 1) * (j + 1)。
        另外给你一个二维整数数组 waitCost，其中 waitCost[i][j] 定义了在该单元格 等待 的成本。
        路径始终从第 1 步进入单元格 (0, 0) 并支付入场花费开始。
        每一步，你都遵循交替模式：
            在 奇数秒，你必须向 右 或向 下 移动到 相邻 的单元格，并支付其进入成本。
            在 偶数秒，你必须原地 等待恰好 1 秒并在 1 秒期间支付 waitCost[i][j]。
        返回到达 (m - 1, n - 1) 所需的 最小 总成本。
        从第 1 秒开始在单元格 (0, 0)
         */
        /*
        1 <= m, n <= 1e5
        2 <= m * n <= 1e5
        waitCost.length == m, waitCost[0].length == n
        0 <= waitCost[i][j] <= 1e5
         */
        /*
        如果没有等待的概念
        dfs(i, j) 表示从 (0, 0) 到 (i, j) 的最小成本
            (i, j) 可以拆分成 dfs(i-1, j) 和 dfs(i, j-1) 最小成本
        dfs(i, j) = min(dfs(i-1, j), dfs(i, j-1)) + (i+1) * (j+1)
        除了 (0, 0) 出发点和终点 (m-1, n-1)，不用花费 waitCost[i][j]，中间的点都需要 cost[i][j]
        可以把 waitCost[0][0] 置为 0，这样就不用特殊处理
        dfs(i, j) = (i + 1)  * (j + 1) +
            min(
            dfs(i-1, j) + cost[i-1][j],
            dfs(i, j-1) + cost[i][j-1]
            )
        每个路径点的值表示为 (i + 1) * (j + 1) + cost[i][j]，把 cost[0][0] 和 cost[m-1][n-1] 置为 0，
        只有起始点和终点不用等待，
        问题就变成路径中求最小路径和
         */
        waitCost[m - 1][n - 1] = 0;

        return dfs(m - 1, n - 1, waitCost, new long[m][n]);
    }

    public long dfs(int i, int j, int[][] cost, long[][] memo) {
        if (i < 0 || j < 0) return Long.MAX_VALUE;
        if (i == 0 && j == 0) return 1;
        if (memo[i][j] != 0) return memo[i][j];
        return memo[i][j] = Math.min(dfs(i - 1, j, cost, memo),
                dfs(i, j - 1, cost, memo)) + (i + 1) * (j + 1) + cost[i][j];
    }

}
