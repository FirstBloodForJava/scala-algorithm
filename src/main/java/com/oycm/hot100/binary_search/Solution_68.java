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
        双指针题解思路：
        均匀数组：从 nums1 中选出 i 个数放入第一组，从 nums2 中选出 (m+n+1)/2 - i 个数放入第一组，其它放入第二组中
        当 第一组的最大值 <= 第二组的最小值时，此时这两个数就是要求的中位数，
        m/2 个数 <= 最大值，m/2 个数 >= 最小值

         */
        if (nums1.length > nums2.length) {
            // 交换 nums1 和 nums2，保证下面的 i 可以从 0 开始枚举
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int[] a = new int[m + 2];
        int[] b = new int[n + 2];
        // 边界处理
        a[0] = b[0] = Integer.MIN_VALUE;
        a[m + 1] = b[n + 1] = Integer.MAX_VALUE;
        System.arraycopy(nums1, 0, a, 1, m);
        System.arraycopy(nums2, 0, b, 1, n);

        /*
        枚举 nums1 有 i 个数在第一组
        那么 nums2 有 j = (m + n + 1) / 2 - i 个数在第一组，[0, j] 中个数放入第一组
         */
        int i = 0;
        int j = (m + n + 1) / 2;
        while (true) {
            if (a[i] <= b[j + 1] && a[i + 1] > b[j]) { // 写 >= 也可以
                int max1 = Math.max(a[i], b[j]); // 第一组的最大值
                int min2 = Math.min(a[i + 1], b[j + 1]); // 第二组的最小值
                return (m + n) % 2 > 0 ? max1 : (max1 + min2) / 2.0;
            }
            i++; // 继续枚举
            j--;
        }
    }

}
