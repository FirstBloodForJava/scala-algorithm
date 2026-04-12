package com.oycm.algorithm.i.subset;

import java.util.Arrays;

public class Solution_9 {

    /**
     * 2212. <a href="https://leetcode.cn/problems/maximum-points-in-an-archery-competition/description/">射箭比赛中的最大得分</a> 1869
     *
     * @param numArrows   射箭总数
     * @param aliceArrows Alice 在 [0, 11] 射箭范围
     * @return
     */
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        /*
        Alice 和 Bob 射箭比赛规则：
        1. Alice 先射 numArrows 支箭，然后 Bob 也射 numArrows 支箭
        2. 分数按下述规则计算：
            1. 箭靶有 [0, 11] 整数计分区域;
            2. 箭靶上每个区域都对应一个得分 k(0-11), Alice 和 Bob 分别在得分 k 区域射中 ak 和 bk 支箭。如果 ak >= bk, 那么 Alice 得 k 分。如果 ak < bk, 则 Bob 得 k 分
            3. 如果 ak == bk == 0 ，那么无人得到 k 分
        已知 Alice 在 [0, 11] 区域射箭数量 aliceArrows，求 Bob 获得最大总分的射箭方案，如果存在多个，任意返回一种即可
         */
        /*
        bobArrows[i] > aliceArrows[i] 才能获得 k 分
        选/不选 思路
        回溯三问：
            当前操作？枚举第 i 个 得分区域，
            子问题？从下标 >= i 区域，是否射箭，射箭射 aliceArrows[i] + 1 获得 i 分
            下一个子问题？从下标 >= i+1 区域
        递归出口 i +
         */
        dfs(aliceArrows, new int[aliceArrows.length], 0, numArrows, 0);
        // 剩余键加到任意位置
        int sum = 0;
        for (int x : bobArrows) {
            sum += x;
        }
        if (sum < numArrows) {
            bobArrows[0] += numArrows - sum;
        }
        return bobArrows;
    }

    int[] bobArrows;
    int maxScore = 0;

    public void dfs(int[] aliceArrows, int[] ans, int i, int remainArrows, int score) {
        if (i == aliceArrows.length) {
            if (score > maxScore) {
                maxScore = score;
                bobArrows = Arrays.copyOf(ans, i);
            }
            return;
        }
        dfs(aliceArrows, ans, i + 1, remainArrows, score);
        if (remainArrows - aliceArrows[i] > 0) {
            ans[i] = aliceArrows[i] + 1;
            dfs(aliceArrows, ans, i + 1, remainArrows - ans[i], score + i);
            // 回溯
            ans[i] = 0;
        }
    }




}
