package com.oycm.algorithm.h.basic;


public class Solution_11 {

    /**
     * 693. <a href="https://leetcode.cn/problems/binary-number-with-alternating-bits/description/">交替位二进制数</a>
     *
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        /*
        cur 和 next 比较
         */
        int cur = n & 1;
        while (n > 0) {
            if (cur != (n & 1)) {
                return false;
            } else {
                cur = cur == 0 ? 1 : 0;
                n = n >> 1;
            }
        }
        return true;
    }

}
