package com.oycm.week.lc2026.No509;

public class Solution_4 {

    /**
     * 3985. 回文子数组求和
     * <br>
     * 3985. <a href="https://leetcode.cn/problems/palindromic-subarray-sum/description/">回文子数组求和</a>
     *
     * @param nums
     * @return
     */
    public long getSum(int[] nums) {
        /*
        给你一个整数数组 nums。
        你的任务是找出 nums 中一个 回文子数组 的 最大 元素和。
        返回这样的子数组的 最大 元素和。
        子数组 是数组中一个连续的 非空 元素序列。
        如果一个 子数组 正着读和反着读都相同，则称其为 回文 。
         */
        /*
        Manacher 算法，中间补 0，前缀和。
         */
        int n = nums.length;
        int[] ts = new int[2 * n + 3];
        for (int i = 0; i < n; i++) {
            ts[2 * i + 2] = nums[i];
        }
        long[] sums = new long[ts.length + 1];
        for (int i = 0; i < ts.length; i++) {
            sums[i + 1] = sums[i] + ts[i];
        }
        ts[2 * n + 2] = -1;
        // 表示 ts 以 i 为回文中心，最长的回文半径长度
        int[] halfLen = new int[ts.length - 2];
        // 前缀和


        long ans = 0;
        // Manacher 计算
        int boxM = 0, boxR = 0;
        for (int i = 2; i < halfLen.length; i++) {
            int hl = 1;
            if (i < boxR) {
                hl = Math.min(halfLen[2 * boxM - i], boxR - i);
            }
            while (ts[i - hl] == ts[i + hl]) {
                hl++;
                boxM = i;
                boxR = i + hl;
            }
            halfLen[i] = hl;
            // (i-hl, i+hl) 区间两个端点不匹配
            int r = i + hl;
            int l = i - hl + 1;
            ans = Math.max(ans, sums[r] - sums[l]);
        }

        return ans;
    }

}
