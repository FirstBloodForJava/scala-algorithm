package com.oycm.week.No496;

public class Solution_3 {

    /**
     * 3891. <a href="https://leetcode.cn/problems/minimum-increase-to-maximize-special-indices/description/">最大化特殊下标数目的最少增加次数</a>
     *
     * @param nums
     * @return
     */
    public long minIncrease(int[] nums) {
        /*
        如果 nums[i] > nums[i - 1] 且 nums[i] > nums[i + 1]，则下标 i (0 < i < n - 1) 是 特殊的
        可以执行操作，将任意 nums[i] 增加 1, 目标是：
            最大化 特殊 下标的数量
            最小化 达到该 最大值 所需的总 操作 数
         */
        /*
        只有一种情况情况，特殊下标的数量是固定的，需要在固定的位置进行添加操作 n % 2 == 1 (n >= 3), 数量是 n/2
        n = 6 时, [0, 1, 2, 3, 4, 5], 最大特殊下标方案 1, 3/4; 2, 4
        题解思路: n 是偶数时, 从右往左间隔 1 选, 从左往右间隔 1 选 中间跳过 2 个元素
        [1, 4] [1, 3]
         */

        int n = nums.length;
        long[] suf = new long[n + 1];
        for (int i = n - 2; i > 0; i -= 2) {
            // 奇数下标修改代价后缀和
            suf[i] = suf[i + 2] + Math.max(Math.max(nums[i - 1], nums[i + 1]) - nums[i] + 1, 0);
        }
        if (n % 2 == 1) {
            return suf[1];
        }
        // n 是偶数，后面倒着选 [2, n-2]
        long ans = suf[2];
        long pre = 0;
        for (int i = 1; i < n - 1; i += 2) {
            pre += Math.max(Math.max(nums[i - 1], nums[i + 1]) - nums[i] + 1, 0);
            ans = Math.min(ans, pre + suf[i + 3]);
        }

        return ans;
    }
}
