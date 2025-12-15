package com.oycm.algorithm.h.basic;

public class Solution_13 {

    /**
     * <a href="https://leetcode.cn/problems/insert-into-bits-lcci/description/">面试题 05.01. 插入</a>
     *
     * @param N
     * @param M
     * @param i
     * @param j
     * @return
     */
    public int insertBits(int N, int M, int i, int j) {
        /*
        M 对应的二进制数字插入 N 对应的二进制数字的第 [i,j] (i <= j) 位区域，不足之处用 0 补齐,

        题解思路：先把 N 的 [i, j] 归零，可以构建 [i,j] 的 11000 取反后和 N 做 与运算
        构建 1 的过程 [i,j] 区间有 j-i+1 个
            1. 先把 a = 1 << j-i+1
            2. a - 1 还原了 j-i+1 个 1
            3. 把 1 用移动到 [i,j] 位置 (a-1) << i
            ((1 << (j-i+1)) - 1) << i
            ~(((1 << (j-i+1)) - 1) << i)
         M << i 填入 N
         */
        N &= ~(((1 << (j-i+1)) - 1) << i);
        return N | (M << i);
    }

}
