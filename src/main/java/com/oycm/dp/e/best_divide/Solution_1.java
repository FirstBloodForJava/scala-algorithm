package com.oycm.dp.e.best_divide;

import java.util.Arrays;

public class Solution_1 {

    /**
     * 132. <a href="https://leetcode.cn/problems/palindrome-partitioning-ii/">分割回文串 II</a>
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        /*
        给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
        返回符合要求的 最少分割次数 。
         */
        /*
        思路：
        dfs(r) 表示将 s[0, r] 字符串，分割成一些子串，使每个子串都是回文串的最小分割次数
        枚举 r 的左端点 l，如果 s[l, r] 是回文串，在 l 和 l-1 之间分割，问题转换成 求 dfs(l-1) 的值
         */
        int n = s.length();
        int[] f = new int[n];
        boolean[][] isPalindrome = new boolean[n][n];
        for (boolean[] row : isPalindrome) {
            Arrays.fill(row, true);
        }
        char[] cs = s.toCharArray();
        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                isPalindrome[l][r] = cs[l] == cs[r] && isPalindrome[l + 1][r - 1];
            }
        }
        for (int r = 0; r < n; r++) {
            if (isPalindrome[0][r]) {
                continue;
            }
            int res = Integer.MAX_VALUE;
            for (int l = 1; l <= r; l++) {
                if (isPalindrome[l][r]) {
                    res = Math.min(res, f[l - 1] + 1);
                }
            }
            f[r] = res;
        }

        return f[n - 1];
    }

    public int dfs(int r, char[] cs, int[] memo, int[][] palMemo) {
        if (isPalindrome(0, r, cs, palMemo)) {
            return 0;
        }
        if (memo[r] != -1) {
            return memo[r];
        }
        int res = Integer.MAX_VALUE;
        for (int l = 1; l <= r; l++) {
            if (isPalindrome(l, r, cs, palMemo)) {
                res = Math.min(res, dfs(l - 1, cs, memo, palMemo) + 1);
            }
        }
        return memo[r] = res;
    }

    public boolean isPalindrome(int l, int r, char[] cs, int[][] palMemo) {
        if (l >= r) {
            return true;
        }
        if (palMemo[l][r] != -1) {
            return palMemo[l][r] == 1;
        }
        boolean res = cs[l] == cs[r] && isPalindrome(l + 1, r - 1, cs, palMemo);
        palMemo[l][r] = res ? 1 : 0;
        return res;
    }

}
