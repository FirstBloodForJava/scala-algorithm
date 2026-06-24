package com.oycm.month2026.june;

public class Solution_24 {

    /**
     * 3700. <a href="https://leetcode.cn/problems/number-of-zigzag-arrays-ii/description/">锯齿形数组的总数 II</a> 2435
     *
     * @param n
     * @param l
     * @param r
     * @return
     */
    public int zigZagArrays(int n, int l, int r) {
        /*
        给你 三个整数 n、l 和 r。
        长度为 n 的锯齿形数组定义如下：
            每个元素的取值范围为 [l, r]。
            任意 两个 相邻的元素都不相等。
            任意 三个 连续的元素不能构成一个 严格递增 或 严格递减 的序列。
        返回满足条件的锯齿形数组的总数。
        由于答案可能很大，请将结果对 109 + 7 取余数。
        序列 被称为 严格递增 需要满足：当且仅当每个元素都严格大于它的前一个元素（如果存在）。
        序列 被称为 严格递减 需要满足，当且仅当每个元素都严格小于它的前一个元素（如果存在）。
         */
        /*
        3 <= n <= 1e9
        1 <= l < r <= 75
         */
        /*
        题解：矩阵快速幂优化 DP
         */
        int k = r - l + 1;
        long[][] m = new long[k * 2][k * 2];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < i; j++) {
                m[i][k + j] = 1;
            }
            for (int j = i + 1; j < k; j++) {
                m[k + i][j] = 1;
            }
        }

        long[][] f1 = new long[k * 2][1];
        for (int i = 0; i < k * 2; i++) {
            f1[i][0] = 1;
        }

        long[][] fn = powMul(m, n - 1, f1);

        long ans = 0;
        for (long[] row : fn) {
            ans += row[0];
        }
        return (int) (ans % MOD);
    }

    private static final int MOD = 1_000_000_007;

    // a^n * f0
    private long[][] powMul(long[][] a, int n, long[][] f0) {
        long[][] res = f0;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = mul(a, res);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }

    // 返回矩阵 a 和矩阵 b 相乘的结果
    private long[][] mul(long[][] a, long[][] b) {
        long[][] c = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
                if (a[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < b[k].length; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }

}
