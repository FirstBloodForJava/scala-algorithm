package com.oycm.hot100.substring;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_11 {

    /**
     * 239. <a href="https://leetcode.cn/problems/sliding-window-maximum/description/">滑动窗口最大值</a>
     *
     * @param nums 整数数组
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        /*
        有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
        你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
         */
        /*
        [0, k] 区间，用队列维护一个非递减的下标集合
        q 表示队列，q 最后一个元素小于等于当前元素，就移除当前元素，直到队列为空
        这样队首就是区间中的最大值。
        在记录答案之前，还需要判断队首下标是否在区间 [i, i+k-1] 内
         */
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
