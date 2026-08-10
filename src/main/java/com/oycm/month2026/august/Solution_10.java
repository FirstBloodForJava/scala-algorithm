package com.oycm.month2026.august;

import java.util.Arrays;

public class Solution_10 {

    /**
     * 1510. <a href="https://leetcode.cn/problems/stone-game-iv/description/">石子游戏 IV</a> 1787
     *
     * @param n
     * @return
     */
    public boolean winnerSquareGame(int n) {
        /*
        Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。
        一开始，有 n 个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意 非零 平方数 个石子。
        如果石子堆里没有石子了，则无法操作的玩家输掉游戏。
        给你正整数 n ，且已知两个人都采取最优策略。
        如果 Alice 会赢得比赛，那么返回 True ，否则返回 False 。
         */
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n, memo);
    }

    private boolean dfs(int i, int[] memo) {
        if (i == 0) {
            return false;
        }

        if (memo[i] != -1) {
            return memo[i] == 1;
        }

        for (int x = 1; x * x <= i; x++) {
            if (!dfs(i - x * x, memo)) {
                memo[i] = 1;
                return true;
            }
        }
        memo[i] = 0;
        return false;
    }
}
