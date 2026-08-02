package com.oycm.month2026.august;

public class Solution_2 {

    /**
     * 877. <a href="https://leetcode.cn/problems/stone-game/description/">石子游戏</a> 1590
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        /*
        Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i] 。
        游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
        Alice 和 Bob 轮流进行，Alice 先开始。
        每回合，玩家从行的 开始 或 结束 处取走整堆石头。
        这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
        假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。
         */
        /*
        设所有偶数下标之和为 s0，所有奇数下标之和为 s1。
        Alice 选择策略如下：
            如果 s0 > s1，第一回合 Alice 选择 p[0]。此时 p[1, n-1] 待选，首尾都是奇数，Bob 只能选择奇数下标的石头堆任选一个。
            接下来 Alice 的策略是，如果 Bob 取走左边下标，Alice 也选择左边下标；如果 Bob 取走右边下标，Alice 也选择取走右边下标。
            这样能保证 Alice 能选择所有偶数下标，Bob 只能取走所以奇数下标， 所有 Alice 必胜。

            如果 s0 < s1，第一回合 Alice 选择 p[n-1]。此时 p[0, n-2] 待选，首尾都是偶数，Bob 只能选择偶数下标的石头堆任选一个。
            接下来 Alice 的策略是，如果 Bob 取走左边下标，Alice 也选择左边下标；如果 Bob 取走右边下标，Alice 也选择取走右边下标。
            这样能保证 Alice 能选择所有奇数下标，Bob 只能取走所有奇数下标，所以 Alice 必胜。

            由于 sum[piles] 和为 奇数，s0 = s1 不可能成立
         */
        return true;
    }

}
