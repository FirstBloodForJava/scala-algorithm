package com.oycm.hot100.binary_search;

public class Solution_68 {

    /**
     * 4. <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/description/">寻找两个正序数组的中位数</a>
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*
        给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数。
         */
        /*
        先合并两个有序数组, 取中间
         */
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            if (nums[i] <= nums[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        while (i < m) {
            nums[k++] = nums1[i++];
        }
        while (j < n) {
            nums[k++] = nums2[j++];
        }
        double mid = nums[nums.length / 2];
        if (nums.length % 2 == 1) {
            return mid;
        }
        return (mid + nums[nums.length / 2 - 1]) / 2;
    }

}
