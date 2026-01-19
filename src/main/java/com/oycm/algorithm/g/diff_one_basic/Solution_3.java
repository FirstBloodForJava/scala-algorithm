package com.oycm.algorithm.g.diff_one_basic;

public class Solution_3 {

    /**
     * 1854. <a href="https://leetcode.cn/problems/maximum-population-year/">人口最多的年份</a> 1370
     * <p>
     * 年份 x 的人口定义为这一年期间活着的人的数目。第 i 个进入年份 x 的人口需要满足，x 在 [logs[i][0], logs[i][1] - 1] 内
     *
     * @param logs logs[i][0, 1] 表示 第 i 个人的出生和死亡年份
     * @return 人口最多 且 最早 的年份
     */
    public int maximumPopulation(int[][] logs) {
        /*
        维护一个 max(logs[i][1]) 的 差分数组 d, 原数组 a [birth, death-1] 数组都 加1, a[death] - 1
        a[i] 表示 i 年存活的人数，还原数组的过程中，不断计算 a[i] 最大值第一次出现的 index
         */
        int max = 0;
        for (int[] log : logs) {
            max = Math.max(max, log[1]);
        }
        int start = 1950;
        int[] d = new int[max + 1 - start];
        for (int[] log : logs) {
            d[log[0] - start] += 1;
            d[log[1] - start] -= 1;
        }
        int s = 0;
        int maxLog = 0;
        int minIndex = 0;
        for (int i = 0; i < d.length; i++) {
            s += d[i];
            if (s > maxLog) {
                maxLog = s;
                minIndex = i;
            }
        }

        return minIndex + start;
    }
}
