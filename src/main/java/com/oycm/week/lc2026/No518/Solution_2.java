package com.oycm.week.lc2026.No518;

public class Solution_2 {

    /**
     * 统计好循环移位的数量
     *
     * @param nums
     * @return
     */
    public int countGoodRotations(int[] nums) {
        /*
        给你一个长度为偶数 n 的整数数组 nums。
        nums 的一次 循环移位 可以通过以下方式得到：选择 nums 的一个长度在 0 到 n - 1（包含两端）之间的 前缀 ，并将其移动到数组末尾，同时保持所有元素的相对顺序不变。
        如果一次循环移位后的数组中，前 n / 2 个元素之和 严格大于 后 n / 2 个元素之和，则称该循环移位是 好循环移位 。
        返回 nums 中好循环移位的数量。
        数组的 前缀 是指从数组开头开始，并延伸到数组中某个位置的子数组。
        子数组 是数组中一段连续的元素序列，可以为空。
         */
        /*
        前缀和
         */
        int n = nums.length;
        long[] sum = new long[2 * n + 1];
        for (int i = 0; i < 2 * n; i++) {
            sum[i + 1] = sum[i] + nums[i % n];
        }
        int ans = 0;
        int m = n / 2;
        for (int i = 0; i < n; i++) {
            long pre = sum[i + m] - sum[i];
            long suf = sum[i + n] - sum[i + m];
            if (pre > suf) {
                ans++;
            }
        }
        return ans;
    }
}
