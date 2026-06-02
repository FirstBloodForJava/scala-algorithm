package com.oycm.month2026.june;

public class Solution_2 {

    /**
     * 3633. <a href="https://leetcode.cn/problems/earliest-finish-time-for-land-and-water-rides-i/description/">最早完成陆地和水上游乐设施的时间 I</a> 1343
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
        1 <= n, m <= 100
        1 <= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j] <= 1000
         */
        /*
        暴力做法：
        枚举第 i 个陆地游乐设施，和第 j 个水上游乐设施一起游玩，计算他的最小开始时间，为了避免不必要的等待，谁先开始就玩谁
        游乐设施可以在其开放时间开始，或 之后任意时间 开始。

         */
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {
            int landStart = landStartTime[i];

            for (int j = 0; j < waterStartTime.length; j++) {
                int waterStart = waterStartTime[j];
                if (landStart <= waterStart) {
                    ans = Math.min(ans, Math.max(landStart + landDuration[i], waterStart) + waterDuration[j]);
                } else {
                    ans = Math.min(ans, Math.max(waterStart + waterDuration[j], landStart) + landDuration[i]);
                }
            }
        }

        return ans;
    }

}
