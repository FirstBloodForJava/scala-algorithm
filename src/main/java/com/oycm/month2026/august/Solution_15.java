package com.oycm.month2026.august;

public class Solution_15 {

    /**
     * 3702. 按位异或非零的最长子序列
     *
     * @param nums
     * @return
     */
    public int longestSubsequence(int[] nums) {
        /*
        给你一个整数数组 nums。
        返回 nums 中 按位异或（XOR）计算结果 非零 的 最长子序列 的长度。如果不存在这样的 子序列 ，返回 0 。
        子序列 是一个 非空 数组，可以通过从原数组中删除一些或不删除任何元素（不改变剩余元素的顺序）派生而来。
         */
        /*
        计算整个数组的异或和，如果不为 0，答案为 n；
        如果异或和为 0，去掉一个非 0 元素，就能得到最长子序列；
         */
        int mx = Integer.MIN_VALUE;
        int xor = 0;
        for (int x : nums) {
            xor ^= x;
            mx = Math.max(mx, x);
        }
        if (xor > 0) return nums.length;
        return mx == 0 ? 0 : nums.length - 1;
    }

}
