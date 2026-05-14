package com.oycm.month2026.may;

public class Solution_14 {

    /**
     * 2784. <a href="https://leetcode.cn/problems/check-if-array-is-good/description/">检查数组是否是好的</a> 1376
     *
     * @param nums 整数数组
     * @return
     */
    public boolean isGood(int[] nums) {
        /*
        一个整数数组 nums ，如果它是数组 base[n] 的一个排列，我们称它是个 好 数组。
        base[n] = [1, 2, ..., n - 1, n, n] （换句话说，它是一个长度为 n + 1 且包含 1 到 n - 1 恰好各一次，包含 n  两次的一个数组）。
        比方说，base[1] = [1, 1] ，base[3] = [1, 2, 3, 3] 。
        如果数组是一个好数组，请你返回 true ，否则返回 false 。
        注意：数组的排列是这些数字按任意顺序排布后重新得到的数组。
         */
        /*
        base[n], n = nums.length - 1
        nums[i] >= n, 不符合要求
        base[i] 初始次数都为 0，出现一次后，次数减少 1，如果减少后，次数小于 -1
         */
        int n = nums.length;

        int[] base = new int[n];
        base[n - 1] = 1;
        for (int i : nums) {
            if (i >= n) {
                return false;
            }
            base[i]--;
            if (base[i] < -1) {
                return false;
            }
        }

        return true;
    }

}
