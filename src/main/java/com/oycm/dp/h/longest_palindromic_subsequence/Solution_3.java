package com.oycm.dp.h.longest_palindromic_subsequence;

public class Solution_3 {

    /**
     * 3472. <a href="https://leetcode.cn/problems/longest-palindromic-subsequence-after-at-most-k-operations/description/">至多 K 次操作后的最长回文子序列</a> 1884
     *
     * @param s
     * @param k
     * @return
     */
    public int longestPalindromicSubsequence(String s, int k) {
        /*
        给你一个字符串 s 和一个整数 k。
        在一次操作中，你可以将任意位置的字符替换为字母表中相邻的字符（字母表是循环的，因此 'z' 的下一个字母是 'a'）。
        例如，将 'a' 替换为下一个字母结果是 'b'，将 'a' 替换为上一个字母结果是 'z'；同样，将 'z' 替换为下一个字母结果是 'a'，替换为上一个字母结果是 'y'。
        返回在进行 最多 k 次操作后，s 的 最长回文子序列 的长度。
        子序列 是一个 非空 字符串，可以通过删除原字符串中的某些字符（或不删除任何字符）并保持剩余字符的相对顺序得到。
        回文 是正着读和反着读都相同的字符串。
         */
        /*
        1 <= s.length <= 200
        1 <= k <= 200
        s 仅由小写英文字母组成。
         */
        /*
        s[i] - s[j]
         */
        int n = s.length();
        return dfs(0, n - 1, k, s.toCharArray(), new int[n][n][k + 1]);
    }

    public int dfs(int i, int j, int k, char[] cs, int[][][] memo) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (memo[i][j][k] > 0) return memo[i][j][k];
        int res = 0;
        if (cs[i] == cs[j]) {
            res = dfs(i + 1, j - 1, k, cs, memo) + 2;
        } else {
            int cnt;
            if (cs[i] < cs[j]) {
                cnt = Math.min(cs[j] - cs[i], cs[i] + 26 - cs[j]);
            } else {
                cnt = Math.min(cs[i] - cs[j], cs[j] + 26 - cs[i]);
            }

            res = Math.max(dfs(i, j - 1, k, cs, memo), dfs(i + 1, j, k, cs, memo));
            if (cnt <= k) {
                res = Math.max(dfs(i + 1, j - 1, k - cnt, cs, memo) + 2, res);
            }

        }
        return memo[i][j][k] = res;
    }

}
