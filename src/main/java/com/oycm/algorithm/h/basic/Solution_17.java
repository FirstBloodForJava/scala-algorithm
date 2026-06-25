package com.oycm.algorithm.h.basic;

public class Solution_17 {

    /**
     * 338. <a href="https://leetcode.cn/problems/counting-bits/description/">比特位计数</a>
     *
     * todo 后续尝试动态规划解答
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        /*
        计算 0-n 中对应二进制中 1 的个数
         */
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n ; i++) {
            ans[i] = Integer.bitCount(i);
        }
        return ans;
    }

    public int[] countBits_dp(int n) {
        /*
        计算 i 的 1 bit 位数，如果存在 0 <= j < i，且 i 和 和 j 相比，i 的二进制只多了一个 1，还需要再计算 i 的 1 bit 位数吗？不需要
        如果知道 小于等于 i 的最大 2 的幂，那么 bit[i] = bit[i-highBit] + 1;
         */
        int[] ans = new int[n + 1];
        int highBit = 0;
        for (int i = 0; i <= n ; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            ans[i] = ans[i - highBit] + 1;
        }
        return ans;
    }
}
