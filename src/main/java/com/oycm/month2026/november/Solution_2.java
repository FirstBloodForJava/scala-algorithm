package com.oycm.month2026.november;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 462. <a href="https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/description/">最小操作次数使数组元素相等 II</a>
     *
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        /*
        给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最小操作数。
        在一次操作中，你可以使数组中的一个元素加 1 或者减 1 。
        测试用例经过设计以使答案在 32 位 整数范围内。
         */
        /*
        假设 nums 数组按升序排序，
        最终元素取 x > nums[n-1]，如果 x 减少，操作数是能够减少的，不是最优。
        最终元素取 x < nums[0]，如果 x 增加，操作数是能够减少的，不是最优。
        x 取 [nums[0], nums[n-1]] 中的值，对于 x 取任意值，nums[0], nums[n-1]; nums[1], nums[n-2] 这样一对元素，操作次数是固定的。
        x 取 nums 数组的中位数最优，如果 n 是偶数，取任意一个都可以，n 是奇数，取自己即可
         */
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];
        int ans = 0;
        for (int x : nums) {
            ans += Math.abs(x - mid);
        }
        return ans;
    }

}
