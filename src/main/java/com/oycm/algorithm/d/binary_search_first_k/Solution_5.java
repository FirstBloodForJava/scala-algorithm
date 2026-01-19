package com.oycm.algorithm.d.binary_search_first_k;


public class Solution_5 {

    /**
     * 1201. <a href="https://leetcode.cn/problems/ugly-number-iii/description/">丑数 III</a> 2039
     * <p>
     * 丑数是可以被 a 或 b 或 c 整除的 正整数
     *
     * @param n
     * @param a
     * @param b
     * @param c
     * @return 求第 n 个丑数
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        /*
        |a ∪ b ∪ c| = |a| + |b| + |c| - |a ∩ b| - |b ∩ c| - |a ∩ c| - |a ∩ b ∩ c|
        怎么求 a, b,c 的最小公倍数
         */
        long left = 0;
        long right = (long) Math.min(Math.min(a, b), c) * n;

        return (int) right;
    }

}
