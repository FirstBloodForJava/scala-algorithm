package com.oycm.math;

public class Combinatorics {

    /**
     * 1735. <a href="https://leetcode.cn/problems/count-ways-to-make-array-with-product/description/">生成乘积数组的方案数</a> 2500
     *
     * @param queries queries[i] = [n, k], 第 i 个查询 queries[i] 要求构造长度为 n，每个元素都是正整数的数组，且满足所有元素的乘积为 k，找出有多少种可行的方案，对 1e9 + 7 取模。
     * @return
     */
    public int[] waysToFillArray(int[][] queries) {
        /*
        组合数学：把 m 个无区别小球放入 n 个有区别盒子，允许空盒放置的方案数
        假设 m = 3, n = 5
        先增加 n = 5 个小球；总共 8 个小球，小球之间共 7 个空隙，插入 4 个隔板，
        就可以分为 5 个盒子，如果每个盒子移除一个小球，就得到了最终的放法。
        把 3 个无区别的小球，放入 5 个有区别的盒子，允许空盒的方案数。
        有以下等价：
            把 8 个无区别的小球放入 5 个有区别的和，不允许空盒的方案数。
            在 7 个空隙中选择 4 个空隙，放入隔板的方案数。（7 个不同的位置，选出 4 个位置的方案数）
            C(n + m - 1, n - 1) == C(n + m - 1, m)
        [1,1,1,1,1,1,1,1]
        这里就是 n 个盒子，k 的质因子个数表示不同球的个数，k = p1^e1 * p2^e2 ...
        res = C(n + e1 - 1, e1) * C(n + e2 - 1, e2) * ...
         */
        /*
        组合数预处理, C(n, m) n 个物品中选 m 个物品，对于第 n 个物品，可以用 选/不选 来思考
            选 C(n-1, m-1)
            不选 C(n-1, m)
        所以 C(n, m) = C(n-1, m-1) + C(n-1, m)
        C(n, 0) = 1
        n <= 1e4,
        2^13 < 1e4 < 2^14, m 最大 14 C(1e4 + 13 - 1, 13)
         */
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            long res = 1;
            int n = queries[i][0];
            int k = queries[i][1];
            for (int p = 2; p * p <= k; p++) {
                if (k % p == 0) {
                    int e = 1;
                    for (k /= p; k % p == 0; k /= p) {
                        e++;
                    }
                    res = res * com[n + e - 1][e] % mod;
                }
            }

            if (k > 1) {
                res = res * n % mod;
            }
            ans[i] = (int) res;
        }

        return ans;
    }

    public static final int mod = 1000000007;
    public static final int[][] com = new int[10013][14];

    static {
        com[0][0] = 1;
        for (int i = 1; i < com.length; i++) {
            com[i][0] = 1;
            for (int j = 1; j < com[i].length; j++) {
                com[i][j] = (com[i - 1][j - 1] + com[i - 1][j]) % mod;
            }
        }
    }

    public static final int maxN = 10013;
    public static final int maxK = 10001;

    public static final long[] F = new long[maxN];
    // 1/i!
    public static final long[] INV_F = new long[maxN];
    // lpf[i] 表示 i 的最小质因子
    public static final int[] lpf = new int[maxK];

    static {
        F[0] = 1;
        for (int i = 1; i < F.length; i++) {
            F[i] = F[i - 1] * i % mod;
        }
        INV_F[maxN - 1] = pow(F[maxN - 1], mod - 2);
        for (int i = maxN - 1; i > 0; i--) {
            INV_F[i - 1] = INV_F[i] * i % mod;
        }
        /*
        埃氏筛法
        https://oi-wiki.org/math/number-theory/sieve/
         */
        for (int i = 2; i < lpf.length; i++) {
            if (lpf[i] == 0) {
                for (int j = i; j < lpf.length; j += i) {
                    if (lpf[j] == 0) {
                        lpf[j] = i;
                    }
                }
            }
        }
    }

    public static long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
        }
        return res;
    }

    public static long comb(int n, int m) {
        return F[n] * INV_F[m] % mod * INV_F[n - m] % mod;
    }

    public int[] waysToFillArray_2(int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int n = queries[i][0];
            int k = queries[i][1];
            long res = 1;
            while (k > 1) {
                int p = lpf[k];
                int e = 1;
                for (k /= p; k % p == 0; k /= p) {
                    e++;
                }
                res = res * comb(n + e - 1, e) % mod;
            }
            ans[i] = (int) res;
        }

        return ans;
    }
}
