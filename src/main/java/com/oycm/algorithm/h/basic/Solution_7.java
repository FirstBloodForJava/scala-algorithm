package com.oycm.algorithm.h.basic;


public class Solution_7 {

    /**
     * 476. <a href="https://leetcode.cn/problems/number-complement/description/">数字的补数</a>
     * 整数的二进制表示取反后，再转换为十进制表示，可以得到这个整数的补数。
     *
     * @param num
     * @return
     */
    public int findComplement(int num) {
        /*
        前倒 0 不取反
        取反后 与 num 最高位的全集取交集
         */
        return ~num & ((1 << (32 - Integer.numberOfLeadingZeros(num))) -1);
    }
}
