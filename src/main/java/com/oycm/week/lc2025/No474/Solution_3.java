package com.oycm.week.lc2025.No474;

public class Solution_3 {

    /**
     * 3733. <a href="https://leetcode.cn/problems/minimum-time-to-complete-all-deliveries/description/">完成所有送货任务的最少时间</a> 1973
     *
     * @param d
     * @param r
     * @return
     */
    public long minimumTime(int[] d, int[] r) {
        /*
        给你两个大小为 2 的整数数组：d = [d1, d2] 和 r = [r1, r2]。
        两架送货无人机负责完成特定数量的送货任务。无人机 i 必须完成 di 次送货。
        每次送货花费 正好 一小时，并且在任何给定小时内 只有一架 无人机可以送货。
        此外，两架无人机都需要在特定时间间隔进行充电，在此期间它们不能送货。无人机 i 必须每 ri 小时充电一次（即在 ri 的倍数小时进行充电）。
        返回完成所有送货所需的 最小 总时间（以小时为单位）的整数。
         */
        /*
        1 <= di <= 1e9
        2 <= ri <= 3 * 1e4
         */
        /*
        怎么判定一个时间能完成送货？
         */
        /*
        t - t/r1 >= d1
        t - t/r2 >= d2
        t - t/l >= d1 + d2
        t - t/r >= d，d 表示运行多少个小时，r 表示第几个小时要充电，已知 r，d，求最小的 t。
        直接使用不等式，不好就算 t 的最小值，换个思路，计算需要几个小时充电。
        要执行 d 次，那么没执行 r-1 次，就需要充电一次，但是当 d 能整除 r-1 时，以及 d % (r-1) > 0 时，最后一次是不需要充电的。
        相当于 d / (r-1) 上取整后，再减少 1，相当于 (d-1)/(r-1)，就是充电等待时间
        都大于这个最小值 d + (d-1)/(r-1)
         */
        int d1 = d[0], d2 = d[1];
        int r1 = r[0], r2 = r[1];
        int l = lcm(r1, r2);

        return Math.max(Math.max(f(d1, r1), f(d2, r2)), f(d1 + d2, l));
    }

    public long f(int d, int r) {
        return (long) d + (d - 1) / (r - 1);
    }

    public int gcd(int a, int b) {
        while (a != 0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

    public int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

}
