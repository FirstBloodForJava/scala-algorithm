package com.oycm.month2026.october;

import java.util.Arrays;

public class Solution_10 {

    /**
     * 2333. <a href="https://leetcode.cn/problems/minimum-sum-of-squared-difference/description/">最小差值平方和</a> 2011
     *
     * @param nums1
     * @param nums2
     * @param k1
     * @param k2
     * @return
     */
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        /*
        给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度为 n 。
        数组 nums1 和 nums2 的 差值平方和 定义为所有满足 0 <= i < n 的 (nums1[i] - nums2[i])^2 之和。
        同时给你两个正整数 k1 和 k2 。你可以将 nums1 中的任意元素 +1 或者 -1 至多 k1 次。类似的，你可以将 nums2 中的任意元素 +1 或者 -1 至多 k2 次。
        请你返回修改数组 nums1 至多 k1 次且修改数组 nums2 至多 k2 次后的最小 差值平方和 。
        注意：你可以将数组中的元素变成 负 整数。
         */
        /*
        k1 + k2 记为总操作次数 k，|nums1[i] - nums2[i]| 绝对值表示要操作的数，得分新数组 nums
        对 nums 的每个元素至多进行 k 次减 1，求最小的平方和
         */
        int n = nums1.length;
        int k = k1 + k2;
        int[] nums = new int[n];
        long ans = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums1[i] - nums2[i]);
            sum += nums[i];
            ans += (long) nums[i] * nums[i];
        }
        if (sum <= k) {
            // 都改成 0
            return 0;
        }
        Arrays.sort(nums);
        for (int i = n - 1; ; i--) {
            int m = n - i;
            long v = nums[i];
            long c = m * (v - (i > 0 ? nums[i - 1] : 0));
            ans -= v * v;
            if (c < k) {
                k -= c;
                continue;
            }
            v -= k / m;
            return ans + k % m * (v - 1) * (v - 1) + (m - k % m) * v * v;
        }
    }
}
