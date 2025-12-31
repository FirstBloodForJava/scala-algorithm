package com.oycm.datastructure.stack.monotonic;

import java.util.*;

public class Solution_3 {

    /**
     * 496. <a href="https://leetcode.cn/problems/next-greater-element-i/description/">下一个更大元素 I</a>
     * <p>
     * nums1 是 nums2 的子集
     * <p>
     * 0 <= i < nums1.length, 找出满足 nums1[i] == nums2[j] 的下标 j, 并且在 nums2 确定 nums2[j] 的 下一个更大元素 。
     * 如果不存在下一个更大元素，那么本次查询的答案是 -1 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /*
        没有重复元素, hash 表记录 nums 2 中大于 nums[j] 的下标, 问题就是下次的最高温度问题
         */
        int n = nums1.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                map.put(nums2[stack.pop()], nums2[i]);
            }
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }

        return ans;
    }

    public static void main(String[] args) {
        nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
    }

}
