package com.oycm.algorithm.f.prefix_sum_advance;

public class Solution_7 {

    /**
     * 3756. 连接非零数字并乘以其数字和 II
     * 3756. <a href="https://leetcode.cn/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/description/">连接非零数字并乘以其数字和 II</a> 1968
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
        区间所有数字之和可以用前缀和快速计算。

         */
        init();
        int n = s.length();
        int[] sum = new int[n + 1];
        int[] preNum = new int[n + 1];
        int[] sumNoZero = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            sum[i + 1] = sum[i] + d;
            preNum[i + 1] = d > 0 ? (int) ((preNum[i] * 10L + d) % MOD) : preNum[i];
            sumNoZero[i + 1] = sumNoZero[i] + (d > 0 ? 1 : 0);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1] + 1;
            int length = sumNoZero[r] - sumNoZero[l];
            long x = preNum[r] - (long) preNum[l] * pow10[length] % MOD + MOD;
            ans[i] = (int) (x * (sum[r] - sum[l]) % MOD);
        }

        return ans;
    }

    public static final int MOD = 1000000007;
    public static final int MAX_N = 100001;
    public static final int[] pow10 = new int[MAX_N];
    public static boolean initialized = false;

    public void init() {
        if (initialized) return;
        initialized = true;
        pow10[0] = 1;
        for (int i = 1; i < MAX_N; i++) {
            pow10[i] = (int) (pow10[i - 1] * 10L % MOD);
        }
    }


}
