package com.oycm.dp.d.lcs.basic;

import java.util.Arrays;

public class Solution_6 {

    /**
     * 1458. <a href="https://leetcode.cn/problems/max-dot-product-of-two-subsequences/description/">两个子序列的最大点积</a> 1824
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        /*
        给你两个数组 nums1 和 nums2 。
        请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
        数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。
        比方说，[2,3,5] 是 [1,2,3,4,5] 的一个子序列而 [1,5,3] 不是。
         */
        /*
        点积: ai * bi
         */
        int m = nums2.length;
        int[] f = new int[m + 1];
        Arrays.fill(f, Integer.MIN_VALUE);
        for (int a : nums1) {
            int pre = f[0];
            for (int j = 0; j < m; j++) {
                /*
                dfs(i, j, sum) = max()
                    dfs(i-1, j, sum)
                    dfs(i, j-1, sum)
                    max(dfs(i-1, j-1), 0) + a * b
                 */
                int temp = f[j + 1];
                f[j + 1] = Math.max(f[j], f[j + 1]);
                f[j + 1] = Math.max(Math.max(pre, 0) + a * nums2[j], f[j + 1]);

                pre = temp;
            }
        }

        return f[m];
    }
}
