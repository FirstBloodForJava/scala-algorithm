package com.oycm.month2026.july;

public class Solution_26 {

    /**
     * 628. <a href="https://leetcode.cn/problems/maximum-product-of-three-numbers/description/">三个数的最大乘积</a>
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        /*
        给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
         */
        /*
        第一想法是维护前三个最大值，由于 nums 中有负数，最大值可能是负数。
        讨论选的 3 个数，包含负数的情况。
        0 个负数，选三个最大的数是最优解。
        2 个负数，选 nums 中最小的两个数（负负得正），再选 nums 中最大的数。
        1 个负数，这种情况乘积是负数，要选最大的负数（绝对值最小的），以及两个最小的非负数数。如果这种选法是最优的，那么数组中恰好有 2 个非负数，
        如果有 3 个非负数，选 3 个正数更优。此时选绝对值最小的负数，和两个最小的非负数，等价选 nums 中最大的 3 个数。前两个数是正数，第三个是负数。
        3 个负数，这种情况乘积是负数，要选 3 个最大的负数（绝对值最小的）。如果这种选法是最优，说明数组全是负数。此时绝对值最小的三个数，就是前 3 个最大数。
        两个情况取最大值
            两个最小值，最大值
            三个最大值
         */
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MAX_VALUE;
        for (int x : nums) {
            // 最小值，次小值
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

}
