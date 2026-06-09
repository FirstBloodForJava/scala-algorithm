package com.oycm.month2026.june;

public class Solution_9 {

    /**
     * 3689. <a href="https://leetcode.cn/problems/maximum-total-subarray-value-i/description/">最大子数组总值 I</a> 1371
     *
     * @param nums
     * @param k
     * @return
     */
    public long maxTotalValue(int[] nums, int k) {
        /*
        给定一个长度为 n 的整数数组 nums 和一个整数 k。
        你必须从 nums 中选择 恰好 k 个非空子数组 nums[l..r]。子数组可以重叠，同一个子数组（相同的 l 和 r）可以 被选择超过一次。
        子数组 nums[l..r] 的 值 定义为：max(nums[l..r]) - min(nums[l..r])。
        总值 是所有被选子数组的 值 之和。
        返回你能实现的 最大 可能总值。
        子数组 是数组中连续的 非空 元素序列。
         */
        /*
        1 <= n == nums.length <= 5 * 1e4
        0 <= nums[i] <= 1e9
        1 <= k <= 105
         */
        /*
        (最大值 - 最小值) * k
         */
        /*
        思考题：计算数组的最小值和最大值，最多需要多少次比大小？至多要 3n/2。
         */
        int mx = Integer.MIN_VALUE;
        int mn = Integer.MAX_VALUE;
        for (int x : nums) {
            mx = Math.max(mx, x);
            mn = Math.min(mn, x);
        }
        return (long) (mx - mn) * k;
    }

}
