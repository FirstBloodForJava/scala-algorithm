package com.oycm.algorithm.h.xor;


public class Solution_2 {

    /**
     * 1720. <a href="https://leetcode.cn/problems/decode-xored-array/description/">解码异或后的数组</a> 1284
     *
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        /*
        encoded[i] = ans[i] ^ ans[i+1]
        ans[i+1] = encoded[i] ^ ans[i]
         */
        int n = encoded.length;
        int[] ans = new int[n + 1];
        ans[0] = first;
        for (int i = 0; i < n; i++) {
            ans[i + 1] = encoded[i] ^ ans[i];
        }

        return ans;
    }
}
