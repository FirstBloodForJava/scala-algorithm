package com.oycm.algorithm.bb;

public class Solution_7 {

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
        题解思路：双序列双指针
        枚举 j，随着 j 的增大 nums2[j] 变小，对应 nums1 满足要求的 i 也需要更大
        可以使用双指针，如果 nums1[i] > nums2[j], i < nums1.length, i++，过程中就算 i > j 了，也不影响，因为是求最大值
         */
        int ans = 0;
        int i = 0;
        for (int j = 0; j < nums2.length; j++) {
            while (i < nums1.length && nums1[i] > nums2[j]) {
                i++;
            }
            if (i == nums1.length) {
                break;
            }
            ans = Math.max(ans, j - i);
        }

        return ans;
    }

}
