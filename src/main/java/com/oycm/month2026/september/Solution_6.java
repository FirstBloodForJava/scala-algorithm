package com.oycm.month2026.september;

public class Solution_6 {

    /**
     * 115. <a href="https://leetcode.cn/problems/distinct-subsequences/description/">不同的子序列</a>
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        /*
        给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
        测试用例保证结果在 32 位有符号整数范围内。
         */
        /*
        dfs(i, j) s[0:i] 中 t[0:j 出现的次数
        dfs(i, j) =
            dfs(i-1, j)
            dfs(i-1, j-1), (s[i] == t[j])
         */
        int m = s.length();
        int n = t.length();
        /*
        f[i+1][j+1] =
            f[i][j+1]
            f[i][j] + f[i][j+1], (s[i] == t[j])
        f[i][0] = 1
         */
        int[][] f = new int[m + 1][n + 1];
        f[0][0] = 1;
        char[] cs = s.toCharArray();
        char[] ts = t.toCharArray();

        for (int i = 0; i < m; i++) {
            f[i + 1][0] = 1;
            /*
            j < min(i+1, n) 当 i < m-1 时，后面的计算是无效的无法得到该结果
            因为 i >= j 才能找到对应的子序列，所以 m-i >= n-j => j >= n - m + i
             */
            for (int j = Math.max(n - m + i, 0); j < Math.min(i + 1, n); j++) {
                f[i + 1][j + 1] = f[i][j + 1];
                if (cs[i] == ts[j]) {
                    f[i + 1][j + 1] += f[i][j];
                }
            }
        }

        return f[m][n];
    }

    public int dfs(int i, int j, char[] s, char[] t, int[][] memo) {
        if (i < j) {
            // s 剩余字符长度不够
            return 0;
        }
        if (j < 0) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = dfs(i - 1, j, s, t, memo);
        if (s[i] == t[j]) {
            res += dfs(i - 1, j - 1, s, t, memo);
        }
        return memo[i][j] = res;
    }

}
