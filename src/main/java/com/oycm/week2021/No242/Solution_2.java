package com.oycm.week2021.No242;

public class Solution_2 {

    /**
     * 1870. <a href="https://leetcode.cn/problems/minimum-speed-to-arrive-on-time/description/">准时到达的列车最小时速</a> 1676
     *
     * @param dist 1 <= n <= 1e5; dist[i] [1, 1e5]
     * @param hour [1, 1e9]
     * @return
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        /*
        给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。
        要到达办公室，你必须按给定次序乘坐 n 趟列车。
        另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。
        每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
            例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
        返回能满足你在时限前到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1。
         */
        /*
        时速越快，不到一小时就能走 dist[i]，最快需要 大于 n-1 个小时，才能到达。
        车速（下标） 和 到达办公室时间（值）成反比，是一个非递增关系
            5, 8
            2, 1
        在一个递减数组中，查询 大于等于 hour，最小下标，
        [0, n-1] 计算时间是上取整
         */
        int n = dist.length;
        if (hour <= n - 1) return -1;
        int l = 0;
        int r = (int) (1e7 + 1);
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(dist, mid, hour)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public boolean check(int[] dist, int speed, double target) {
        int n = dist.length;
        int hour = 0;
        for (int i = 0; i < n - 1; i++) {
            // 上取整
            hour += (dist[i] + speed - 1) / speed;
            if (hour > target) return false;
        }

        return hour + dist[n-1] * 1.0 / speed <= target;
    }

}
