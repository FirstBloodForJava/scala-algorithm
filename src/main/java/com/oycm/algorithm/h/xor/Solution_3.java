package com.oycm.algorithm.h.xor;


public class Solution_3 {

    /**
     * 2433. <a href="https://leetcode.cn/problems/find-the-original-array-of-prefix-xor/description/">找出前缀异或的原始数组</a> 1367
     *
     * @param pref
     * @return
     */
    public int[] findArray(int[] pref) {
        /*
        pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i] 求 pref 对应的原始数组
        pref[0] = arr[0]
        pref[1] = arr[0] ^ arr[1] = pref[0] ^ arr[1] => arr[1] = pref[1] ^ pref[0]
        pref[2] = arr[0] ^ arr[1] ^ arr[2] = pref[1] ^ arr[2] => arr[2] = pref[2] ^ pref[1]

         */
        int pre = pref[0];
        for (int i = 1; i < pref.length; i++) {
            int temp = pref[i];
            pref[i] = pref[i] ^ pre;
            pre = temp;
        }
        return pref;
    }
}
