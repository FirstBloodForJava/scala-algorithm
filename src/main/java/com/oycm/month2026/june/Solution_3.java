package com.oycm.month2026.june;

public class Solution_3 {

    /**
     * 3635. <a href="https://leetcode.cn/problems/earliest-finish-time-for-land-and-water-rides-ii/">最早完成陆地和水上游乐设施的时间 II</a> 1870
     *
     * @param landStartTime
     * @param landDuration
     * @param waterStartTime
     * @param waterDuration
     * @return
     */
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        /*
        给你两种类别的游乐园项目：陆地游乐设施 和 水上游乐设施。
        陆地游乐设施：
            landStartTime[i]：第 i 个陆地游乐设施最早可以开始的时间。
            landDuration[i]：第 i 个陆地游乐设施持续的时间。
        水上游乐设施：
            waterStartTime[j]：第 j 个水上游乐设施最早可以开始的时间。
            waterDuration[j]：第 j 个水上游乐设施持续的时间。
        一位游客必须从 每个 类别中体验 恰好一个 游乐设施，顺序 不限 。
            游乐设施可以在其开放时间开始，或 之后任意时间 开始。
            如果一个游乐设施在时间 t 开始，它将在时间 t + duration 结束。
            完成一个游乐设施后，游客可以立即乘坐另一个（如果它已经开放），或者等待它开放。
         */
        /*
        landStartTime.length == landDuration.length == n
        waterStartTime.length == waterDuration.length == m
        1 <= n, m <= 5 * 1e4
        1 <= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j] <= 1e5
         */
        /*
        题解思路：
        假设先玩 陆地游乐设施。
        贪心地，完成 陆地游乐设施 时间越早越好，这样 水上游乐设施 先开始，等待时间也短
        再遍历一次，先完成 水上游乐设施，同上计算逻辑
         */
        return Math.min(
                solve(landStartTime, landDuration, waterStartTime, waterDuration),
                solve(waterStartTime, waterDuration, landStartTime, landDuration)
        );
    }

    public int solve(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int finish = Integer.MAX_VALUE;
        int minFinish = Integer.MAX_VALUE;
        for (int i = 0; i < landDuration.length; i++) {
            minFinish = Math.min(minFinish, landStartTime[i] + landDuration[i]);
        }
        for (int i = 0; i < waterDuration.length; i++) {
            finish = Math.min(finish, Math.max(minFinish, waterStartTime[i]) + waterDuration[i]);
        }

        return finish;
    }

}
