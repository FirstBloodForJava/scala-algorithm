package com.oycm.month2026.august;

import java.util.Arrays;

public class Solution_9 {

    /**
     * 1140. <a href="https://leetcode.cn/problems/stone-game-ii/description/">石子游戏 II</a> 2035
     *
     * @param piles
     * @return
     */
    public int stoneGameII(int[] piles) {
        /*
        Alice 和 Bob 继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
        Alice 和 Bob 轮流进行，Alice 先开始。最初，M = 1。
        在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
        游戏一直持续到所有石子都被拿走。
        假设 Alice 和 Bob 都发挥出最佳水平，返回 Alice 可以得到的最大数量的石头。
         */
        int[] s = piles;
        int n = s.length;
        for (int i = n - 2; i >= 0; i--) {
            s[i] += s[i + 1]; // 后缀和
        }

        int[][] memo = new int[n - 1][(n + 1) / 4 + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 1, s, memo);
    }

    private int dfs(int i, int m, int[] s, int[][] memo) {
        if (i + m * 2 >= s.length) {
            return s[i]; // 全拿
        }
        if (memo[i][m] != -1) { // 之前计算过
            return memo[i][m];
        }
        int mn = Integer.MAX_VALUE;
        for (int x = 1; x <= m * 2; x++) {
            mn = Math.min(mn, dfs(i + x, Math.max(m, x), s, memo));
        }
        return memo[i][m] = s[i] - mn; // 记忆化
    }
}
