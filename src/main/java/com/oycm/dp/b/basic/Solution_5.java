package com.oycm.dp.b.basic;

public class Solution_5 {

    /**
     * 3393. <a href="https://leetcode.cn/problems/count-paths-with-the-given-xor-value/description/">统计异或值为给定值的路径数目</a> 1573
     *
     * @param grid
     * @param k
     * @return
     */
    public int countPathsWithXorValue(int[][] grid, int k) {
        /*
        给你一个大小为 m x n 的二维整数数组 grid 和一个整数 k。
        你的任务是统计满足以下 条件 且从左上格子 (0, 0) 出发到达右下格子 (m - 1, n - 1) 的路径数目：
            每一步你可以向右或者向下走，也就是如果格子存在的话，可以从格子 (i, j) 走到格子 (i, j + 1) 或者格子 (i + 1, j) 。
            路径上经过的所有数字 XOR 异或值必须 等于 k。
         */
        /*
        1 <= m == grid.length <= 300
        1 <= n == grid[r].length <= 300
        0 <= grid[r][c] < 16
        0 <= k < 16
         */
        /*
        f[i][j] = f[i][j-1] + f[i-1][j]
         */
        /*
        优化一：grid 中最大值 mx，二进制长度为 L，如果 k > 2^L - 1，则一定不能满足异或和为 k
         */
        int mx = 0;
        for (int[] row : grid) {
            for (int x : row) {
                mx = Math.max(mx, x);
            }
        }
        int u = 1 << (32 - Integer.numberOfLeadingZeros(mx));
        if (k >= u) return 0;
        int mod = 1000000007;
        int m = grid.length;
        int n = grid[0].length;
        int[][][] f = new int[m + 1][n + 1][u];
        int pre = grid[0][0];
        f[1][1][pre] = 1;
        for (int j = 1; j < n; j++) {
            f[1][j + 1][pre ^ grid[0][j]] = f[1][j][pre];
            pre ^= grid[0][j];

        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = 0; x < u; x++) {
                    f[i + 1][j + 1][x ^ grid[i][j]] = (f[i + 1][j][x] + f[i][j + 1][x]) % mod;
                }
            }
        }

        return f[m][n][k];
    }


    public int dfs(int i, int j, int x, int[][] grid, int[][][] memo) {
        /*
        dfs(i, j, x) 表示从 (0, 0) 到 (i, j) 异或和为 x 的方案数
            (i, j) 可以从 (i, j-1) 或 (i-1, j) 到达，只要他们的异或和满足 y ^ grid[i][j] == x
            根据异或运算移项，y = x ^ grid[i][j]
            问题可以转换成两个子问题
                dfs(i, j-1, x ^ grid[i][j])
                dfs(i-1, j, x ^ grid[i][j])
         */
        if (i < 0 || j < 0) return 0;
        if (i == 0 && j == 0) return x == grid[i][j] ? 1 : 0;
        if (memo[i][j][x] != -1) return memo[i][j][x];
        return memo[i][j][x] = (dfs(i, j - 1, x ^ grid[i][j], grid, memo) + dfs(i - 1, j, x ^ grid[i][j], grid, memo)) % 1000000007;
    }

    public int countPathsWithXorValue_dp(int[][] grid, int k) {
        /*
        dfs 翻译成递推
        f[i][j][k] = f[i][j-1][k ^ grid[i][j] + f[i-1][j][k ^ grid[i][j]
        避免负数下标，f 定义下标 i,j 右移一位
        f[i+1][j+1][k] = f[i+1][j][k ^ grid[i][j] + f[i][j+1][k ^ grid[i][j]
        dfs(0, 0, grid[0][0]) = 1，等价 f[1][1][grid[0][0]] = 1
         */
        int mx = 0;
        for (int[] row : grid) {
            for (int x : row) {
                mx = Math.max(mx, x);
            }
        }
        int u = 1 << (32 - Integer.numberOfLeadingZeros(mx));
        if (k >= u) return 0;
        int mod = 1000000007;
        int m = grid.length;
        int n = grid[0].length;
        int[][][] f = new int[m + 1][n + 1][u];
        /*f[1][1][grid[0][0]] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 特判左上角 (0, 0)
                if (i == 0 && j == 0) continue;
                int val = grid[i][j];
                for (int x = 0; x < u; x++) {
                    f[i + 1][j + 1][x] = (f[i][j + 1][x ^ val] + f[i + 1][j][x ^ val]) % mod;
                }

            }
        }*/
        // 只有 (0, 0, grid[0][0]) 计算会访问到这里，其余都访问不了这里
        f[0][1][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                for (int x = 0; x < u; x++) {
                    f[i + 1][j + 1][x] = (f[i][j + 1][x ^ val] + f[i + 1][j][x ^ val]) % mod;
                }

            }
        }

        return f[m][n][k];
    }
}
