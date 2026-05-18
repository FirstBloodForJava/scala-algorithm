package com.oycm.dualweek2020.No19;

public class Solution_3 {

    /**
     * 1344.
     * <a href="https://leetcode.cn/problems/angle-between-hands-of-a-clock/description/">时钟指针的夹角</a> 1325
     *
     * @param hour    [1, 12]
     * @param minutes [0, 59]
     * @return
     */
    public double angleClock(int hour, int minutes) {
        /*
        给你两个数 hour 和 minutes 。请你返回在时钟上，由给定时间的时针和分针组成的较小角的角度（60 单位制）。
         */
        /*
        minutes / 5 表示多少格，乘以 30 就是顺时针转了多少度
        hour 乘以 30 就是小时转了多少度，需要加上 minutes / 60 * 30
        两者就算绝对值差，和 360 - 绝对值差取最小值即可
        hour = (hour + minutes / 60) * 30;
        minute = (minutes / 5) * 30;
        避免前面计算丢失过多精度
        hour *= 2
        minute *= 2
        min(abs(hour - minute), 720 - abs(hour - minute)) / 2
        都是整数 一分钟表示一小时移动 0.5 度
         */
        double minuteAngle = minutes * 6;
        double hourAngle = (hour % 12) * 30 + minuteAngle / 2.0;

        double abs = Math.abs(hourAngle - minuteAngle);

        return Math.min(abs, 360 - abs);
    }

}
