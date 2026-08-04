package com.oycm.week.lc2025.No474;

public class Solution_2 {

    /**
     * 3732. <a href="https://leetcode.cn/problems/maximum-product-of-three-elements-after-one-replacement/description/">一次替换后的三元素最大乘积</a> 1529
     *
     * @param nums
     * @return
     */
    public static long maxProduct(int[] nums) {
        /*
        给你一个整数数组 nums。
        你 必须 将数组中的 恰好一个 元素替换为范围 [-1e5, 1e5]（包含边界）内的 任意 整数。
        在进行这一替换操作后，请确定从修改后的数组中选择 任意三个互不相同的下标 对应的元素所能得到的 最大乘积。
        返回一个整数，表示可以达到的 最大乘积 。
         */
        /*
        由于可以对数组中任意一个元素就行替换，所以可以找到 nums 两个数的最大值、最小值
        mx * 1e5，mn * -1e5 两者取最大值
        不用考虑最大值是否大于 0，
            如果 mx < 0，那么 mn < 0 一定成立，mn * -1e 取最大
        问题转换成，怎么在 nums 中求两数乘积最大值和最小值，求两数乘积最大绝对值。
        如果最大绝对原始值是负数，可以 * -1e5。
        abs(mx1 * mx2), abs(mn1, mn2), abs(mx1 * mn1) 三者取最大值
        也可以转换成，求 nums 中，绝对值最大值的两个数
         */
        int mx1 = Integer.MIN_VALUE;
        int mx2 = Integer.MIN_VALUE;
        for (int num : nums) {
            num = Math.abs(num);
            if (num > mx1) {
                mx2 = mx1;
                mx1 = num;
            } else if (num > mx2) {
                mx2 = num;
            }
        }
        return mx1 * mx2 * 100000L;
    }

    public static void main(String[] args) {
        int[] nums = {-100000, -100000, -100000};
        System.out.println(maxProduct(nums));
    }

}
