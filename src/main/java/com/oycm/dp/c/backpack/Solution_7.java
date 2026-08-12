package com.oycm.dp.c.backpack;

import java.util.Arrays;

public class Solution_7 {

    /**
     * 474. <a href="https://leetcode.cn/problems/ones-and-zeroes/description/">一和零</a>
     *
     * @param strs strs.length [1, 600]; strs[i].length [1, 100]; strs[i] 仅有 0 或 1 组成
     * @param m    [1, 100]
     * @param n    [1, 100]
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        /*
        给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
        请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
        如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
         */
        /*
        题目意思：从 strs 能最多选多少个字符串，所选字符 0 个数不超过 m，1 个数不超过 n。
        记忆化搜索思路：
        定义 dfs(i, j, k) 表示从 [0, i] 中选字符串，所有字符总数 0 至多 j，1 至多 k，至多选多少字符串。
        dfs(i, j, k) =
            不选: dfs(i-1, j, k)
            选: dfs(i-1, j-i0, k-i1) + 1 要满足 j >= i0 && k >= i1
        两种情况取最大
        递归边界：i = -1 返回 0
         */
        int k = strs.length;
        int[] n0 = new int[k];
        int[] n1 = new int[k];
        for (int i = 0; i < strs.length; i++) {
            n0[i] = (int) strs[i].chars().filter(c -> c == '0').count();
            n1[i] = strs[i].length() - n0[i];
        }
        int[][][] memo = new int[k][m + 1][n + 1];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }

        return dfs(k - 1, m, n, n0, n1, memo);
    }

    public int dfs(int i, int j, int k, int[] n0, int[] n1, int[][][] memo) {
        if (i < 0) return 0;
        if (memo[i][j][k] != -1) return memo[i][j][k];

        int res = dfs(i - 1, j, k, n0, n1, memo);
        if (j >= n0[i] && k >= n1[i]) {
            res = Math.max(res, dfs(i - 1, j - n0[i], k - n1[i], n0, n1, memo) + 1);
        }
        return memo[i][j][k] = res;
    }


}
