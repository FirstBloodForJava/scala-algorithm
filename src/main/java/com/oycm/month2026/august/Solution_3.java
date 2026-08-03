package com.oycm.month2026.august;

import java.util.Arrays;

public class Solution_3 {

    /**
     * 1406. <a href="https://leetcode.cn/problems/stone-game-iii/description/">石子游戏 III</a> 2027
     *
     * @param stoneValue
     * @return
     */
    public String stoneGameIII(int[] stoneValue) {
        /*
        Alice 和 Bob 继续他们的石子游戏。几堆石子 排成一行 ，每堆石子都对应一个得分，由数组 stoneValue 给出。
        Alice 和 Bob 轮流取石子，Alice 总是先开始。
        在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子。比赛一直持续到所有石头都被拿走。
        每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。
        比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
        假设 Alice 和 Bob 都采取 最优策略 。
        如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，分数相同返回 "Tie" 。
         */
        /*
        设 Alice 的最终得分为 a，Bob 的最终得分为 b。如果 a > b，那么 Alice 获胜；a < b Bob 获胜，否则平局。
        a > b 可以变形为 a - b > 0。问题变成只需要关注得分之差 a-b 的值。
        用得分之差来看，为了获胜，Alice 需要最大化 a-b 的值，Bob 需要最小化 a-b 的值（最大化 b-a 的值）。
        每个玩家需要最大化 自己的得分减去对手的得分。
        stoneValue 数组设为 s
        定义 dfs(i) 表示剩余 s 子数组 [i, n-1] 时，先手得分减去后手得分的最大值。
        s = [1,2,3,7]。第一回合，Alice 选择移除前 2 堆石子，那么 Alice 得分增加 sa = a[0] + a[1]。
        子问题为：对于 s' = [3,7]，Bob 如何操作，可以最大化（Bob 的得分减去 Alice 得分最大化）。
        对于 s'，设 Bob 的得分为 b'，Alice 的得分为 a'，那么子问题可以表示为： dfs(2) = b' - a'
        要计算的原问题可以表示为：dfs(0) = a - b
        由于 a = sa + a'，b = b'，dfs(0) = a - b
            dfs(0) = sa + a' - b'
                   = sa - (b' - a')
                   = sa - dfs(2)
        所以原问题和子问题的关系为
            dfs(i) =
                s[i] - dfs(i+1);
                s[i] + s[i+1] - dfs(i+2);
                s[i] + s[i+1] + s[i+2] - dfs(i+3);
        递归编辑 i = n，返回 0
         */
        int n = stoneValue.length;
        int[] memo = new int[n];
        Arrays.fill(memo, Integer.MIN_VALUE);
        int diff = dfs(stoneValue, 0, memo);
        if (diff == 0) {
            return "Tie";
        }
        return diff > 0 ? "Alice" : "Bob";
    }

    public int dfs(int[] s, int i, int[] memo) {
        if (i == s.length) {
            return 0;
        }
        if (memo[i] != Integer.MIN_VALUE) return memo[i];
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int j = i; j < Math.min(s.length, i + 3); j++) {
            sum += s[j];
            res = Math.max(res, sum - dfs(s, j + 1, memo));
        }
        return memo[i] = res;
    }

}
