package com.oycm.algorithm.i.permutation;

import java.util.List;

public class Solution_3 {

    /**
     * 3376. <a href="https://leetcode.cn/problems/minimum-time-to-break-locks-i/description/">破解锁的最少时间 I</a> 1793
     * <p>
     * Bob 被困在了一个地窖里，他需要破解 n 个锁才能逃出地窖，每一个锁都需要一定的 能量 才能打开。
     *
     * @param strength 整数数组，strength[i] 表示打开第 i 个锁需要的能量, strength.length [1, 8], strength[i] [1, 1e6]
     * @param k        [1, 10]
     * @return 返回 Bob 打开所有 n 把锁需要的 最少 时间。
     */
    public int findMinimumTime(List<Integer> strength, int k) {
        /*
        Bob 有一把剑，它具备以下的特征：
            一开始剑的能量为 0。
            剑的能量增加因子 X 一开始的值为 1 。
            每分钟，剑的能量都会增加当前的 X 值。
            打开第 i 把锁，剑的能量需要到达 至少 strength[i] 。
            打开一把锁以后，剑的能量会变回 0 ，X 的值会增加一个给定的值 K 。
         */
        dfs(0, 1, k, strength, 0, new boolean[strength.size()]);
        return ans;
    }

    int ans = Integer.MAX_VALUE;

    public void dfs(int i, int x, int k, List<Integer> strength, int total, boolean[] enable) {
        /*
        枚举打开所有锁顺序的方案，计算需要的总时间
        时间计算 need / x 上取整, need + x - 1 / x
         */
        // 剪枝
        if (total >= ans) {
            return;
        }
        if (i == strength.size()) {
            ans = Math.min(ans, total);
            return;
        }
        for (int j = 0; j < strength.size(); j++) {
            if (!enable[j]) {
                enable[j] = true;
                dfs(i + 1, x + k, k, strength, total + (strength.get(j) - 1) / x + 1, enable);
                enable[j] = false;
            }
        }
    }
}
