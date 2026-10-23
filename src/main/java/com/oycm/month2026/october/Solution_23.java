package com.oycm.month2026.october;

public class Solution_23 {

    /**
     * 3644. <a href="https://leetcode.cn/problems/maximum-k-to-sort-a-permutation/description/">排序排列</a> 1775
     *
     * @param nums
     * @return
     */
    public int sortPermutation(int[] nums) {
        /*
        给你一个长度为 n 的整数数组 nums，其中 nums 是范围 [0..n - 1] 内所有数字的一个 排列 。
        你可以在满足条件 nums[i] AND nums[j] == k 的情况下交换下标 i 和 j 的元素，其中 AND 表示按位与操作，k 是一个非负整数。
        返回可以使数组按 非递减 顺序排序的最大值 k（允许进行任意次这样的交换）。如果 nums 已经是有序的，返回 0。
        排列 是数组所有元素的一种重新排列。
         */
        /*
        题解思路：
        思考构造有序数组的过程
         */
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x != i) {
                ans &= x;
            }
        }

        return Math.max(ans, 0);
    }

}
