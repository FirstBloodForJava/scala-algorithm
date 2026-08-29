package com.oycm.dp.e.constraint_divide;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 3599. <a href="https://leetcode.cn/problems/partition-array-to-minimize-xor/description/">划分数组得到最小 XOR</a> 1955
     *
     * @param nums
     * @param k
     * @return
     */
    public int minXor(int[] nums, int k) {
        /*
        给你一个整数数组 nums 和一个整数 k。
        你的任务是将 nums 分成 k 个非空的 子数组 。对每个子数组，计算其所有元素的按位 XOR 值。
        返回这 k 个子数组中 最大 XOR(^) 的 最小值 。
        子数组 是数组中连续的 非空 元素序列。
         */
        /*
        最大值最小化
         */
        int n = nums.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        // j 个连续子数组
        for (int j = 1; j <= k; j++) {
            // 长为 i 的前缀，要有 j 个连续子数组，长度至少为 j
            // 倒序遍历，可以空间优化
            for (int i = n - (k - j); i >= j ; i--) {
                // i <= n - (k-j) 是因为 j < k 时，计算到后面的 i 对答案无用
                f[i] = Integer.MAX_VALUE;
                int xor = 0;
                // 枚举最后一个子数组的左端点 l, 从右到左 子数组异或和
                for (int l = i - 1; l >= j - 1; l--) {

                    xor ^= nums[l];
                    // 最大值最小化
                    f[i] = Math.min(f[i], Math.max(f[l], xor));
                }
            }
        }

        return f[n];
    }

}
