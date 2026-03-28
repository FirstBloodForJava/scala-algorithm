package com.oycm.week.No179;

public class Solution_2 {

    static int MOD = 1000000007;

    public int countVisiblePeople(int n, int pos, int k) {
        /*
        组合
        阶乘 + 除法 的模运算
         */
        // 预处理阶乘和逆元
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];

        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[n] = pow(fact[n], MOD - 2);
        for (int i = n; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        long ans = 0;

        for (int ls = 0; ls <= k; ls++) {

            int rs = k - ls;

            if (ls <= pos && rs <= n - pos - 1) {

                long waysLeft = comb(pos, ls, fact, invFact);
                long waysRight = comb(n - pos - 1, rs, fact, invFact);

                ans = (ans + waysLeft * waysRight * 2) % MOD;
            }
        }

        return (int) ans;
    }

    public long comb(int n, int k, long[] fact, long[] invFact) {
        if (k < 0 || k > n) return 0;
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

    public long pow(long x, long n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }
}
