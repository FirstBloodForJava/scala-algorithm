package com.oycm.algorithm.f.prefix_sum_hash;

import java.util.TreeSet;

public class Solution_363 {

    /**
     * 363. <a href="https://leetcode.cn/problems/max-sum-of-rectangle-no-larger-than-k/description/">矩形区域不超过 K 的最大数值和</a>
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        /*
        给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
        题目数据保证总会存在一个数值和不超过 k 的矩形区域。
         */
        /*
        枚举所有矩形的各种高 n, n-1, ... 的和，问题转换成一个数组中，找到小于等于 k 的最大子数组和
        [l, r) sum[r] - sum[l] <= k，枚举子数组的右端点，查找符合要求的最小值 s[l](枚举有维护左)
        sum[r] - k <= sum[l]，前缀和要大 sum[l] 要越小，查找 大于等于 sum[r] - k 最小 sum[l]
         */
        int ans = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for (int j = i; j < m; j++) {
                for (int c = 0; c < n; c++) {
                    sum[c] += matrix[j][c];
                }
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    // 查找大于等于 sum[r] - k 的最小 sum[l]
                    Integer sl = set.ceiling(s - k);
                    if (sl != null) {
                        ans = Math.max(ans, s - sl);
                    }
                    set.add(s);
                }
            }

        }
        return ans;

    }

}
