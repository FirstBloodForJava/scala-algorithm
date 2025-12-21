package com.oycm.algorithm.h.identical_equation;

public class Solution_1 {

    /**
     * 1835. <a href="https://leetcode.cn/problems/find-xor-sum-of-all-pairs-bitwise-and/description/">所有数对按位与结果的异或和</a> 1825
     *
     *
     * @param arr1
     * @param arr2
     * @return 求 arr1 和 arr2 与运算结果列表的异或和
     */
    public int getXORSum(int[] arr1, int[] arr2) {
        /*
        (a & b) ^ (a & c) = a & (b ^ c)
        arr1[0] 和 arr2[i] 的 异或和 = arr1[0] & xor(arr2)
        arr1[1] 和 arr2[i] 的 异或和 = arr1[1] & xor(arr2)
        xor(arr2) & xor(arr1)
         */
        int xor1 = 0;
        int xor2 = 0;
        for (int arr : arr1) {
            xor1 ^= arr;
        }
        for (int arr : arr2) {
            xor2 ^= arr;
        }


        return xor1 & xor2;
    }

}
