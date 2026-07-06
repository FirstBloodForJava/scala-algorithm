package com.oycm.week.lc2026.No509;

public class Solution_1 {

    /**
     * 3982. 最大数字范围的整数之和
     * <br>
     * 3982. <a href="https://leetcode.cn/problems/sum-of-integers-with-maximum-digit-range/description/">最大数字范围的整数之和</a>
     *
     * @param nums
     * @return
     */
    public int maxDigitRange(int[] nums) {
        /*
        给你一个整数数组 nums。
        一个整数的 数字范围 定义为其 最大 数字与 最小 数字之间的差。
        例如，5724 的数字范围为 7 - 2 = 5。
        返回 nums 中所有 数字范围 等于数组中 最大数字范围 的整数之和。
         */
        /*
        枚举数字数位
         */
        int maxD = 0;
        int ans = 0;
        for (int x : nums) {
            int mx = 0;
            int mn = 9;
            int num = x;
            while (x > 0) {
                int d = x % 10;
                x /= 10;
                mx = Math.max(mx, d);
                mn = Math.min(mn, d);
            }
            int d = mx - mn;
            if (d == maxD) {
                ans += num;
            } else if (d > maxD) {
                maxD = d;
                ans = num;
            }
        }

        return ans;
    }

}
