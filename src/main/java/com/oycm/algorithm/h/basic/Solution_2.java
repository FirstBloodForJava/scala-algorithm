package com.oycm.algorithm.h.basic;


public class Solution_2 {

    /**
     * 3226. <a href="https://leetcode.cn/problems/number-of-bit-changes-to-make-two-integers-equal/description/">使两个整数相等的位更改次数</a> 1247
     *
     * 选择 n 的 二进制表示 中任意一个值为 1 的位，并将其改为 0
     * @param n 正整数
     * @param k 正整数
     * @return 求 使得 n 等于 k 所需要的更改次数，无法相等返回 -1
     */
    public int minChanges(int n, int k) {
        /*
        n 二进制表示集合 a, k 的二进制表示集合 b
        a ∩ b == b, 先判断是否满足条件，再看怎么计算需要删除的元素
        b 对 a 的补给的数量就是需要删除的元素
         */
        if ((n & k) != k) {
            return -1;
        }
        return Integer.bitCount(n & (~k));
    }
}
