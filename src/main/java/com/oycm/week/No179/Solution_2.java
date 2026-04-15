package com.oycm.week.No179;

public class Solution_2 {

    static int MOD = 1000000007;

    /**
     * 3881. <a href="https://leetcode.cn/problems/direction-assignments-with-exactly-k-visible-people/">恰好看到 K 个人的方向选择</a> 1761
     *
     * @param n   [1, 1e5]
     * @param pos
     * @param k
     * @return 可能的方向分配数量，使得位于下标 pos 的人 恰好 看到 k 个人
     */
    public int countVisiblePeople(int n, int pos, int k) {
        /*
        每个人 独立地 选择一个方向：
            'L'：只对他们 右边 的人 可见
            'R'：只对他们 左边 的人 可见
        位于下标 pos 的人看其他人的方式如下：
            一个 i < pos 的人可见当且仅当他们选择 'L'。
            一个 i > pos 的人可见当且仅当他们选择 'R'。
         */
        /*
        组合
        阶乘 + 除法 的模运算
         */
        /*
        pos 看到 k 个人，有以下确定条件：
            k 个人的方向确定的，在 pos 左边的人的方向为 L，在 pos 右边的人的方向为 R；
            其余 n - 1 - k 个人的方向也是确定的，在 pos 左边的人的方向为 R，在 pos 右边的人的方向为 L；
        相当于从 n - 1 个人中选出 k 个人，每种方案，n - 1 个人的方向都是唯一，pos 有两个方向，所以 2 * C(n-1, k)
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
            // 左右看见人数够 k 个才计算
            if (ls <= pos && rs <= n - pos - 1) {
                // 左边 pos 个人要看到 ls 个人，ls 个人方向为 L，pos - ls 个人方向为 R，每种方案的方向都是固定，可以使用 C(n, m) 计算；右边同理
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
