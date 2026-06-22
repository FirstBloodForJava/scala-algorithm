package com.oycm.week.lc2021.No242;

public class Solution_2 {

    /**
     * 1870. <a href="https://leetcode.cn/problems/minimum-speed-to-arrive-on-time/description/">准时到达的列车最小时速</a> 1676
     *
     * @param dist 1 <= n <= 1e5; dist[i] [1, 1e5]
     * @param hour [1, 1e9]，最多存在两位数字
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
        优化：把浮点数转换成整数
         */
        int n = dist.length;
        if (hour <= n - 1) return -1;
        long h100 = Math.round(hour * 100);

        int maxDist = 0;
        long sumDist = 0;
        for (int d : dist) {
            sumDist += d;
            maxDist = Math.max(maxDist, d);
        }
        long diff = h100 - (n - 1) * 100;
        /*
        如果 hour <= n，前面趟列车控制在 1 小时内，速度至少为 max(dist)
        最后一趟车，速度需满足 v / dist[n-1] <= hour - (n - 1)
        (hour - (n - 1)) * v >= dist[n-1]
        两者之间取最大值
        (h100 - (n-1) * 100) * v >= dist[n-1] * 100 => v = dist[n-1] * 100 / diff 上取整
         */
        if (diff <= 100) {
            return Math.max(maxDist, (int) ((dist[n - 1] * 100 - 1) / diff) + 1);
        }

        /*
        计算区间：
            开区间左端点：假设不考虑等待时间，需要 sumDist / h 时间才能到达，v < sumDist * 100 / h100（上取整），可能到达，速度再减 1，肯定不符合要求。
            开区间右端点：假设所有距离都是 maxDist，则每趟列车至多分配时间 h = h100 / n100（下取整），则 v * h >= maxDist，v >= maxDist / h（上取整），则一定符合要求。
                注意：这里 maxDist / h 时，不用再除以 100，计算平均分配时间时，已经除掉了
         */

        int l = (int) ((sumDist * 100 - 1) / h100);
        int h = (int) (h100 / (n * 100));
        int r = (maxDist - 1) / h + 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(dist, mid, h100)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public boolean check(int[] dist, int speed, long h100) {
        int n = dist.length;
        long hour = 0;
        for (int i = 0; i < n - 1; i++) {
            // 上取整
            hour += (dist[i] - 1) / speed + 1;

        }
        /*
        h + dist[n-1] / v <= h100
        hv + dist[n-1] <= h100 * v;
         */
        return (hour * speed + dist[n - 1]) * 100 <= h100 * speed;
    }

}
