package com.oycm.dp.f.basic;

public class Solution_4 {

    /**
     * 2708. <a href="https://leetcode.cn/problems/maximum-strength-of-a-group/description/">一个小组的最大实力值</a> 1502
     *
     * @param nums nums.length [1, 13]; -9 <= nums[i] <= 9
     * @return
     */
    public long maxStrength(int[] nums) {
        /*
        给你一个下标从 0 开始的整数数组 nums ，它表示一个班级中所有学生在一次考试中的成绩。
        老师想选出一部分同学组成一个 非空 小组，且这个小组的 实力值 最大，如果这个小组里的学生下标为 i0, i1, i2, ... , ik ，那么这个小组的实力值定义为 nums[i0] * nums[i1] * nums[i2] * ... * nums[ik]。
        请你返回老师创建的小组能得到的最大实力值为多少。
         */
        /*
        偶数个最小奇数 * 所有偶数
        分类讨论，从最大积的正负性入手
         */
        long prod = 1;
        int zeros = 0;
        int positives = 0;
        int negatives = 0;
        int mxNeg = -9;
        for (int x : nums) {
            if (x < 0) {
                negatives++;
                prod *= x;
                mxNeg = Math.max(mxNeg, x);
            } else if (x > 0) {
                prod *= x;
                positives++;
            } else {
                zeros++;
            }
        }
        // 只有一个负数
        if (negatives == 1 && zeros == 0 && positives == 0) {
            return nums[0];
        }
        // 只有一个负数和至少一个 0
        if (negatives == 1 && positives == 0) {
            return 0;
        }
        // 乘积大于 0
        if (prod > 0) {
            return prod;
        }
        // 乘积小于 0，除以最大负数
        return prod / mxNeg;

    }

    public long maxStrength_dp(int[] nums) {
        // mn, mx 表示 nums[0, i] 子数组所选元素中得到的最小值, 最大值
        long mn = nums[0];
        long mx = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            long tmp = mn;
            /*
            nums[0, i-1] => nums[0, i], 最大积计算逻辑：
            不选 nums[i], 为 [0, i-1] 最大积；
            选 nums[i]，有以下情况：
                nums[i] 单独作为最大积；
                nums[i] > 0，选 [0, i-1] 前面所选元素的最大乘积相乘；
                nums[i] < 0，选 [0, i-1] 前面所选元素的最小乘积相乘；
             */
            mn = Math.min(Math.min(mn, x), Math.min(mn * x, mx * x));
            mx = Math.max(Math.max(mx, x), Math.max(tmp * x, mx * x));
        }
        return mx;
    }

}
