package com.oycm.month2026.april;

public class Solution_19 {

    /**
     * 1855. <a href="https://leetcode.cn/problems/maximum-distance-between-a-pair-of-values/description/">下标对中的最大距离</a> 1515
     * <p>
     * 非递增 数组: 每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立
     *
     * @param nums1 非递增 的整数数组
     * @param nums2 非递增 的整数数组
     * @return 求有效下标对 (i, j) 的最大距离
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        /*
        下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length。
        如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i 。
         */
        /*
        非递增数组，左边越来越大，对于 nums2[j] 如果，nums[i] <= nums[i] 则 [i, n-1] 都成立，i 如果能尽量左移最好
        在非递减数组中查找 小于等于 nums[j] 的 最小下标
         */
        int ans = 0;

        for (int j = 0; j < nums2.length; j++) {
            int i = binarySearch(nums1, nums2[j], nums2.length);
            // 未考虑 i == nums1.length, nums2.length > nums1.length
            ans = Math.max(ans, j - i);
        }

        return ans;
    }

    public int binarySearch(int[] nums, int target, int m) {
        int l = -1;
        int r = nums.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r == nums.length ? m : r;
    }

}
