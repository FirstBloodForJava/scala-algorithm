package com.oycm.dp.c.complete_backpack;

import java.util.ArrayList;
import java.util.List;

public class SolutionExtend_1 {

    /**
     * 3592. <a href="https://leetcode.cn/problems/inverse-coin-change/description/">硬币面值还原</a> 1701
     *
     * @param numWays
     * @return
     */
    public List<Integer> findCoins(int[] numWays) {
        /*
        给你一个 从 1 开始计数 的整数数组 numWays，其中 numWays[i] 表示使用某些 固定 面值的硬币（每种面值可以使用无限次）凑出总金额 i 的方法数。
        每种面值都是一个 正整数 ，并且其值 最多 为 numWays.length。
        然而，具体的硬币面值已经 丢失 。你的任务是还原出可能生成这个 numWays 数组的面值集合。
        返回一个按从小到大顺序排列的数组，其中包含所有可能的 唯一 整数面值。
        如果不存在这样的集合，返回一个 空 数组。
         */
        /*
        numWays 数组就是 完全背包 的结果数组，相当于使用该数组得到硬币数组
        f[i] += f[i-c]，c 表示硬币值
        f[0] = 1, 第一个最小的硬币一定满足 f[i] = 1;
         */
        int n = numWays.length;
        int[] f = new int[n + 1];
        f[0] = 1;
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            // numWays[i] 表示使用硬币凑出金额为 i+1 的方法数
            int way = numWays[i - 1];
            if (f[i] == way) {
                // 没有符合要求的币值
                continue;
            }
            // 凑出金额较小的方法数，一定是先选金额小的，和他的方案数，第一次一定是差 1
            if (way - 1 != f[i]) {
                return List.of();
            }
            ans.add(i);
            for (int j = i; j <= n; j++) {
                f[j] += f[j - i];
            }
        }
        return ans;
    }
}
