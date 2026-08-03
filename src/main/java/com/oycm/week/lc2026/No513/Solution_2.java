package com.oycm.week.lc2026.No513;

public class Solution_2 {

    /**
     * 按奇偶比统计子数组 I
     *
     * @param nums
     * @param a
     * @param b
     * @return
     */
    public int countRatioSubarrays(int[] nums, int a, int b) {
        /*
        给你一个整数数组 nums，以及两个整数 a 和 b。
        对于一个子数组，定义：
            x 表示其中偶数元素的数量。
            y 表示其中奇数元素的数量。
        子数组中偶数与奇数的比例定义为 x / y，其中该比例按照精确的有理数值进行比较。
        如果一个子数组满足以下条件，则称其为有效子数组：
            y > 0，并且
            x / y <= a / b。
        返回 nums 中有效子数组的数量。
        子数组是数组中一个连续的非空元素序列。
         */
        /*
        1 <= nums.length <= 1000
        1 <= nums[i] <= 1000
        1 <= a, b <= 1000
         */
        /*
        暴力
         */
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int odd = 0, even = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
                if (odd > 0 && even * b <= a * odd) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
