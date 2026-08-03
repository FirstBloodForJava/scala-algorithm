package com.oycm.week.lc2026.No513;

public class Solution_1 {

    /**
     * 数对的最大强度
     *
     * @param nums
     * @return
     */
    public long maxPairStrength(int[] nums) {
        /*
        给你一个整数数组 nums。
        选择恰好一对不同下标 i 和 j。该数对的强度定义为：
        (nums[i] * nums[j]) / gcd(nums[i], nums[j])^2
        返回所有可能数对中的最大强度。
        gcd(a, b) 表示 a 和 b 的最大公约数
         */
        /*
        如果 nums[i] 和 nums[j] 有最大公约数，那么相当于 (nums[i] / gcd) * (nums[j] / gcd)
        暴力
         */
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int gcd = gcd(nums[i], nums[j]);
                ans = Math.max(ans, (long) nums[i] * nums[j] / gcd / gcd);
            }
        }

        return ans;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
