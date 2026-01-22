package com.oycm.datastructure.stack.monotonic.contribution_approach;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_3 {

    /**
     * 1856. <a href="https://leetcode.cn/problems/maximum-subarray-min-product/description/">子数组最小乘积的最大值</a>
     * <p>
     * 数组的 最小乘积: 数组的最小值 乘以 数组的和
     *
     * @param nums
     * @return 求 num 任意非空子数组 最小乘积的 最大值
     */
    public static int maxSumMinProduct(int[] nums) {
        /*
        要想最小乘积值最大 最小值 * 最小值最大范围和 最大
        选择 nums[i] 为 最小值, [l, r] 表示可选的最大范围 更新最大值
         */
        int n = nums.length;
        long ans = 0;
        long[] sums = new long[n + 1];
        // 前缀和
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        Deque<Integer> st = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];
        st.push(-1);
        for (int i = 0; i < n; i++) {
            while (st.size() > 1 && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            left[i] = st.peek();
            st.push(i);
        }
        st.clear();
        st.push(n);
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() > 1 && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            right[i] = st.peek();
            st.push(i);
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, nums[i] * (sums[right[i]] - sums[left[i] + 1]));
        }

        return (int) (ans % 1000000007);
    }

    public static void main(String[] args) {
        System.out.println(maxSumMinProduct(new int[]{1, 2, 3, 2}));
    }
}
