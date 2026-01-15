package com.oycm.algorithm.d.binary_search.advance;

import java.util.Arrays;


public class Solution_13 {

    /**
     * 1818. <a href="https://leetcode.cn/problems/minimum-absolute-sum-difference/description/">绝对差值和</a> 1934
     * <p>
     * nums1 和 nums2 的绝对值差和: sum(|nums[1] - nums2[2]|) [0, n-1]
     *
     * @param nums1 正整数数组
     * @param nums2 正整数数组
     * @return 求 将 nums1 中的一个元素 替换 至多一个元素，以最小化绝对值差和
     */
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        /*
        nums1 和 nums2 新组成的 nums 绝对值差数组, 最大的 nums[i] 被改成 0
        把 nums 排序, 计算 替换 i 能减少的值，求最小值的过程
        查找 大于等于 nums2[i] 的第一个 index, index - 1 就是 < nums2[i] 的最后一个数
         */
        long sum = 0;
        int mod = 1000000007;
        int n = nums1.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums1[i] - nums2[i]);
            sum += nums[i];
        }
        if (sum == 0) {
            return 0;
        }
        int min = 0;
        Arrays.sort(nums1);
        for (int i = 0; i < n; i++) {
            // 替换后能减少的更多
            if (nums[i] + min > 0) {
                // 在 nums1 中查找 距离 nums2[i] 更近的数，nums1[i] 查找替换数能变小的值
                int x = nums2[i];
                int l = -1;
                int r = n;
                while (l + 1 < r) {
                    int mid = l + (r - l) / 2;
                    if (nums1[mid] >= x) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                if (r == n) {
                    min = Math.min(Math.abs(nums1[r - 1] - x) - nums[i], min);
                } else if (r == 0){
                    min = Math.min(Math.abs(nums1[r] - x) - nums[i], min);
                } else {
                    int minAbs = Math.min(Math.abs(nums1[r] - x), Math.abs(nums1[r - 1] - x));
                    min = Math.min(minAbs - nums[i], min);
                }
            }
        }
        sum += min;

        return (int) (sum % mod);
    }
}
