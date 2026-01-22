package com.oycm.datastructure.stack.monotonic.contribution_approach;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_2 {

    /**
     * 2104. <a href="https://leetcode.cn/problems/sum-of-subarray-ranges/description/">子数组范围和</a>
     * 范围 是子数组中最大元素和最小元素的差值
     *
     * @param nums
     * @return nums 中 所有 子数组范围的 和
     */
    public long subArrayRanges(int[] nums) {
        /*
        固定 nums[i] 为最小值, [0, i] 可选的最大值, 这样会漏掉 nums[i] 为最小值的情况
        题解思路: 计算 nums[i] 为 最大值子数组 个数; 计算 nums[j] 为 最小值的 子数组个数 两个差值就是 答案
         */
        long ans = maxSum(nums);
        // 取反后求最大就是最小
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -nums[i];
        }

        return ans + maxSum(nums);
    }

    public long maxSum(int[] nums) {
        int n = nums.length;
        long ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            while (st.size() > 1 && nums[st.peek()] <= nums[i]) {
                st.pop();
            }
            left[i] = st.peek();
            st.push(i);
        }
        st.clear();
        st.push(n);
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() > 1 && nums[st.peek()] < nums[i]) {
                st.pop();
            }
            right[i] = st.peek();
            st.push(i);
        }
        for (int i = 0; i < n; i++) {
            ans += (long) nums[i] * (right[i] - i) * (i - left[i]);
        }

        return ans;
    }

}
