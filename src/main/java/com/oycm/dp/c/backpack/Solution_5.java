package com.oycm.dp.c.backpack;

public class Solution_5 {

    /**
     * 2787. <a href="https://leetcode.cn/problems/ways-to-express-an-integer-as-sum-of-powers/description/">将一个数字表示成幂的和的方案数</a> 1818
     *
     * @param n [1, 300]
     * @param x [1, 5]
     * @return
     */
    public int numberOfWays(int n, int x) {
        /*
        给你两个 正整数 n 和 x 。
        请你返回将 n 表示成一些 互不相同 正整数的 x 次幂之和的方案数。
        换句话说，你需要返回互不相同整数 [n1, n2, ..., nk] 的集合数目，满足 n = n1^x + n2^x + ... + nk^x 。
        由于答案可能非常大，请你将它对 1^9 + 7 取余后返回。
        比方说，n = 160 且 x = 3 ，一个表示 n 的方法是 n = 2^3 + 3^3 + 5^3 。
         */
        /*
        正整数互不相同，选/不选 和为 n 的方案数
        [1, m] 选
         */
        /*int m = (int) Math.pow(n, 1.0 / x) + 1;
        int[][] memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(m, n, x, memo);*/
        /*
        f[i+1][j] = f[i][j] + f[i][j-m]
         */
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; Math.pow(i, x) <= n; i++) {
            int m = (int) Math.pow(i, x);
            for (int j = n; j >= m; j--) {
                f[j] += f[j - m];
            }
        }

        return (int) (f[n] % 1000000007);
    }

    public int dfs(int i, int j, int x, int[][] memo) {
        if (i == 0) {
            return j == 0 ? 1 : 0;
        }
        if (memo[i][j] != -1) return memo[i][j];
        int n = (int) Math.pow(i, x);
        int res = dfs(i - 1, j, x, memo) % 1000000007;
        if (j >= n) {
            res = (res + dfs(i - 1, j - n, x, memo)) % 1000000007;
        }
        return memo[i][j] = res;
    }

    public static void main(String[] args) {
        Solution_5 solution_5 = new Solution_5();
        System.out.println(solution_5.numberOfWays(64, 3));
    }

}
