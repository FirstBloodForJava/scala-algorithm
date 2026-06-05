package com.leetcode.interview.math_bit;

public class Solution_7 {

    /**
     * 计数质数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        /*
        给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
         */
        /*
        0 <= n <= 5 * 1e6
         */
        init();
        int res = 0;

        for (int i = 2; i < n; i++) {
            if (!isPrim[i]) res++;
        }

        return res;
    }

    public static boolean initialed = false;
    public static boolean[] isPrim = new boolean[5000001];

    public void init() {
        if (initialed) return;
        initialed = true;
        for (int i = 2; i < isPrim.length; i++) {
            if (!isPrim[i]) {
                for (int j = i + i; j < isPrim.length; j += i) {
                    isPrim[j] = true;
                }
            }
        }
    }

}
