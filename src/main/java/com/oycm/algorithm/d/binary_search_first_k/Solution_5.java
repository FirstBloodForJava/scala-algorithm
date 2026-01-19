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
        注意 要加上 abcLcm 的数量
        |a ∪ b ∪ c| = |a| + |b| + |c| - |a ∩ b| - |b ∩ c| - |a ∩ c| + |a ∩ b ∩ c|
        怎么求 a, b,c 的最小公倍数
         */
        long left = 0;
        long right = (long) Math.min(Math.min(a, b), c) * n;

        long abLcm = (long) a * b / gcd(a, b);
        long acLcm = (long) a * c / gcd(a, c);
        long bcLcm = (long) b * c / gcd(b, c);
        long abcLcm = abLcm * c / gcd(abLcm, c);
        while (left + 1 < right) {
            long mid = (right + left) / 2;
            if (check(mid, a, b, c, abLcm, acLcm, bcLcm, abcLcm, n)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return (int) right;
    }

    public boolean check(long x, int a, int b, int c, long ab, long ac, long bc, long abc, int n) {
        return x / a + x / b + x / c - x / ab - x / ac - x / bc + x / abc >= n;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
