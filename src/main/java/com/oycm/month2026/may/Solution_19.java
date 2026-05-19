package com.oycm.month2026.may;

public class Solution_19 {

    /**
     * 2540. <a href="https://leetcode.cn/problems/minimum-common-value/description/">最小公共值</a> 1250
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int getCommon(int[] nums1, int[] nums2) {
        /*
        两个整数数组 nums1 和 nums2 ，它们已经按非降序排序，请你返回两个数组的 最小公共整数。
        如果两个数组 nums1 和 nums2 没有公共整数，请你返回 -1。
        如果一个整数在两个数组中都 至少出现一次 ，那么这个整数是数组 nums1 和 nums2 公共 的。
         */
        /*
        初始化 nums1 左指针 i = 0，num2 左指针 j = 0；
        分类讨论：
            如果 nums1[i] == nums2[j]，由于数组是非递减的，此时就是最小公共值，返回 nums1[i]
            如果 nums1[i] < nums2[j]，nums1 的 [0, i] 都不是小于 nums2[j]（数组非递减），i 右移，从[i+1, n-1] 在筛选，i++
            如果 nums1[i] > nums2[j]，同上，j++
         */
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }

        return -1;
    }

}
