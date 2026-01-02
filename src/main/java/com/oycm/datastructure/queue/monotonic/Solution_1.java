package com.oycm.datastructure.queue.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1 {

    /**
     * 239. <a href="https://leetcode.cn/problems/sliding-window-maximum/description/">滑动窗口最大值</a>
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> q = new ArrayDeque<>();
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i < n; i++) {
            while (!q.isEmpty() && nums[i] >= nums[q.getLast()]) {
                q.removeLast();
            }
            q.addLast(i);
            int left = i - k + 1;
            if (q.getFirst() < left) {
                q.removeFirst();
            }
            if (left >= 0) {
                ans[left] = nums[q.getFirst()];
            }
        }

        return ans;
    }

}
