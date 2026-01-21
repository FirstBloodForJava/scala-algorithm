package com.oycm.datastructure.stack.monotonic.rectangle;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution_2 {

    /**
     * 1793. <a href="https://leetcode.cn/problems/maximum-score-of-a-good-subarray/description/">好子数组的最大分数</a> 1946
     * <p>
     * 子数组分数: min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1), 且 i <= k <= j
     *
     * @param nums
     * @param k
     * @return 求最大的可能分数
     */
    public int maximumScore(int[] nums, int k) {
        /*
        nums = [1, 4, 3, 7, 4, 5], k = 3, 固定的 k 必须要选
        本质是求矩形的面积: 先固定选 k, 往两边选的过程比较 nums[l-1] 和 nums[r+1] 的大小, 因为多选一个 宽度 +1, 要想面积最大, 选择高度变化最小的是最优解决
        所以移动更大的数
        另一种思路: 和 84 题类似, 不过一定要选择 k 下标的柱子
         */
        int ans = nums[k], n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                right[st.pop()] = i;
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        for (int i = 0; i < n; i++) {
            int l = left[i];
            int r = right[i];
            if (l < k && r > k) {
                ans = Math.max(ans, nums[i] * (r - l - 1));
            }
        }

        return ans;
    }

}
