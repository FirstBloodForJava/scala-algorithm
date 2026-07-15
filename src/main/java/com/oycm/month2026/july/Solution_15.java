package com.oycm.month2026.july;

public class Solution_15 {

    /**
     * 3658. 奇数和与偶数和的最大公约数
     * <br>
     * 3658. <a href="https://leetcode.cn/problems/gcd-of-odd-and-even-sums/description/">奇数和与偶数和的最大公约数</a> 1220
     *
     * @param n
     * @return
     */
    public int gcdOfOddEvenSums(int n) {
        /*
        给你一个整数 n。请你计算以下两个值的 最大公约数（GCD）：
            sumOdd：最小的 n 个正奇数的总和。
            sumEven：最小的 n 个正偶数的总和。
        返回 sumOdd 和 sumEven 的 GCD。
         */
        /*
        等差数列求和，再求 gcd
        sum = na1 + n*(n-1)*d/2
         */
//        int sumOdd = n + n * (n - 1);
//        int sumEven = 2 * n + n * (n - 1);
        return n;
    }

}
