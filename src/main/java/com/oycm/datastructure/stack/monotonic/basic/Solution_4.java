package com.oycm.datastructure.stack.monotonic.basic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution_4 {

    /**
     * 503. <a href="https://leetcode.cn/problems/next-greater-element-ii/description/">下一个更大元素 II</a>
     *
     * @param nums 循环数组 nums[nums.length - 1] 的下一个元素是 nums[0]
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int j = i % n;
            while (!stack.isEmpty() && nums[j] >  nums[stack.peek()]) {
                ans[stack.pop()] = nums[j];
            }
            stack.push(j);
        }
        return ans;
    }

}
