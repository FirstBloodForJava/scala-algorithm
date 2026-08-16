package com.oycm.month2026.august;

public class Solution_16 {

    /**
     * 2029. <a href="https://leetcode.cn/problems/stone-game-ix/description/">石子游戏 IX</a> 2277
     *
     * @param stones
     * @return
     */
    public boolean stoneGameIX(int[] stones) {
        /*
        Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
        Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones 中移除任一石子。
            如果玩家移除石子后，导致 所有已移除石子 的价值 总和 可以被 3 整除，那么该玩家就 输掉游戏 。
            如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
        假设两位玩家均采用 最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
         */
        /*
        题解思路：
         */
        int[] cnt = new int[3];
        for (int x : stones) {
            cnt[x % 3]++;
        }

        int n = stones.length;
        // 小技巧：交换 cnt[1] 和 cnt[2] 再调用 check，相当于 Alice 第一回合移除了 2
        return check(n, cnt.clone()) || check(n, new int[]{cnt[0], cnt[2], cnt[1]});
    }

    private boolean check(int n, int[] cnt) {

        if (cnt[1] == 0) {
            return false;
        }
        cnt[1]--;
        // 第一回合 Alice 移除 1，后面两人交替移除 1 和 2，中途可以插入 cnt[0] 个 0
        int rounds = 1 + Math.min(cnt[1], cnt[2]) * 2 + cnt[0];
        if (cnt[1] > cnt[2]) { // 可以再移除一个 1
            rounds++;
        }
        return rounds < n && rounds % 2 > 0;
    }

}
