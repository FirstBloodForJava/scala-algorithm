package com.oycm.dp.d.lis.basic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution_1 {

    /**
     * 300. <a href="https://leetcode.cn/problems/longest-increasing-subsequence/description/">最长递增子序列</a>
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        /*
        给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
        子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
         */
        /*
        把 nums 去重排序后得到数组 nums2，求 nums 和 nums2 的最长公共子序列
         */
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        int m = 0;
        int[] nums2 = new int[set.size()];
        for (Integer x : set) {
            nums2[m++] = x;
        }
        Arrays.sort(nums2);
        int[] f = new int[m + 1];

        for (int i = 0; i < n; i++) {
            int pre = f[0];
            for (int j = 0; j < m; j++) {
                int temp = f[j + 1];
                f[j + 1] = nums[i] == nums2[j] ? pre + 1 : Math.max(f[j], f[j + 1]);
                pre = temp;
            }
        }

        return f[m];
    }

}

class Solution_300_1 {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) f[i] = Math.max(f[i], f[j]);
            }
            f[i]++;
            ans = Math.max(f[i], ans);
        }

        return ans;
    }
}

class Solution_300_2 {


    public int lengthOfLIS(int[] nums) {
        /*
        定义数组 g[i] 表示长度为 i+1 子序列末尾元素的最小值。
        g[i] 是严格递增的。
            如果 g[i] >= g[j] 且 i < j，那么 g[j] 对应子序列第 i 个数应该 小于 g[j]，与 g[i] >= g[j] 矛盾。
        一次只能更新个位置
            如果更新两个位置，g 中就存在两个相同的元素，导致 g 不是严格递增的。
        更新的位置，是第一个大于等于 nums[i] 数的下标，如果 nums[i] 比 g 的最后一个数都大，则添加到末尾。
         */
        int gn = 0;
        for (int x : nums) {
            int j = lowerBound(nums, gn, x);
            nums[j] = x;
            if (j == gn) {
                gn++;
            }
        }

        return gn;
    }

    public int lowerBound(int[] nums, int r, int target) {
        int l = -1;
        while (l + 1 < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] < target){
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }

}