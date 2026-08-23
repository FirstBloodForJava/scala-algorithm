package com.oycm.dp.d.lcs.basic;

import java.util.Arrays;

public class Solution_7 {

    /**
     * 3836. <a href="https://leetcode.cn/problems/maximum-score-using-exactly-k-pairs/description/">恰好 K 个下标对的最大得分</a> 1988
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public long maxScore(int[] nums1, int[] nums2, int k) {
        /*
        给你两个长度分别为 n 和 m 的整数数组 nums1 和 nums2，以及一个整数 k。
        你必须 恰好 选择 k 对下标 (i1, j1), (i2, j2), ..., (ik, jk)，使得：
            0 <= i1 < i2 < ... < ik < n
            0 <= j1 < j2 < ... < jk < m
        对于每对选择的下标 (i, j)，你将获得 nums1[i] * nums2[j] 的得分。
        总 得分 是所有选定下标对的乘积的 总和。
        返回一个整数，表示可以获得的 最大 总得分。
         */
        /*
        dfs(i, j, k) = max
            dfs(i-1, j, k),
            dfs(i, j-1, k),
            dfs(i-1, j-1, k-1) + a * b

         */
        int n = nums1.length;
        int m = nums2.length;
        long[][][] f = new long[k + 1][n + 1][m + 1];
        for (int K = 1; K <= k; K++) {
            for (long[] row : f[K]) {
                Arrays.fill(row, Long.MIN_VALUE);
            }
        }
        for (int K = 1; K <= k; K++) {
            // 选了 K 个，k-K 个待选
            for (int i = K - 1; i < n - (k - K); i++) {
                for (int j = K - 1; j < m - (k - K); j++) {
                    f[K][i + 1][j + 1] = Math.max(
                            Math.max(f[K][i][j + 1], f[K][i + 1][j]),
                            f[K - 1][i][j] + (long) nums1[i] * nums2[j]
                    );
                }
            }
        }

        return f[k][n][m];
    }

}
