package com.oycm.hot100.dp;

public class Solution_89 {

    /**
     * 416. <a href="https://leetcode.cn/problems/partition-equal-subset-sum/description/">分割等和子集</a>
     *
     * @param nums 1 <= nums.length <= 200; 1 <= nums[i] <= 100
     * @return
     */
    public boolean canPartition(int[] nums) {
        /*
        给你一个 只包含正整数 的 非空 数组 nums 。
        请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
         */
        /*
        记 nums 数组元素和为 s，如果 s 是奇数，由于都是偶数，则不可能分割成两个子集相等的元素
        问题转换成从 nums 是否一些子集，使其和恰好为 s/2
         */
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 == 1) return false;
        s /= 2;
        boolean[] f = new boolean[s + 1];
        f[0] = true;
        for (int x : nums) {
            for (int c = s; c >= x; c--) {
                f[c] = f[c] || f[c - x];
            }
            if (f[s]) return true;
        }

        return f[s];
    }

}
