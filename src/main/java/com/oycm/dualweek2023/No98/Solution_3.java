package com.oycm.dualweek2023.No98;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_3 {

    /**
     * 2542. <a href="https://leetcode.cn/problems/maximum-subsequence-score/description/">最大子序列的分数</a> 2056
     * <p>
     * nums1[i], nums2[i] [0, 1e5]; n [1, 1e5]
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public long maxScore(int[] nums1, int[] nums2, int k) {
        /*
        给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两者长度都是 n ，再给你一个正整数 k 。你必须从 nums1 中选一个长度为 k 的 子序列 对应的下标。
        对于选择的下标 i0 ，i1 ，...， ik-1 ，你的 分数 定义如下：
            nums1 中下标对应元素求和，乘以 nums2 中下标对应元素的 最小值
            用公式表示： (nums1[i0] + nums1[i1] +...+ nums1[ik-1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik-1])
        返回 最大 可能的分数。
        一个数组的 子序列 下标是集合 {0, 1, ..., n-1} 中删除若干元素得到的剩余集合，也可以不删除任何元素。
         */
        /*
        思路 1：长为 k 的子序列，确定 nums2 中最小值选的是谁，从 nums1 选 k-1 个数，对应的 nums2 的元素要大于等于这个最小值，所能取得的最大值；
        对 nums2 进行排序，可以 O(1) 的知道哪些数大于等于最小值，同时为了保证 nums1 和 nums2 的相对顺序不变，nums1 也按 nums1 规则排序
        nums2 排序后，最小值最大下标为 n-k，此时 nums1 中对应的和为 [n-k, n)
        把 [n-k, n) 范围求和，放入一个小顶堆，枚举下一个最小值，同时判断 nums1[i] 和 堆顶元素的大小，如果大于则移除堆顶元素，把他加入堆

        优化：对下标排序后，可以不用再修改 nums1 和 nums2 数组，直接遍历 下标数组即可
         */
        int n = nums1.length;
        long ans = 0;
        long sum = 0;
        // 先对 nums1 排序（根据 nums2 的顺序）
        Integer[] idxes = new Integer[n];
        for (int i = 0; i < idxes.length; i++) {
            idxes[i] = i;
        }
        Arrays.sort(idxes, (a, b) -> nums2[a] - nums2[b]);
        PriorityQueue<Integer> min = new PriorityQueue<>(k);

        for (int i = n - 1; i >= 0; i--) {
            if (i >= n - k) {
                sum += nums1[idxes[i]];
                min.add(nums1[idxes[i]]);
                if (i == n - k) ans = Math.max(ans, sum * nums2[idxes[i]]);
            } else if (nums1[idxes[i]] > min.peek()) {
                sum += nums1[idxes[i]] - min.poll();
                min.add(nums1[idxes[i]]);
                // 最小值在变小，sum 只有变大，sum * min 才会变大
                ans = Math.max(ans, sum * nums2[idxes[i]]);
            }

        }

        return ans;
    }

}
