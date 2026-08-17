package com.oycm.dp.c.group_knapsack;

import java.util.Arrays;

public class Solution_1 {

    /**
     * 1155. <a href="https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/description/">掷骰子等于目标和的方法数</a> 1654
     *
     * @param n
     * @param k
     * @param target
     * @return
     */
    public int numRollsToTarget(int n, int k, int target) {
        /*
        这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
        给定三个整数 n、k 和 target，请返回投掷骰子的所有可能得到的结果（共有 k^n 种方式），使得骰子面朝上的数字总和等于 target。
        由于答案可能很大，你需要对 109 + 7 取模。
         */
        /*
        必须要投 n 次，n 个骰子恰好都掷 一次。
        dfs(i, j)
         */
        if (target < n || target > n * k) {
            return 0;
        }
        int[][] memo = new int[n + 1][target - n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(n, target - n, k, memo);
    }


    public int dfs(int i, int j, int k, int[][] memo) {
        if (i == 0) {
            return j == 0 ? 1 : 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        for (int x = 0; x < k && x <= j; x++) {
            res = (res + dfs(i - 1, j - x, k, memo)) % 1000000007;
        }

        return memo[i][j] = res;
    }

}
