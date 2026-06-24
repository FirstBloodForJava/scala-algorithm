package com.oycm.week.lc2025.No469;

public class Solution_2 {

    /**
     * 3698. <a href="https://leetcode.cn/problems/split-array-with-minimum-difference/description/">分割数组得到最小绝对差</a> 1649
     *
     * @param nums
     * @return
     */
    public long splitArray(int[] nums) {
        /*
        给你一个整数数组 nums。
        将数组 恰好 分成两个子数组 left 和 right ，使得 left 严格递增 ，right 严格递减 。
        返回 left 与 right 的元素和之间 绝对差值的最小可能值 。如果不存在有效的分割方案，则返回 -1 。
        子数组 是数组中连续的非空元素序列。
        当数组中每个元素都严格大于其前一个元素（如果存在）时，称该数组为严格递增。
        当数组中每个元素都严格小于其前一个元素（如果存在）时，称该数组为严格递减。
         */
        /*
        2 <= nums.length <= 1e5
        1 <= nums[i] <= 1e5
         */
        /*
        要先判断是否数组是否合法，只存在一个峰值点
         */
        int n = nums.length;
        int left = 0;
        long pre = 0;
        while (left < n - 1 && nums[left] < nums[left + 1]) {
            pre += nums[left++];
        }
        // 整个数组 严格递增
        if (left == n - 1) return Math.abs(pre - nums[n - 1]);

        /*
        pre 只包含 [0, left) 区间元素和
        判断 (left, n) 是否 严格递减
         */

        int right = left + 1;
        long suf = nums[right];
        while (right < n - 1 && nums[right] > nums[right + 1]) {
            suf += nums[++right];
        }
        if (right != n - 1) return -1;
        /*
        如果 nums[left] == nums[left+1] 只能分成 [0, left] [left+1, n-1]
        如果 nums[left] > nums[left+1]，可以拆分成 [0, left] 和 [left+1, n-1]，[0, left) 和 [left, n-1]
        [0, left]        pre + nums[left]
        [left+1, n-1]    suf
         */
        long ans = Math.abs(suf - pre - nums[left]);

        if (left > 0 && nums[left] > nums[left + 1]) {
            ans = Math.min(ans, Math.abs(suf + nums[left] - pre));
        }

        return ans;

    }

}
