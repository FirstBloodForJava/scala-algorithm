package com.oycm.datastructure.stack.monotonic.advance;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_4 {

    /**
     * 456. <a href="https://leetcode.cn/problems/132-pattern/description/">132 模式</a>
     * <p>
     * 132 模式的子序列: 三个整数 nums[i]、nums[j] 和 nums[k] 组成, 同时 i < j < k 且 nums[i] < nums[k] < nums[j]
     *
     * @param nums 整数数组
     * @return
     */
    public boolean find132pattern(int[] nums) {
        /*
        枚举 i, 找出一对 (j, k), (j, k) 满足比 i 大, 同时 j > k
         */
        int n = nums.length;
        int k = Integer.MIN_VALUE;
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < k) return true;
            // 当前 nums[i] 是 j
            while (!s.isEmpty() && nums[i] > s.peek()) {
                k = Math.max(k, s.pop());
            }
            s.push(nums[i]);
        }

        return false;
    }

}
