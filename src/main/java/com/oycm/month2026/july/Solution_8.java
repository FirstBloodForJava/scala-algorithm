package com.oycm.month2026.july;

public class Solution_8 {

    /**
     * 3756. 连接非零数字并乘以其数字和 II
     * <br>
     * 3756. <a href="https://leetcode.cn/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/description/">连接非零数字并乘以其数字和 II</a>1968
     *
     * @param s
     * @param queries
     * @return
     */
    public int[] sumAndMultiply(String s, int[][] queries) {
        /*
        给你一个长度为 m 的字符串 s，其中仅包含数字。另给你一个二维整数数组 queries，其中 queries[i] = [li, ri]。
        对于每个 queries[i]，提取 子串 s[li..ri]，然后执行以下操作：
            将子串中所有 非零数字 按照原始顺序连接起来，形成一个新的整数 x。如果没有非零数字，则 x = 0。
            令 sum 为 x 中所有数字的 数字和 。答案为 x * sum。
        返回一个整数数组 answer，其中 answer[i] 是第 i 个查询的答案。
        由于答案可能非常大，请返回其对 109 + 7 取余数的结果。
        子串 是字符串中的一个连续、非空 字符序列。
         */
        /*
        三个前缀和：
            数位前缀和；
            非 0 数字前缀和；
            非 0 数字前缀值
         */
        init();
        int n = s.length();
        int[] sums = new int[n + 1];
        int[] noZeros = new int[n + 1];
        int[] pre = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            sums[i + 1] = sums[i] + d;
            noZeros[i + 1] = noZeros[i] + (d > 0 ? 1 : 0);
            pre[i + 1] = d > 0 ? (int) ((pre[i] * 10L + d) % mod) : pre[i];
        }


        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1] + 1;
            int k = noZeros[r] - noZeros[l];
            long x = (pre[r] - (long) pre[l] * pow10s[k] % mod + mod) % mod;
            int sum = sums[r] - sums[l];
            ans[i] = (int) (x * sum % mod);
        }

        return ans;
    }

    private static boolean initialized = false;
    private static int[] pow10s = new int[100001];
    private static int mod = 1000000007;

    public void init() {
        if (initialized) return;
        initialized = true;
        pow10s[0] = 1;
        for (int i = 1; i < pow10s.length; i++) {
            pow10s[i] = (int) (pow10s[i - 1] * 10L % mod);
        }
    }

}
