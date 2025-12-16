package com.oycm.algorithm.h.xor;

public class Solution_4 {

    /**
     * 1310. <a href="https://leetcode.cn/problems/xor-queries-of-a-subarray/">子数组异或查询</a> 1460
     *
     * @param arr
     * @param queries
     * @return
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        /*
        异或前缀和
        求 queries[i][0], queries[i][1] 连续子数组的异或和, 利用 异或运算的 结合律和归零律
        xor[l] ^ xor[r+1] == [l, r] 区间的异或和
         */
        int n = arr.length;
        int[] xor = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = xor[queries[i][0]] ^ xor[queries[i][1] + 1];
        }

        return ans;
    }
}
