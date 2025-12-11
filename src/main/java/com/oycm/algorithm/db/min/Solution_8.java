package com.oycm.algorithm.db.min;


public class Solution_8 {

    /**
     * 2594. <a href="https://leetcode.cn/problems/minimum-time-to-repair-cars/description/">修车的最少时间</a> 1915
     * <p>
     * 能力值为 r 的机械工，能在 r * n^2 分钟内修好 n 量车
     *
     * @param ranks 整数数组，表示机械工的能力值
     * @param cars  待修车数量
     * @return 求所有机械工同时修理汽车，修理 cars 辆汽车所需的最少时间
     */
    public long repairCars(int[] ranks, int cars) {
        /*
        时间 n 和所有机械工能修理的汽车数量是 非递减的数组，可以二分答案时间，求 修理大于等于 cars 汽车数量的最小值
         */
        int min = Integer.MAX_VALUE;
        for (int rank : ranks) {
            min = Math.min(rank, min);
        }
        long l = 0;
        long r = (long) min * cars * cars + 1;

        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            if (enable(ranks, cars, mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }


        return r;
    }

    public boolean enable(int[] ranks, int cars, long s) {
        int cnt = 0;
        for (int rank : ranks) {
            // 这里计算会有精度丢失问题吗？
            cnt += Math.sqrt(s / rank);
        }
        return cnt >= cars;
    }
}
