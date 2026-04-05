package com.oycm.math;

public class FastPower {

    /**
     * 50. <a href="https://leetcode.cn/problems/powx-n/description/">Pow(x, n)</a>
     *
     * @param x [-100, 100]
     * @param n -2^31, 2^31 - 1
     * @return x^n
     */
    public double myPow(double x, int n) {
        /*
        x^n, n 看成队友的二进制
        n = 13 = 1101
        1101
        8421
        单独比特位对应的值，表示 x^?，如果为 1 则乘以它
        x * x^4 ^ x^8 = x^13
         */
        double ans = 1;
        long N = n;
        if (n < 0) {
            N = -n;
            x = 1 / x;
        }
        while (N > 0) {
            if ((N & 1) == 1) {
                ans *= x;
            }
            x *= x;
            N >>= 1;
        }

        return ans;
    }

}
