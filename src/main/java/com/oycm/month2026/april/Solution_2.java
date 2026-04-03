package com.oycm.month2026.april;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 3418. <a href="https://leetcode.cn/problems/maximum-amount-of-money-robot-can-earn/description">机器人可以获得的最大金币数</a> 1798
     *
     * @param coins m x n 的网格
     * @return 机器人在路径上可以获得的 最大金币数，机器人的总金币数可以是负数。
     */
    public int maximumAmount(int[][] coins) {
        /*
        机器人从网格的左上角 (0, 0) 出发，目标是到达网格的右下角 (m - 1, n - 1)。
        在任意时刻，机器人只能向右或向下移动。
        网格中的每个单元格包含一个值 coins[i][j]：
            如果 coins[i][j] >= 0，机器人可以获得该单元格的金币。
            如果 coins[i][j] < 0，机器人会遇到一个强盗，强盗会抢走该单元格数值的 绝对值 的金币。
        机器人有一项特殊能力，可以在行程中 最多感化 2个单元格的强盗，从而防止这些单元格的金币被抢走。
         */
        /*
        选或不选 分类讨论:
            选 dfs(i, j, k) = max(dfs(i-1, j, k), dfs(i, j-1, k)) + coins[i][j];
            不选 dfs(i,j,k) = max(dfs(i-1, j, k-1), dfs(i, j-1, k-1));
            两种情况取最大值
        递归边界：
            dfs(-1, j, k) = dfs(i, -1, k) = min; dp[0][j][k] = dp[i][0][k] = min
            // 需要特殊处理, 初始化 dp[0][1][k] = 0, 这样不用特殊处理 i = 0, j = 0 的情况
            dfs(0, 0, 0) = coins[0][0];
            dfs(0, 0, k > 0) = max(coins[0][0], 0);
         */
        int m = coins.length, n = coins[0].length;
        int[][][] dp = new int[m + 1][n + 1][3];
        for (int[] mat : dp[0]) {
            Arrays.fill(mat, Integer.MIN_VALUE);
        }
        Arrays.fill(dp[0][1], 0);
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i + 1][0], Integer.MIN_VALUE);
            for (int j = 0; j < n; j++) {
                int coin = coins[i][j];

                dp[i + 1][j + 1][0] = Math.max(dp[i][j + 1][0], dp[i + 1][j][0]) + coin;
                dp[i + 1][j + 1][1] = Math.max(
                        Math.max(dp[i][j + 1][1], dp[i + 1][j][1]) + coin,
                        Math.max(dp[i][j + 1][0], dp[i + 1][j][0])
                );
                dp[i + 1][j + 1][2] = Math.max(
                        Math.max(dp[i][j + 1][2], dp[i + 1][j][2]) + coin,
                        Math.max(dp[i][j + 1][1], dp[i + 1][j][1])
                );
            }
        }

        /*
        空间优化: dp[i+1][j+1] 需要用到 dp[i][j+1], dp[i+1][j]
        只有 dp[i] 这一排的数据需要给下一排计算, 计算完成后，就之间给下一排使用了
         */
        int[][] f = new int[n + 1][3];
        for (int[] row : f) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        Arrays.fill(f[1], 0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int coin = coins[i][j];
                // k 需要倒序遍历，较大的 k 会用到前面较小的 k 值，如果先更新较小 k 的值，后面较大的 k 就使用了 [i+1][j+1][k] 的值更新了
                f[j + 1][2] = Math.max(
                        Math.max(f[j][2], f[j + 1][2]) + coin,
                        Math.max(f[j][1], f[j + 1][1])
                );
                f[j + 1][1] = Math.max(
                        Math.max(f[j][1], f[j + 1][1]) + coin,
                        Math.max(f[j][0], f[j + 1][0])
                );
                f[j + 1][0] = Math.max(f[j][0], f[j + 1][0]) + coin;
            }
        }


        return dp[m][n][2];
    }


    /**
     * 64. <a href="https://leetcode.cn/problems/minimum-path-sum/description/">最小路径和</a>
     *
     * @param grid m x n 网格
     * @return 找出一条从左上角到右下角的路径，使得路径上的数字总和为最小（只能向下或者向右移动一步）
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] f = grid[0];
        for (int i = 1; i < f.length; i++) {
            f[i] += f[i - 1];
        }
        for (int i = 1; i < m; i++) {
            f[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                f[j] = Math.min(f[j - 1], f[j]) + grid[i][j];
            }
        }
        return grid[0][n - 1];
    }

    public int dfs(int i, int j, int[][] grid, int[][] memo) {
        if (i < 0 || j < 0) return Integer.MAX_VALUE;

        if (i == 0 && j == 0) return grid[i][j];

        if (memo[i][j] != -1) return memo[i][j];

        return memo[i][j] = Math.min(dfs(i - 1, j, grid, memo), dfs(i, j - 1, grid, memo)) + grid[i][j];
    }

}

class Solution_2_1 {
    public int maximumAmount(int[][] coins) {
        /*
        机器人从网格的左上角 (0, 0) 出发，目标是到达网格的右下角 (m - 1, n - 1)。
        在任意时刻，机器人只能向右或向下移动。
        网格中的每个单元格包含一个值 coins[i][j]：
            如果 coins[i][j] >= 0，机器人可以获得该单元格的金币。
            如果 coins[i][j] < 0，机器人会遇到一个强盗，强盗会抢走该单元格数值的 绝对值 的金币。
        机器人有一项特殊能力，可以在行程中 最多感化 2个单元格的强盗，从而防止这些单元格的金币被抢走。
         */
        /*
        (0, 0) -> (m - 1, n - 1) 所有的路径和, 加上路径里面 两个最小值的绝对值 的最大值
        ans[i][j] 表示 (0, 0) -> (i,j) 最大路径和以及路径上的两个最小值
            ans[i][j][0] 表示路径和(未加上两个最小值)
            ans[i][j][1] 表示路径上的最小值
            ans[i][j][2] 表示路径上的次小值
        coins[i][j] >= 0
            ans[i][j] = Math.max(ans[i-1][j][0], ans[i][j-1][0])
        coins[i][j] < 0
            上 -> 下 ans[i-1][j]
            左 -> 有 ans[i][j-1]
            coins[i][j] >= ans[?][?][2] sum += coins[i][j] 大于等于 次小值
            coins[i][j] <= ans[?][?][1] sum += coins[?][?][2] 小于等于 最小值, 更新 次小值,
            coins[i][j] > ans[?][?][1] sum += coins[?][?][2] 大于最小值, 小于次小值, 更新次小值
        只有 top 和 left 的时候，可以像上面这样选，
        top = s1, min, nextMin
        left = s2, min, nextMin

        top left 该怎么选呢?
            s1 == s2 的情况下
                选最小值更大的，当是如果后面有一个较小值，次小值需要加到 sum 中，最小值大的，其次小值较小，这样就不是最优解了
                选两个和较大的，也会有上面相同的问题
                选次小值较大的，下一个选的时候只有次小值被添加到和里面
         */
        int m = coins.length, n = coins[0].length;

        int[][][] memo = new int[m][n][3];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }
        return dfs(m - 1, n - 1, 2, coins, memo);
    }

    public int dfs(int i, int j, int k, int[][] coins, int[][][] memo) {
        if (i < 0 || j < 0) return Integer.MIN_VALUE;
        int coin = coins[i][j];
        if (i == 0 && j == 0) {
            return k > 0 ? Math.max(0, coin) : coin;
        }
        if (memo[i][j][k] != Integer.MIN_VALUE) return memo[i][j][k];
        int res = Math.max(dfs(i - 1, j, k, coins, memo), dfs(i, j - 1, k, coins, memo)) + coin;
        if (k > 0 && coin < 0)
            res = Math.max(res, Math.max(dfs(i - 1, j, k - 1, coins, memo), dfs(i, j - 1, k - 1, coins, memo)));

        return memo[i][j][k] = res;
    }
}
