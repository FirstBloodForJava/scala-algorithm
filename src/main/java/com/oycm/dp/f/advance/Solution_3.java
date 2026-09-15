package com.oycm.dp.f.advance;

public class Solution_3 {

    /**
     * 2771. <a href="https://leetcode.cn/problems/longest-non-decreasing-subarray-from-two-arrays/description/">构造最长非递减子数组</a> 1792
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        /*
        给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度均为 n 。
        让我们定义另一个下标从 0 开始、长度为 n 的整数数组，nums3 。
        对于范围 [0, n - 1] 的每个下标 i ，你可以将 nums1[i] 或 nums2[i] 的值赋给 nums3[i] 。
        你的任务是使用最优策略为 nums3 赋值，以最大化 nums3 中 最长非递减子数组 的长度。
        以整数形式表示并返回 nums3 中 最长非递减 子数组的长度。
        注意：子数组 是数组中的一个连续非空元素序列。
         */
        /*
        题解思路：枚举选哪个。
        dfs(i, j) 表示以 numsj[i] 结尾的最长非递减子数组的长度。
            如果 nums1[i-1] <= numsj[i]，那么下一步选 nums1[i-1]，dfs(i, j) = dfs(i-1, 0) + 1
            如果 nums2[i-1] <= numsj[i]，那么下一步选 nums2[i-1]，dfs(i, j) = dfs(i-1, 1) + 1
            如果都不成立 dfs(i, j) = 1
        所有情况取最大值，即为 dfs(i, j) 的结果
         */
        int n = nums1.length;
        int[][] f = new int[n][];
        for (int i = 0; i < f.length; i++) {
            f[i] = new int[]{1, 1};
        }

        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (nums1[i - 1] <= nums1[i]) {
                f[i][0] = f[i - 1][0] + 1;
            }
            if (nums2[i - 1] <= nums2[i]) {
                f[i][1] = f[i - 1][1] + 1;
            }
            if (nums1[i - 1] <= nums2[i]) {
                f[i][1] = Math.max(f[i - 1][0] + 1, f[i][1]);
            }
            if (nums2[i - 1] <= nums1[i]) {
                f[i][0] = Math.max(f[i - 1][1] + 1, f[i][0]);
            }
            ans = Math.max(ans, Math.max(f[i][0], f[i][1]));
        }
        return ans;
    }

}
