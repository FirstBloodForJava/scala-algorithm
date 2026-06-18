package com.oycm.dp.h.longest_palindromic_subsequence;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 1312. <a href="https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/">让字符串成为回文串的最少插入次数</a> 1787
     *
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        /*
        给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
        请你返回让 s 成为回文串的 最少操作次数。
        「回文串」是正读和反读都相同的字符串。
         */
        /*
        解决思路一：中心扩展法
        枚举 s[i] 作为结果回文串中心，不匹配前后缀字符串计算需要插入多少变成回文串，求最小值
        解决思路二：递归
        分类讨论：
            如果 s[0] = s[n-1], 计算 s[1:n-1] 的最少插入次数;
            如果 s[0] != s[n-1],
                s[n-1] 后面插入 s[0]，计算 s[1:n-1] 的最少插入次数;
                s[0] 前面插入 s[n-1]，计算 s[0:n-2] 的最少插入次数;
        子问题和原问题相似，可以使用递归解决。
        递归边界：i >= j 返回 0
        */
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, n-1, cs, memo);
    }

    public int dfs(int i, int j, char[] cs, int[][] memo) {
        if (i >= j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int res = 0;
        if (cs[i] == cs[j]) {
            res = dfs(i + 1, j - 1, cs, memo);
        } else {
            res = Math.min(dfs(i + 1, j, cs, memo), dfs(i, j - 1, cs, memo)) + 1;
        }
        return memo[i][j] = res;
    }




}
