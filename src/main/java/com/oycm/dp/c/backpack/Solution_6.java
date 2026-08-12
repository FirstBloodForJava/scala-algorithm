package com.oycm.dp.c.backpack;

import java.util.Arrays;

public class Solution_6 {

    /**
     * 3180. <a href="https://leetcode.cn/problems/maximum-total-reward-using-operations-i/description/">执行操作可获得的最大总奖励 I</a>
     *
     * @param rewardValues rewardValues[i] [1, 2000]; rewardValues.length [1, 2000]
     * @return
     */
    public int maxTotalReward(int[] rewardValues) {
        /*
        给你一个整数数组 rewardValues，长度为 n，代表奖励的值。
        最初，你的总奖励 x 为 0，所有下标都是 未标记 的。你可以执行以下操作 任意次 ：
            从区间 [0, n - 1] 中选择一个 未标记 的下标 i。
            如果 rewardValues[i] 大于 你当前的总奖励 x，则将 rewardValues[i] 加到 x 上（即 x = x + rewardValues[i]），并 标记 下标 i。
        以整数形式返回执行最优操作能够获得的 最大 总奖励。
         */
        /*
        和选择下标顺序没有关系，可以对数组排序。
        定义 f[i][j] 表示 从 [0, i] 是否能得到总奖励 j。
        f[i][j] = f[i-1][j] || f[i-1][j-x]; x = nums[i] 且 x > j-x && j - x >= 0;
        j 的访问多大呢？数组最大值记为 m。最大为 2m-1。
        如果最大值能选，那么前面选的奖励至多 m-1，最大为 2m-1
         */
        Arrays.sort(rewardValues);
        boolean[] f = new boolean[2 * rewardValues[rewardValues.length - 1]];
        f[0] = true;
        for (int x : rewardValues) {
            for (int j = 2 * x - 1; j >= x; j--) {
                if (f[j - x]) {
                    f[j] = true;
                }
            }
        }
        for (int i = f.length - 1; i >= 0; i--) {
            if (f[i]) return i;
        }
        return 0;
    }

}
