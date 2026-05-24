package com.oycm.hot100.dp_mult;

import java.util.Arrays;

public class Solution_95 {

    /**
     * 72. <a href="https://leetcode.cn/problems/edit-distance/description/">编辑距离</a>
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        /*
        给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。
        你可以对一个单词进行如下三种操作：
            插入一个字符
            删除一个字符
            替换一个字符
         */
        /*
        从后往前考虑操作，word1 = s 的下标记为 i，word2 = t 的下标记为 j
        如果 s[i] = t[j] 考虑 [0, i-1] 和 [0, j-1] 剩余字符串，操作多少次相等
        如果 s[i] != t[j] 对 i 有以下选择
            删除 i，考虑 [0, i-1] 和 [0, j] 剩余字符串，操作多少次相等
            修改 i，考虑 [0, i-1] 和 [0, j] 剩余字符串，操作多少次相等
            i 后插入，考虑 [0, i] 和 [0, j-1] 剩余字符串，操作多少次相等
        这个是一个原问题和子问题的解决方式，可以使用递归来解决
        dfs(i, j)
            s[i] = t[j] = dfs(i-1, j-1)
            s[i] != t[j] = min((dfs(i-1, j), (dfs(i-1, j-1), dfs(i, j-1) + 1
        递归边界：
            如果 i < 0, 返回 j+1，t 还剩 [0, j] 个字符需要插入；
            如果 j < 0，返回 i+1，t 全部匹配，s 还剩 [0, i] 个字符需要删除；
         */
        s = word1.toCharArray();
        t = word2.toCharArray();
        int m = word1.length();
        int n = word2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(m - 1, n - 1);
    }

    char[] s, t;
    int[][] memo;

    public int dfs(int i, int j) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;
        if (memo[i][j] != -1) return memo[i][j];
        if (s[i] == t[j]) return memo[i][j] = dfs(i - 1, j - 1);
        return memo[i][j] = Math.min(dfs(i - 1, j), Math.min(dfs(i - 1, j - 1), dfs(i, j - 1))) + 1;
    }
}
