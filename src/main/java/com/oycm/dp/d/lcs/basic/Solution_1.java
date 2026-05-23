package com.oycm.dp.d.lcs.basic;

import java.util.Arrays;

public class Solution_1 {

    /**
     * 1143. <a href="https://leetcode.cn/problems/longest-common-subsequence/description/">最长公共子序列</a>
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        /*
        给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0。
        一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
        例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
         */
        /*
        子序列可以看作里面的字符 选/不选，考虑最后一对字母 x, y 有以下选择：
            选 x，选 y
            选 x，不选 y
            不选 x，选 y
            不选 x，不选 y
        回溯三问？
            当前操作：s[i], t[j] 选/不选；
            子问题：s 的前 i 个字母和 t 的前 j 个字母的 lcs 长度；
            下一个子问题：
                s 的前 i-1 个字母和 t 的前 j-1 个字母 lcs 长度；这里包含 选 x 选 y；不选 x 不选 y
                s 的前 i 个字母和 t 的前 j-1 个字母 lcs 长度；
                s 的前 i-1 个字母和 t 的前 j 个字母 lcs 长度；
            dfs(i, j) 分类讨论
                s[i] = t[j]：dfs(i, j) = max(dfs(i-1, j), dfs(i, j-1), dfs(i-1, j-1) + 1)；
                s[i] != t[j]：dfs(i, j) = max(dfs(i-1, j), dfs(i, j-1), dfs(i-1, j-1))；
            dfs(i, j) = max(dfs(i-1, j), dfs(i, j-1), dfs(i-1, j-1)) + (s[i] == t[i])
            当 s[i] = t[j] 时，还需要考虑 dfs(i-1, j), dfs(i, j-1) 吗？
                s = abcdc, t = abc
                设 x = dfs(i-1, j-1)，假设 dfs(i-1, j) > x + 1，分类讨论：
                    如果 s[i-1] = t[j]，则 dfs(i-2, j-1) > x，由于该字符串时 dfs(i-1, j-1) 子序列，长度应该小于等于 x，前后矛盾
                    如果 s[i-1] != t[j]，则 dfs(i-2, j-1), dfs(i-1, j-1), dfs(i-2, j) 大于 x+1，前两个矛盾情况同上
                    dfs(i-2, j) 贪心的想，去掉 t[j] 该子序列要么不变，要么减少 1，则 dfs(i-2, j-1) 要大于 x（不减少 > x+1, 减少 > x），前后矛盾
                    综上所述，dfs(i-1, j) <= x + 1
                    dfs(i, j-1) 同理。
                怎么说明 dfs(i-1, j-1) lcs 为 x，给 t 后面增加一个字母 t[j]，使得 lcs 增加至少 2
            当 s[i] != t[j] 时，还需考虑 dfs(i-1, j-1) 吗？
                不需要，因为 dfs(i-1, j) 和 dfs(i, j-1) 会递归到 dfs(i-1, j-1)，前者会大于等于 dfs(i-1, j-1)。
         */
        int n = text1.length();
        int m = text2.length();
        this.memo = new int[n][m];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        this.s = text1.toCharArray();
        this.t = text2.toCharArray();
        return dfs(n - 1, m - 1);
    }

    char[] s;
    char[] t;
    int[][] memo;

    public int dfs(int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        if (s[i] == t[j]) return memo[i][j] = dfs(i - 1, j - 1) + 1;
        return memo[i][j] = Math.max(dfs(i - 1, j), dfs(i, j - 1));
    }

}

class Solution_1143_1 {
    public int longestCommonSubsequence(String text1, String text2) {
        /*
        dfs 翻译成 递推
        s[i] = t[j]:  dfs(i, j) = dfs(i-1, j-1) + 1
        s[i] != t[j]: dfs(i, j) = max(dfs(i-1, j), dfs(i, j-1))
        s[i] = t[j]:  f[i+1][j+1] = f[i][j] + 1;
        s[i]!= t[j]:  f[i+1][j+1] = max(f[i][j+1], f[i+1][j])
         */
        int n = text1.length();
        int m = text2.length();
        int[][] f = new int[2][m + 1];
        char[] s = text1.toCharArray();
        char[] t = text2.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[(i + 1) % 2][j + 1] = s[i] == t[j] ? f[i % 2][j] + 1 :
                        Math.max(f[(i + 1) % 2][j], f[i % 2][j + 1]);
            }
        }
        return f[n % 2][m];
    }
}

class Solution_1143_2 {
    public int longestCommonSubsequence(String text1, String text2) {
        /*
        s[i] = t[j]:  f[i+1][j+1] = f[i][j] + 1;
        s[i]!= t[j]:  f[i+1][j+1] = max(f[i][j+1], f[i+1][j])
        f[i+1][j+1] 计算需要用到三个位置变量：
            上   f[i][j+1]，只有当前行会访问到
            左   f[i+1][j]，更新后，当前行可以使用
            左上 f[i][j]，需要使用变量记录上一行的
         */
        int n = text1.length();
        int m = text2.length();
        int[] f = new int[m + 1];
        char[] s = text1.toCharArray();
        char[] t = text2.toCharArray();
        for (int i = 0; i < n; i++) {
            int pre = f[0];
            for (int j = 0; j < m; j++) {
                // 记录当前行会覆盖上一行的内容
                int temp = f[j + 1];
                f[j + 1] = s[i] == t[j] ? pre + 1 :
                        Math.max(f[j], f[j + 1]);
                pre = temp;
            }
        }
        return f[m];
    }
}
