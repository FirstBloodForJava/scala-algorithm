package com.oycm.month2026.may;

public class Solution_10 {

    /**
     * 2770. <a href="https://leetcode.cn/problems/maximum-number-of-jumps-to-reach-the-last-index/description/">达到末尾下标所需的最大跳跃次数</a> 1533
     *
     * @param nums   nums.length [2, 1000]
     * @param target
     * @return
     */
    public int maximumJumps(int[] nums, int target) {
        /*
        你的初始位置在下标 0 。在一步操作中，你可以从下标 i 跳跃到任意满足下述条件的下标 j ：
            0 <= i < j < n
            -target <= nums[j] - nums[i] <= target
        返回到达下标 n - 1 处所需的 最大跳跃次数 。
        如果无法到达下标 n - 1 ，返回 -1 。
         */
        /*
        因为只能往前跳跃，最大的跳跃次数为 n-1 次
        dp[j] = max(dp[j], dp[i] + 1) i 要满足 -target <= nums[j] - nums[i] <= target
        这个 i 怎么求？
        两层循环，查找所有 [0, j-1]
         */
        int n = nums.length;
        int[] dp = new int[n];
        for (int j = 1; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {

                if (Math.abs(nums[j] - nums[i]) <= target && (i == 0 || dp[i] > 0)) {
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                }
            }
        }

        return dp[n - 1] == 0 ? -1 : dp[n - 1];
    }
}
