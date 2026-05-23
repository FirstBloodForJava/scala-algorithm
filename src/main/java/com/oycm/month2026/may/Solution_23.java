package com.oycm.month2026.may;

public class Solution_23 {

    /**
     * 1752. <a href="https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated/description/">检查数组是否经排序和轮转得到</a> 1325
     *
     * @param nums
     * @return
     */
    public boolean check(int[] nums) {
        /*
        给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
        如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
        数组 A 在轮转 x 个位置后得到长度相同的数组 B ，使得对于每一个有效的下标 i，满足 B[i] == A[(i+x) % A.length]。
         */
        /*
        判断数组是不是两段有序，或一段有序
        如果数组是经过旋转的则 nums[0] >= nums[n-1]，且旋转 x 的后的 nums[x] > nums[x-1] 至多出现一次
        如果未旋转，则 不能 出现 nums[i] > nums[i+1] 的情况
         */
        int n = nums.length;
        boolean sorted = nums[0] >= nums[n - 1];
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                // 之前出现过严格递减，或数组未旋转，不能出现严格递减
                if (!sorted) return false;
                sorted = false;
            }
        }

        return true;
    }

}
