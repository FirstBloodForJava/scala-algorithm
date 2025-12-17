package com.oycm.algorithm.h.andor;


public class Solution_5 {

    /**
     * 2401. <a href="https://leetcode.cn/problems/longest-nice-subarray/description/">最长优雅子数组</a> 1750
     * <p>
     * nums 的子数组中位于 不同 位置的每对元素按位 与（AND）运算的结果等于 0, 则称该子数组为优雅子数组
     *
     * @param nums 正整数数组
     * @return 求最长优雅子数组长度
     */
    public static int longestNiceSubarray(int[] nums) {
        /*
        题解-再刷
        暴力做法是 [i, n] 后面的元素, nums[i] 最大为 10^9 长度为 30
        nums[i] & nums[i+1] ... nums[j] 的数量最多不会超过 30 个 (2^0, 2^1 ... 2^29)
        是不是可以考虑使用滑动窗口 [0, 1] 的下标对大于0，l = 0 这个点不可能和其他下标组成更长的子数组
        滑动窗口该怎么滑呢？nums[l] & nums[r] > 0 l++, 否则更新答案 r - l + 1
         */
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int xor = 0;
            int j = i;
            while (j >= 0 && (xor & nums[j]) == 0) {
                // 记录 1
                xor |= nums[j];
                j--;
            }
            ans = Math.max(ans, i - j);
        }

        return ans;
    }

    public static int slidingWindow(int[] nums) {
        /*
        记录 [l, r-1] 前面 已经有 1 的情况 xor & nums[r] > 0 则左边有元素不符合要求
         */
        int ans = 0;
        int n = nums.length, l = 0, r = 0, xor = 0;
        while (r < n) {
            while ((xor & nums[r]) > 0) {
                xor ^= nums[l];
                l++;
            }
            // 添加
            ans = Math.max(ans, r - l + 1);
            xor |= nums[r];
            r++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(1000000000));
        System.out.println(Integer.toBinaryString(1000000000).length());
    }

}
