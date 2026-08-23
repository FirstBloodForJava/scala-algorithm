package com.oycm.month2026.august;

public class Solution_23 {

    /**
     * 1927. <a href="https://leetcode.cn/problems/sum-game/description/">求和游戏</a> 2005
     *
     * @param num
     * @return
     */
    public boolean sumGame(String num) {
        /*
        Alice 和 Bob 玩一个游戏，两人轮流行动，Alice 先手。
        给你一个 偶数长度 的字符串 num ，每一个字符为数字字符或者 '?' 。
        每一次操作中，如果 num 中至少有一个 '?' ，那么玩家可以执行以下操作：
            选择一个下标 i 满足 num[i] == '?' 。
            将 num[i] 用 '0' 到 '9' 之间的一个数字字符替代。
        当 num 中没有 '?' 时，游戏结束。
        Bob 获胜的条件是 num 中前一半数字的和 等于 后一半数字的和。Alice 获胜的条件是前一半的和与后一半的和 不相等 。
        比方说，游戏结束时 num = "243801" ，那么 Bob 获胜，因为 2+4+3 = 8+0+1 。
        如果游戏结束时 num = "243803" ，那么 Alice 获胜，因为 2+4+3 != 8+0+3 。
        在 Alice 和 Bob 都采取 最优 策略的前提下，如果 Alice 获胜，请返回 true ，如果 Bob 获胜，请返回 false 。
         */
        /*
        题解：博弈
         */
        char[] s = num.toCharArray();
        int n = s.length;
        int d = 0;
        for (int i = 0; i < n; i++) {
            int x = s[i] == '?' ? 9 : (s[i] - '0') * 2;
            d += i < n / 2 ? x : -x;
        }
        return d != 0;
    }

}
