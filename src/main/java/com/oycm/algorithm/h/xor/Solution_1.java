package com.oycm.algorithm.h.xor;


public class Solution_1 {

    /**
     * 1486. <a href="https://leetcode.cn/problems/xor-operation-in-an-array/description/">数组异或操作</a> 1181
     *
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        /*
        nums[i] = start + 2 * i, i in [0, n-1]
         */
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= start + 2 * i;
        }

        return xor;
    }
}
