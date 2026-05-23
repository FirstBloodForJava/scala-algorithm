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

class Solution_300 {

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