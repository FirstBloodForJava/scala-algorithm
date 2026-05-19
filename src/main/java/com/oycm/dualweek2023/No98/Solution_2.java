package com.oycm.dualweek2023.No98;

public class Solution_2 {

    /**
     * 2541. <a href="https://leetcode.cn/problems/minimum-operations-to-make-array-equal-ii/description/">使数组中所有元素相等的最小操作数 II</a> 1620
     *
     * @param nums1
     * @param nums2
     * @param k     [0, 1e5]
     * @return
     */
    public long minOperations(int[] nums1, int[] nums2, int k) {
        /*
        给你两个整数数组 nums1 和 nums2 ，两个数组长度都是 n ，再给你一个整数 k 。你可以对数组 nums1 进行以下操作：
            选择两个下标 i 和 j ，将 nums1[i] 增加 k ，将 nums1[j] 减少 k 。换言之，nums1[i] = nums1[i] + k 且 nums1[j] = nums1[j] - k。
        如果对于所有满足 0 <= i < n 都有 num1[i] == nums2[i] ，那么我们称 nums1 等于 nums2。
        返回使 nums1 等于 nums2 的 最少 操作数。如果没办法让它们相等，请你返回 -1 。
         */
        /*
        nums1 任意数进行操作，会以 k 的倍数进行增加或减少，
            如果 nums1[i] 和 nums2[i] 的差值不是 k 的倍数，无论怎么操作都无法变成 nums2[i]；
            当 nums[i] > nums2[i]，t = diff / k 的次数记为 i 需要增加 k 的次数；
            当 nums[i] < nums2[i]，-t = diff / k 的次数记为 i 需要减少 k 的次数；
        要想操作次数尽可能的少，优先选择 nums1[i] < nums2[i] 和 nums1[j] > nums2[j] 这种去操作。
        为什么这样是最优的？
        假设 nums1[j] > nums2[j]，需要将 nums1[j] 减少到 nums2[j]，记为 t 次操作，可以选择任意一个不等于 j 的下标 i，增加 t*k；
        分类讨论：
            如果选择 nums1[i] == nums2[i] 的 i，nums1[i] 增加了 t*k，要想 nums1[i] 再次等于 nums2[i]，一个平衡的下标，还需要找一个 nums1[i] < nums2[i] 增加 t*k，这样执行两次 t 操作，才能使得两个下标 i, j 相等；
            如果选择 nums1[i] > nums2[i] 的 i，和等于是一样的，需要执行 2 倍的操作，才能还原 nums1[i]；
            如果选择 nums1[i] < nums2[i] 的 i，减少 j 的同时又增加了 i，
        这里，如果 i 增加的操作次数比 j 减少的次数多，可以选择其它 nums1 大于 nums2 的下标进行操作；
        如果增加的操作次数和记为 a 次，减少的操作次数记为 b 次，如果 a - b != 0，后面一定不能使得 nums1 等于 nums2
        在判断两者差值是否 k 的倍数时，需要对 k == 0
         */
        long cnt = 0;
        long sum = 0;
        for (int i = 0; i < nums1.length; i++) {
            int diff = nums1[i] - nums2[i];
            if (k == 0) {
                if (diff != 0) return -1;
                continue;
            } else if (diff % k != 0) {
                return -1;
            }
            diff /= k;
            if (diff > 0) {
                cnt += diff;
            }
            sum += diff;
        }
        return sum == 0 ? cnt : -1;
    }

}
