package com.oycm.hot100.dp;

public class Solution_88 {

    /**
     * 152. <a href="https://leetcode.cn/problems/maximum-product-subarray/description/">乘积最大子数组</a>
     *
     * @param nums -10 <= nums[i] <= 10; nums.length [1, 2e4]
     * @return
     */
    public int maxProduct(int[] nums) {
        /*
        给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
        测试用例的答案是一个 32-位 整数。
        请注意，一个只包含一个元素的数组的乘积是这个元素的值。
         */
        /*
        维护两个数组
            fMax 表示 i 为右端点连续子数组的最大值
            fMin 表示 i 为右端点连续子数组的最大值
        遍历过程中
            fMax[i-1] * x, fMin[i-1] * x, x 三者取最大值
            fMax[i-1] * x, fMin[i-1] * x, x 三者取最小值
        由于只用到前一个遍历，可以使用两个变量

         */
        int ans = Integer.MIN_VALUE;
        int fMax = 1;
        int fMix = 1;
        for (int x : nums) {
            int mx = fMax;
            fMax = Math.max(Math.max(fMax * x, fMix * x), x);
            fMix = Math.min(Math.min(mx * x, fMix * x), x);
            ans = Math.max(ans, fMax);
        }

        return ans;
    }

}
