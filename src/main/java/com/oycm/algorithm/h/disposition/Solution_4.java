package com.oycm.algorithm.h.disposition;

public class Solution_4 {

    /**
     * 1835. <a href="https://leetcode.cn/problems/find-xor-sum-of-all-pairs-bitwise-and/">所有数对按位与结果的异或和</a> 1825
     *
     * @param arr1
     * @param arr2
     * @return 求 arr1[i] 和 arr2[j] 按位与组成的列表 异或和
     */
    public int getXORSum(int[] arr1, int[] arr2) {
        /*
        arr1[0] & arr2[0]
        arr1[0] & arr2[1]
        arr1[0] & arr2[2]
        arr1[0] & arr2[3]
        (a & b) ^ (a & c) == a & (b ^ c)
        当 a = 0, (0 & b) ^ (a & c) == 0 == 0 & (b ^ c)
        当 a = 1, (1 & b) ^ (1 & c) == b ^ c == 1 & (b ^ c)
        arr1[0] & (xor(arr2)) ^ arr1[1] & (xor(arr2))
         */
        int ans = 0;
        int xor = 0;
        for (int i = 0; i < arr2.length; i++) {
            xor ^= arr2[i];
        }
        for (int i = 0; i < arr1.length; i++) {
            ans ^= arr1[i] & xor;
        }
        return ans;
    }
}
