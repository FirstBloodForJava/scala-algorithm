package com.oycm.datastructure.queue.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_3 {

    /**
     * 1438. <a href="https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/">绝对差不超过限制的最长连续子数组</a> 1672
     *
     * @param nums
     * @param limit 限制
     * @return 求 nums 中任意两个元素之间的绝对值差 小于等于 limit
     */
    public int longestSubarray(int[] nums, int limit) {
        /*
        题解思路: 子数组越长，子数组的最大值越大，最小值越小，最大值与最小值的差值越大，越不能满足 <= limit
            单调队列维护一个最大值和最小值
         */
        int ans = 0;
        Deque<Integer> min = new ArrayDeque<>();
        Deque<Integer> max = new ArrayDeque<>();
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            while (!min.isEmpty() && num <= nums[min.getLast()]) {
                min.removeLast();
            }
            min.addLast(i);

            while (!max.isEmpty() && num >= nums[max.getLast()]) {
                max.removeLast();
            }
            max.addLast(i);
            while (nums[max.getFirst()] - nums[min.getFirst()] > limit) {
                left++;
                if (min.getFirst() < left) {
                    min.removeFirst();
                }

                if (max.getFirst() < left) {
                    max.removeFirst();
                }
            }
            ans = Math.max(ans, i - left + 1);

        }
        return ans;
    }

}
