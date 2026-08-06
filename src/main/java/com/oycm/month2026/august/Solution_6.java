package com.oycm.month2026.august;

public class Solution_6 {

    /**
     * 3345. <a href="https://leetcode.cn/problems/smallest-divisible-digit-product-i/description/">最小可整除数位乘积 I</a>
     *
     * @param n
     * @param t
     * @return
     */
    public int smallestNumber(int n, int t) {
        /*
        给你两个整数 n 和 t 。请你返回大于等于 n 的 最小 整数，且该整数的 各数位之积 能被 t 整除。
         */
        /*
        1 <= n <= 100
        1 <= t <= 10
         */
        /*
        0 能整除 任何 t
        如果 n 是 个位数，如果个位找不到数位乘积 % t 为 0，那么 10 一定是最小符合要求整数
        如果 n 是 两位数，十位从 1 到 9，个位从 0 到 9，找到第一个数位乘积 % t == 0 的数
        n 的十位记为 i，各位记为 j，如果 i * [j, 9] 都不和要求，那么 (i+1) * 10 数位乘积为 0，肯定符合要求
        当 i = 9 时，也不符合要求，那么 i + 1 = 10 * 10 也是最小符合要求整数
         */
        if (n <= 10) {
            for (int i = n; i < 10; i++) {
                if (i % t == 0) return i;
            }
            return 10;
        }
        if (n < 100) {
            int i = n / 10;
            for (int j = n % 10; j < 10; j++) {
                if (i * j % t == 0) {
                    return i * 10 + j;
                }
            }
            return (i + 1) * 10;
        }

        return 100;
    }

}
