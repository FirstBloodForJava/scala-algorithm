package com.oycm.week.No494;

import java.util.Arrays;
import java.util.List;

public class Solution_3 {

    /**
     * <a href="https://leetcode.cn/problems/minimum-removals-to-achieve-target-xor/description/">达到目标异或值的最少删除次数</a>
     *
     * @param nums
     * @param target
     * @return 移除 任意 数量的元素, 使剩余元素的 按位异或和 等于 target 所需的 最小 移除次数
     */
    public int minRemovals(int[] nums, int target) {
        /*
        题解思路: 和 2915 题类似
            这里先判断 nums 最大的数 是否符合要求 (1 << m) - 1 < target
                1 << m < target + 1 都为整数 => 1 << m <= target
         */
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int m = 32 - Integer.numberOfLeadingZeros(max);
        if (1 << m <= target) {
            return -1;
        }
        int n = nums.length;
        int l = 1 << m;
        int[][] f = new int[n + 1][l];
        /*
        f[i+1][target+1]
            f[i+1][j] = max(f[i][j], f[i][i ^ nums[i] + 1)
            f[0][0] = 0; 其余 f[0][j] = Integer.MIN_VALUE
         */
        Arrays.fill(f[0], Integer.MIN_VALUE);
        f[0][0] = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = 0; j < l; j++) {
                f[i + 1][j] = Math.max(f[i][j], f[i][j ^ x] + 1);
            }
        }
        if (f[n][target] < 0) {
            return -1;
        }


        return n - f[n][target];
    }


    /**
     * 2915. <a href="https://leetcode.cn/problems/length-of-the-longest-subsequence-that-sums-to-target/description/">和为目标值的最长子序列的长度</a> 1659
     *
     * @param nums
     * @param target
     * @return
     */
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        /*
        题解思路:
            f[i+1][target+1]
            f[i+1][j] = max(f[i][j], f[i][i-nums[i]] + 1)
            f[0][0] = 0; 其余 f[0][j] = Integer.MIN_VALUE
         */
        int n = nums.size();
        int[][] f = new int[n + 1][target + 1];
        Arrays.fill(f[0], Integer.MIN_VALUE);
        f[0][0] = 0;
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            for (int j = 0; j <= target; j++) {
                if (j < x) {
                    // 不能选
                    f[i + 1][j] = f[i][j];
                } else {
                    f[i + 1][j] = Math.max(f[i][j], f[i][j - x] + 1);
                }
            }
        }

        return f[n][target] > 0 ? f[n][target] : -1;
    }

}
