package com.oycm.dp.d.lis.basic;

public class Solution_2 {

    /**
     * 334. <a href="https://leetcode.cn/problems/increasing-triplet-subsequence/description/">递增的三元子序列</a>
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        /*
        给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的 递增子序列。
        如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
         */
        /*
        递增子序列的子序列一定是递增的，求最长递增子序列的过程，判断子序列长度是否存在 等于 3 递增子序列
         */
        int gn = 0;
        for (int x : nums) {
            // f[i] 表示 长为 i+1 的递增子序列，末尾元素的最小值
            int j = lowerBound(nums, gn, x);
            nums[j] = x;
            if (j == gn) {
                gn++;
            }
            if (gn == 3) return true;

        }
        return false;
    }

    public int lowerBound(int[] nums, int r, int target) {
        // 开区间，查找 第一个 大于等于 target 的下标
        int l = -1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
