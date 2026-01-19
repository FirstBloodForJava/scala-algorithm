package com.oycm.algorithm.d.binary_search_first_k;


public class Solution_4 {

    /**
     * 878. <a href="https://leetcode.cn/problems/nth-magical-number/description/">第 N 个神奇数字</a>
     * <p>
     * 神奇数字: 正整数能被 a 或 b 整除
     *
     * @param n
     * @param a
     * @param b
     * @return 求第 n 个神奇数字
     */
    public int nthMagicalNumber(int n, int a, int b) {
        /*
        直接枚举计算, n 太大, x 越大, 神奇数字的个数越多
        问题转换成 第 n 个神奇数字是 x, <= x 的神奇数字有 n 个, 找最小的 x, 至少有 n 个神奇数字
         */
        // 最大公约数
        int gcd = gcd(a, b);
        // 最小公倍数
        int lcm = a * b / gcd;
        long left = 1;
        long right = (long) Math.min(a, b) * n;

        while (left + 1 < right) {
            long mid = (right + left) / 2;
            if (mid / a + mid / b - mid / lcm >= n) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return (int) (right % 1000000007);
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
