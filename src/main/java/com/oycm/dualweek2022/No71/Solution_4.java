package com.oycm.dualweek2022.No71;

import java.util.PriorityQueue;

public class Solution_4 {

    /**
     * 2163. <a href="https://leetcode.cn/problems/minimum-difference-in-sums-after-removal-of-elements/description/">删除元素后和的最小差值</a> 2225
     *
     * @param nums
     * @return
     */
    public long minimumDifference(int[] nums) {
        /*
        给你一个下标从 0 开始的整数数组 nums ，它包含 3 * n 个元素。
        你可以从 nums 中删除 恰好 n 个元素，剩下的 2 * n 个元素将会被分成两个 相同大小 的部分。
            前面 n 个元素属于第一部分，它们的和记为 sumfirst。
            后面 n 个元素属于第二部分，它们的和记为 sumsecond。
        两部分和的 差值 记为 sumfirst - sumsecond。
        比方说，sumfirst = 3 且 sumsecond = 2 ，它们的差值为 1 。
        再比方，sumfirst = 2 且 sumsecond = 3 ，它们的差值为 -1 。
        请你返回删除 n 个元素之后，剩下两部分和的 差值的最小值 是多少。
         */
        /*
        m = nums.length; n = m / 3
        枚举 [n, 2n - 1] 作为分界线，求 前缀最小值 - 后缀最大值 的最小值
        后缀最大值，从后缀中每次删除最小值元素（小顶堆维护）
         */
        int m = nums.length;
        int n = m / 3;


        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        long sum = 0;
        for (int i = m - 1; i >= m - n; i--) {
            sum += nums[i];
            minQ.add(nums[i]);
        }
        // 长 2n + 1
        long[] sufMax = new long[m - n + 1];
        sufMax[m - n] = sum;
        for (int i = m - n - 1; i >= n; i--) {
            int x = nums[i];
            if (x > minQ.peek()) {
                sum += x - minQ.poll();
                minQ.add(x);
            }
            sufMax[i] = sum;
        }
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a);
        long preMin = 0;
        for (int i = 0; i < n; i++) {
            preMin += nums[i];
            maxQ.add(nums[i]);
        }
        long ans = preMin - sufMax[n];
        for (int i = n; i < m - n; i++) {
            int x = nums[i];
            if (x < maxQ.peek()) {
                preMin += x - maxQ.poll();
                minQ.add(x);
            }
            ans = Math.min(ans, preMin - sufMax[i + 1]);
        }

        return ans;
    }

}
