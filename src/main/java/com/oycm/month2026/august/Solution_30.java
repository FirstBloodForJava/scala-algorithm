package com.oycm.month2026.august;

public class Solution_30 {

    /**
     * 2091. <a href="https://leetcode.cn/problems/removing-minimum-and-maximum-from-array/description/">从数组中移除最大值和最小值</a> 1384
     *
     * @param nums
     * @return
     */
    public int minimumDeletions(int[] nums) {
        /*
        给你一个下标从 0 开始的数组 nums ，数组由若干 互不相同 的整数组成。
        nums 中有一个值最小的元素和一个值最大的元素。分别称为 最小值 和 最大值。你的目标是从数组中移除这两个元素。
        一次 删除 操作定义为从数组的 前面 移除一个元素或从数组的 后面 移除一个元素。
        返回将数组中最小值和最大值 都 移除需要的最小删除次数。
         */
        /*
        nums 中的整数 互不相同
        找到最大值、最小值 下标，分别记为 mx, mn，计算最小删除次数
        元素互不相同，设 mn < mx
            mn + 1 + n - mx 前后删除
            n - mn 从后面删除
            mx + 1 从前面删除
        三者取最小
         */
        int n = nums.length;
        if (n <= 2) return n;
        int mn = 0, mx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[mx]) {
                mx = i;
            } else if (nums[i] < nums[mn]) {
                mn = i;
            }
        }
        if (mn > mx) {
            int temp = mn;
            mn = mx;
            mx = temp;
        }

        return Math.min(mn + 1 + n - mx, Math.min(mx + 1, n - mn));
    }

}
