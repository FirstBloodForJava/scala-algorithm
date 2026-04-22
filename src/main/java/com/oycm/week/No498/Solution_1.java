package com.oycm.week.No498;

public class Solution_1 {

    /**
     * 3903. <a href="https://leetcode.cn/problems/smallest-stable-index-i/description/">最小稳定下标 I</a>
     *
     * @param nums nums[i] [0, 1e9]; nums.length [1, 100]
     * @param k
     * @return 返回 最小 的稳定下标。如果不存在这样的下标，则返回 -1
     */
    public int firstStableIndex(int[] nums, int k) {
        /*
        对于每个下标 i，定义它的 不稳定值 为 max(nums[0..i]) - min(nums[i..n - 1])
        如果某个下标 i 的不稳定值 小于等于 k，则称该下标为 稳定下标 。
         */
        /*
        暴力的计算每个 i 右边的最小值
        [0, i] 的最大值可以随着遍历更新
        [i, n-1] 的最小值可以使用堆来维护，每次从最小堆中取出值，当前 cur == 最小值，则最小值不入堆，否则入堆
        先遍历 nums, 用数组记录 [i, n-1] 区间最小值对应的下标, 从右到左遍历
         */
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int[] minIdx = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < min) {
                min = nums[i];
            }
            minIdx[i] = min;
        }
        int max = nums[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            if (max - minIdx[i] <= k) {
                return i;
            }
        }

        return -1;
    }

}
