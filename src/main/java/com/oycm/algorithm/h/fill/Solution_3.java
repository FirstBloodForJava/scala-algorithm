package com.oycm.algorithm.h.fill;

public class Solution_3 {

    /**
     * 3007. <a href="https://leetcode.cn/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/description/">价值和小于等于 K 的最大数字</a> 2258
     * <p>
     * 整数 num 的价值表示 二进制 在 x, 2x, 3x 等位置 1 的数目
     *
     * @param k
     * @param x
     * @return
     */
    public static long findMaximumNumber(long k, int x) {
        /*
        二分答案: 随着 num 变大, 价值不断变大 求大于等于 k 的最后一个 index, >= k+1 的 第一个 index-1
        怎么构建 第 x, 2x, 3x 位 是 1 的基数
        todo 超时 待解答
         */
        long ans = 0;
        long r = Long.MAX_VALUE;
        long base = 1L << (x - 1);
        for (int i = 2 * x - 1; i < 64; i += x) {
            base |= 1L << i;
        }
        long l = 0;
        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            long prices = 0;
            boolean flag = true;
            for (long i = mid; i >= 1; i--) {
                prices += Long.bitCount(base & i);
                if (prices >= k + 1) {
                    r = mid;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                l = mid;
            }

        }

        return r - 1;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumNumber(7, 2));
        System.out.println(findMaximumNumber(9, 1));
    }

}
