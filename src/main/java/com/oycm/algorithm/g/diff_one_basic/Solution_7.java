package com.oycm.algorithm.g.diff_one_basic;

public class Solution_7 {

    /**
     * 1109. <a href="https://leetcode.cn/problems/corporate-flight-bookings/description/">航班预订统计</a> 1570
     *
     * @param bookings bookings[i] = [first, last, seats] 从 [first, last] 的 每个航班 上预订了 seats 个座位
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        /*
        使用差分数组
         */
        int[] ans = new int[n];
        for (int[] booking : bookings) {
            ans[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                ans[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < ans.length; i++) {
            ans[i] += ans[i - 1];
        }

        return ans;
    }

}
