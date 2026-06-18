package com.oycm.dp.h.longest_palindromic_subsequence;

public class Solution_1 {

    /**
     * 516. <a href="https://leetcode.cn/problems/longest-palindromic-subsequence/description/">最长回文子序列</a>
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        /*
        给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
        子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
         */
        /*
        计算 [0, n-1] 字符串回文子序列长度，
            如果 s[0] = s[n-1] 去掉头尾字符，看 [1, n-2] 字符串回文子序列长度
            计算 [1, n-1] 字符串回文子序列长度，计算 [0, n-2] 字符串回文子序列长度；两者取最大值
        子问题与原问题相似，可以用递归解决
        dfs(i, j) =
            s[i] = s[j]; dfs(i+1, j-1) + 2;
            s[i] != s[j]; max(dfs(i, j-1), dfs(i+1, j))
         */
        int n = s.length();
        return dfs(0, n - 1, s.toCharArray(), new int[n][n]);
    }

    public int dfs(int i, int j, char[] cs, int[][] memo) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (memo[i][j] > 0) return memo[i][j];
        int res = 0;
        if (cs[i] == cs[j]) {
            res = dfs(i + 1, j - 1, cs, memo) + 2;
        } else {
            res = Math.max(dfs(i, j - 1, cs, memo), dfs(i + 1, j, cs, memo));
        }
        return memo[i][j] = res;
    }

    public int longestPalindromeSubseq_DP(String s) {
        /*
        dfs(i, j) =
            s[i] = s[j]; dfs(i+1, j-1) + 2;
            s[i] != s[j]; max(dfs(i, j-1), dfs(i+1, j))
        f[i][j] =
            s[i] = s[j]; f[i+1][j-1] + 2;
            s[i] != s[j]; max(f[i+1][j], f[i][j-1])
        循环顺序该怎么写呢？
            要计算 f[i][j]，必须先把 f[i+1][*] 计算出来，那么 i 只从从大到小枚举才能做到；
            对于 j 来说，需要用到 f[i][j-1]，必须先把 f[i][j-1] 计算出来，所以 j 要从小到大枚举。
         */
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                f[i][j] = cs[i] == cs[j] ? f[i + 1][j - 1] + 2 :
                        Math.max(f[i + 1][j], f[i][j - 1]);
            }
        }

        return f[0][n - 1];
    }

    public int longestPalindromeSubseq_DP_optimize(String s) {
        /*
        dfs(i, j) =
            s[i] = s[j]; dfs(i+1, j-1) + 2;
            s[i] != s[j]; max(dfs(i, j-1), dfs(i+1, j))
        f[i][j] =
            s[i] = s[j]; f[i+1][j-1] + 2;
            s[i] != s[j]; max(f[i+1][j], f[i][j-1])
        循环顺序该怎么写呢？
            要计算 f[i][j]，必须先把 f[i+1][*] 计算出来，那么 i 只从从大到小枚举才能做到；
            对于 j 来说，需要用到 f[i][j-1]，必须先把 f[i][j-1] 计算出来，所以 j 要从小到大枚举。
         */
        /*
        空间优化 f[i+1][j-1] 的是历史数据，之会被 f[i][j] 用到一次
         */
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] f = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            f[i] = 1;
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                int temp = f[j];
                f[j] = cs[i] == cs[j] ? pre + 2 : Math.max(f[j], f[j - 1]);
                pre = temp;
            }
        }

        return f[n - 1];
    }

}
