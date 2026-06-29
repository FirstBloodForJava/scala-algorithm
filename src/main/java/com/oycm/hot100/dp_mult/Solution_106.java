package com.oycm.hot100.dp_mult;

public class Solution_106 {

    /**
     * 10. <a href="https://leetcode.cn/problems/regular-expression-matching/description/">正则表达式匹配</a>
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        /*
        给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
            '.' 匹配任意单个字符
            '*' 匹配零个或多个前面的那一个元素
        返回一个布尔值，表示匹配是否覆盖整个输入字符串（而非部分）。
         */
        /*
        题解思路：
        从左往右思考。第一个字符 s[0] 怎么匹配？
        你肯定会去看 p[0] 的值。但注意，对于正则表达式来说，如果 p[1]=*，那么 p[0] 和 p[1] 就是一个整体，不能单独把 p[0] 拿出来去和 s[0] 匹配。
        如果 p[1] != *
            如果 p[0] = . 或者 p[0] = s[0]，那么匹配成功，下一步考虑 s 的后缀 [1,n−1] 能否与 p 的后缀 [1,m−1] 匹配。
            否则直接返回 false
        如果 p[1] = *，那么 p[0] 和 p[1] 是一个整体。
            情况一，匹配零个字符，下一步考虑 s 的 [0, n−1] 能否与 p 的 [2, m−1] 匹配。
            情况二，我们可以先匹配一个字符（要求  p[0] = . 或者 p[0] = s[0]），下一步考虑 s 的 [1, n−1] 能否与 p 的 [0, m−1] 匹配。
            至于 s[1] 是否要与 p[0] 和 p[1] 匹配，那是下一步要做的事情（这类似完全背包）。
         */
        return dfs(0, 0, s.toCharArray(), p.toCharArray(), new boolean[s.length() + 1][p.length() + 1]);
    }

    public boolean dfs(int i, int j, char[] s, char[] p, boolean[][] memo) {
        int n = s.length;
        int m = s.length;
        if (j == m) return i == n;
        if (memo[i][j]) return false;
        memo[i][j] = true;
        boolean isMatch = i < n && (s[i] == p[j] || p[j] == '.');
        if (j + 1 < m && s[j + 1] == '*') {
            // 跳过不匹配
            return dfs(i, j + 2, s, p, memo) || isMatch && dfs(i + 1, j, s, p, memo);// 匹配一个，有下一个字符判断是否需要重复匹配
        }
        return isMatch && dfs(i + 1, j + 1, s, p, memo);
    }

    public boolean isMatch_dp(String S, String P) {
        char[] s = S.toCharArray();
        char[] p = P.toCharArray();
        int n = s.length;
        int m = p.length;
        boolean[][] f = new boolean[n + 1][m + 1];
        f[n][m] = true;
        for (int i = n; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (p[j] == '*') continue;
                boolean isMatch = i < n && (p[j] == '.' || s[i] == p[j]);
                if (j + 1 < m && p[j + 1] == '*') {
                    f[i][j] = f[i][j + 2] || isMatch && f[i + 1][j];
                } else {
                    f[i][j] = isMatch && f[i + 1][j + 1];
                }
            }
        }

        return f[0][0];
    }

    public boolean isMatch_dp_optimize(String S, String P) {
        char[] s = S.toCharArray();
        char[] p = P.toCharArray();
        int n = s.length;
        int m = p.length;
        boolean[] f = new boolean[m + 1];
        f[m] = true;
        for (int i = n; i >= 0; i--) {
            boolean pre = f[m];
            f[m] = i == n;
            for (int j = m - 1; j >= 0; j--) {
                if (p[j] == '*') continue;
                boolean isMatch = i < n && (p[j] == '.' || s[i] == p[j]);
                boolean temp = f[j];
                if (j + 1 < m && p[j + 1] == '*') {
                    f[j] = f[j + 2] || isMatch && f[j];
                } else {
                    f[j] = isMatch && pre;
                }
                pre = temp;
            }
        }

        return f[0];
    }

}
