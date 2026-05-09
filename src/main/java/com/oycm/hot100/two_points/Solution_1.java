package com.oycm.hot100.two_points;

public class Solution_1 {

    /**
     * 283. <a href="https://leetcode.cn/problems/move-zeroes/description/">移动零</a>
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        /*
        给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
         */
        /*
        初始化 idx = 0，用来表示 nums 已知的非零元素个数，记录元素个数的同时也更新 nums，最终在数组后面补 0.
         */
        int n = nums.length;
        int idx = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[idx++] = num;
            }
        }
        while (idx < n) {
            nums[idx++] = 0;
        }
        /*
        双指针做法：nums 从左到右遍历，同时维护另一个下标 i0（初始值 0），并保证区间 [i0, i-1] 都是空位（0）
        每次遇到 nums[i] == 0，i0 和 i 交换位置，i0++,i++，保证 [i0, i-1] 都是空位（0）
         */

    }

}
