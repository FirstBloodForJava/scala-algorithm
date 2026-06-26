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

    public double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        /*
        nums1 记为 a，nums 2 记为 b；
        max(ai, bj) <= min(a(i+1), b(j+1))
             因为数组升序，所以 ai <= a(i+1)，且 bi <= b(j+1)
             只需要判断 ai <= b(j+1) 且 bj <= a(i+1)
        枚举过程中 ai 不断变大，bj 不断变小，除了特殊情况（a 最大值小于 b 最小值）
            存在 ai <= b(j+1)，那么 a(i+1) > a(i), b(j+1) > b(j)
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
        枚举 nums1 有 i 个数在第一组（不包含添加的最小值）(i+1 个数)
        那么 nums2 有 j = (m + n + 1) / 2 - i 个数在第一组，[0, j] 中个数放入第一组（不包含添加的最小值）(j+1 个数)
        和前后添加的 最小，最大 个数匹配
         */
        int i = 0;
        int j = (m + n + 1) / 2;
        while (a[i + 1] <= b[j]) {
            i++;
            j--;
        }
        int max1 = Math.max(a[i], b[j]); // 第一组的最大值
        int min2 = Math.min(a[i + 1], b[j + 1]); // 第二组的最小值
        return (m + n) % 2 > 0 ? max1 : (max1 + min2) / 2.0;

    }

    public double findMedianSortedArrays_binary(int[] nums1, int[] nums2) {
        /*
        由于 a 和 b 是有序数组，长度分别为 m,n，划分两个数组，当 m+n 和为奇数时，第一个数组多一个元素，第一个数组元素数量为 half = (m+n+1)/2
        设 a 数组长度 m < n 恒成立，枚举 i [0, m)，剩余元素从 b 数组取，范围为 [0, j] j = half - i - 2;
        如果 max(a[i], b[j]) <= min(a[i+1], b[j+1]) 那么为奇数的情况 max(a[i], b[j]) 就是中位数，否则就是两个值的平均值。
        a[i] <= b[j+1] 这个等式随着 i 变大，j 变小，会越来越难成立，可以二分查找，是不是查找最右边的 i 满足 a[i] <= b[j+1]，数组就分割好了？
        假设 a[i] <= b[j+1] 成立，那么 a[i+1] > b[j] 肯定成立，否则 i 就可以右移一位了，这里也恰好印证了 max(a[i], b[j]) <= min(a[i+1], b[j+1]) 这个等式
        i 越小，ai <= b(j+1) 越能成立，i 越大， ai <= b(j+1) 越不能成立。
        可以二分查找最大满足 ai <= b(j+1) 的 i，二分介绍后的 i 就是符合条件的边界。
         */
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // 较短数组
        int m = nums1.length;
        int n = nums2.length;
        int half = (m + n + 1) / 2;
        int left = -1;
        int right = m;
        while (left + 1 < right) {
            int i = left + (right - left) / 2;
            // 闭区间，原来的范围各要减去 1
            int j = half - i - 2;
            if (nums1[i] <= nums2[j + 1]) {
                left = i;
            } else {
                right = i;
            }
        }
        /*
        此时 left = right-1，如果 i 右移一位，则 j 要左移一位。
        a[i] <= b[j+1]，现在判断 a[i+1] >= b[j] 是否成立
        此时 a[i] 没有右移，说明 a[i+1] > b[j]
         */
        int i = left;
        int j = half - i - 2;
        int a1 = i >= 0 ? nums1[i] : Integer.MIN_VALUE;
        int a2 = j >= 0 ? nums2[j] : Integer.MIN_VALUE;
        int b1 = i + 1 < m ? nums1[i + 1] : Integer.MAX_VALUE;
        int b2 = j + 1 < n ? nums2[j + 1] : Integer.MAX_VALUE;
        int mx = Math.max(a1, a2);
        int mn = Math.min(b1, b2);
        return (m + n) % 2 > 0 ? mx : (mx + mn) / 2.0;
    }

}
