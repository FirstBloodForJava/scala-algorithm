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
}
