package com.oycm.month2026.march;

import java.util.Arrays;

public class Solution_9 {

    /**
     * <a href="https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-i/description/">3129. 找出所有稳定的二进制数组 I</a> 2200
     * 二进制数组稳定的条件:
     *  0 在 arr 中出现次数 恰好 为 zero 次
     *  1 在 arr 中出现次数 恰好 为 one 次
     *  arr 中每个长度超过 limit 的 子数组 都同时 包含 0 和 1 (arr 中连续 1 或 0 的长度不超过 limit, 至多有连续 limit 个 1; 至多有连续个 limit 0)
     *
     * @param zero
     * @param one
     * @param limit
     * @return
     */
    public int numberOfStableArrays(int zero, int one, int limit) {

        int[][][] memo = new int[zero + 1][one + 1][2];
        for (int[][] m : memo) {
            for (int[] m2 : m) {
                Arrays.fill(m2, -1); // -1 表示没有计算过
            }
        }
        return (dfs(zero, one, 0, limit, memo) + dfs(zero, one, 1, limit, memo)) % MOD;
    }

    private static final int MOD = 1_000_000_007;

    private int dfs(int i, int j, int k, int limit, int[][][] memo) {
        if (i == 0) {
            return k == 1 && j <= limit ? 1 : 0;
        }
        if (j == 0) {
            return k == 0 && i <= limit ? 1 : 0;
        }
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        if (k == 0) {
            // + MOD 保证答案非负
            memo[i][j][k] = (int) (((long) dfs(i - 1, j, 0, limit, memo) + dfs(i - 1, j, 1, limit, memo) +
                    (i > limit ? MOD - dfs(i - limit - 1, j, 1, limit, memo) : 0)) % MOD);
        } else {
            memo[i][j][k] = (int) (((long) dfs(i, j - 1, 0, limit, memo) + dfs(i, j - 1, 1, limit, memo) +
                    (j > limit ? MOD - dfs(i, j - limit - 1, 0, limit, memo) : 0)) % MOD);
        }
        return memo[i][j][k];
    }

}
