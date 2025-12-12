package com.oycm.algorithm.f.other;


public class Solution_1 {

    /**
     * 1310. <a href="https://leetcode.cn/problems/xor-queries-of-a-subarray/description/">子数组异或查询</a> 1460
     * <p>
     * queries[i] [l, r] 表示查询 arr [l,r] 区间数组元素的 XOR 值
     *
     * @param arr     长为 n
     * @param queries 长为 q
     * @return
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        /*
        这里应该需要使用 异或运算的特点。同为 0, 异为 1。相同值异或结果为 0
        定义长为 n+1 的数组 xor, xor[i] 表示 arr [0, i] 的 异或值
        queries[i] = [l, r]
            当 l = 0 时, ans[i] = xor[r+1]
            当 l > 0 时, arr[l] ^ arr[l+1] ... arr[r]
                = (arr[0] ^ arr[1] ... arr[l-1]) ^ (arr[0] ^ arr[1] ... arr[l-1]) ^ (arr[l] ^ arr[l+1] ... arr[r])
                = xor[l] ^ xor[r+1]
            xor[0] = 0

         */
        int n = arr.length;
        int[] xor = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }

        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            ans[i] = xor[queries[i][0]] ^ xor[queries[i][1] + 1];
        }

        return ans;
    }
}
