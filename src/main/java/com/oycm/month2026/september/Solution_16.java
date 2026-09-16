package com.oycm.month2026.september;

public class Solution_16 {

    /**
     * 1621. <a href="https://leetcode.cn/problems/number-of-sets-of-k-non-overlapping-line-segments/description/">大小为 K 的不重叠线段的数目</a> 2198
     *
     * @param n
     * @param k
     * @return
     */
    public int numberOfSets(int n, int k) {
        /*
        给你一维空间的 n 个点，其中第 i 个点（编号从 0 到 n-1）位于 x = i 处，请你找到 恰好 k 个不重叠 线段且每个线段至少覆盖两个点的方案数。
        线段的两个端点必须都是 整数坐标 。这 k 个线段不需要全部覆盖全部 n 个点，且它们的端点 可以 重合。
        请你返回 k 个不重叠线段的方案数。由于答案可能很大，请将结果对 109 + 7 取余 后返回。
         */
        /*
        题解：组合数学
         */
        return (int) comb(n + k - 1, 2 * k);
    }

    private static final int mod = 1000000007;
    private static final int max = 1999;
    private static final long[] f = new long[max];
    private static final long[] invF = new long[max];
    private static boolean initialized = false;


    public void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        f[0] = 1;
        for (int i = 1; i < max; i++) {
            f[i] = f[i - 1] * i % mod;
        }
        // a/b % p, b 和 p 互质 => a * b^(p - 2) % p
        invF[max - 1] = pow(f[max - 1], mod - 2);
        for (int i = max - 1; i > 0; i--) {
            invF[i - 1] = invF[i] * i % mod;
        }
    }

    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
        }
        return res;
    }

    private long comb(int n, int m) {
        return f[n] * invF[m] % mod * invF[n - m] % mod;
    }

    public int numberOfSets_dp(int n, int k) {
        int[] f = new int[n];
        int[] preSum = new int[n + 1];
        for (int j = 0; j < n; j++) {
            f[j] = 1;
            preSum[j + 1] = preSum[j] + 1;
        }
        for (int i = 1; i <= k; i++) {
            f[0] = 0;
            for (int j = 1; j < n; j++) {
                f[j] = (f[j - 1] + preSum[j]) % mod;
            }
            for (int j = 0; j < n; j++) {
                // preSum[0] 始终为 0
                preSum[j + 1] = (preSum[j] + f[j]) % mod;
            }
        }
        return f[n - 1];
    }
}
