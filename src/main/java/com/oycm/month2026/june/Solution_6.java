package com.oycm.month2026.june;

public class Solution_6 {

    /**
     * 2574. <a href="https://leetcode.cn/problems/left-and-right-sum-differences/description/">左右元素和的差值</a> 1206
     *
     * @param nums
     * @return
     */
    public int[] leftRightDifference(int[] nums) {
        /*
        给你一个下标从 0 开始的长度为 n 的整数数组 nums。
        定义两个数组 leftSum 和 rightSum，其中：
            leftSum[i] 是数组 nums 中下标 i 左侧元素之和。如果不存在对应的元素，leftSum[i] = 0 。
            rightSum[i] 是数组 nums 中下标 i 右侧元素之和。如果不存在对应的元素，rightSum[i] = 0 。
        返回长度为 n 数组 answer，其中 answer[i] = |leftSum[i] - rightSum[i]|。
         */
        /*
        1 <= nums.length <= 1000
        1 <= nums[i] <= 1e5
         */
        /*
        前后缀分解
         */
        int n = nums.length;
        int[] suf = new int[n];
        int[] ans = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] += suf[i + 1] + nums[i + 1];
        }
        int pre = 0;
        for (int i = 0; i < n; i++) {
            ans[i] = Math.abs(pre - suf[i]);
            pre += nums[i];
        }

        return ans;
    }

}
