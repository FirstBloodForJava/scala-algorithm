package com.oycm.month2026.august;

public class Solution_17 {

    /**
     * 1563. <a href="https://leetcode.cn/problems/stone-game-v/description/">石子游戏 V</a> 2087
     *
     * @param stoneValue
     * @return
     */
    public int stoneGameV(int[] stoneValue) {
        /*
        几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。
        游戏中的每一轮：
            Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；
            Bob 负责计算每一行的值，即此行中所有石子的值的总和。
            Bob 会丢弃值最大的行，Alice 的得分为剩下那行的值（每轮累加）。
            如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。
        只 剩下一块石子 时，游戏结束。Alice 的分数最初为 0 。
        返回 Alice 能够获得的最大分数 。
         */
        int n = stoneValue.length;
        // 前缀和
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stoneValue[i];
        }

        int[][] memo = new int[n][n + 1];
        return dfs(0, n, sum, memo);
    }

    private int dfs(int i, int j, int[] sum, int[][] memo) {
        if (j - i == 1) { // 只剩下一块石子，游戏结束
            return 0;
        }

        if (memo[i][j] > 0) { // 之前计算过
            return memo[i][j];
        }

        int res = 0;

        // 把子数组 [i,j) 分成 [i,k) 和 [k,j)
        for (int k = i + 1; k < j; k++) {
            int sumL = sum[k] - sum[i];
            int sumR = sum[j] - sum[k];
            int score;
            if (sumL < sumR) { // Bob 丢弃 [k,j)，剩下 [i,k)
                score = dfs(i, k, sum, memo) + sumL;
            } else if (sumL > sumR) { // Bob 丢弃 [i,k)，剩下 [k,j)
                score = dfs(k, j, sum, memo) + sumR;
            } else { // sumL = sumR，由 Alice 决定丢弃哪边
                score = Math.max(dfs(i, k, sum, memo), dfs(k, j, sum, memo)) + sumL;
            }
            res = Math.max(res, score);
        }

        memo[i][j] = res; // 记忆化
        return res;
    }

}
