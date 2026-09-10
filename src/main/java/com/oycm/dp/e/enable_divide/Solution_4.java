package com.oycm.dp.e.enable_divide;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 1278. <a href="https://leetcode.cn/problems/palindrome-partitioning-iii/description/">分割回文串 III</a> 1979
     *
     * @param s
     * @param k
     * @return
     */
    public int palindromePartition(String s, int k) {
        /*
        给你一个由小写字母组成的字符串 s，和一个整数 k。
        请你按下面的要求分割字符串：
            首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
            接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
        请返回以这种方式分割字符串所需修改的最少字符数。
         */
        /*
        可以先分割成 k 份子串，计算子串需要修改的次数
        dfs(i, k) 表示将长为 i 的字符串分割为 k 个不相交回文子串的最少修改次数
        dfs(i, k) res = mx
            for(int j = i - 1; j > k - 2; j--) {
                // s[j, i) 成为回文串的修改次数
                res = min(res, dfs(j, k-1) + cnt)
            }
        递归边界: k == 0
            i = 0 => 0
            i != 0 => mx
         */
        int n = s.length();
        int[][] memo = new int[n + 1][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(n, k, s.toCharArray(), memo);

    }

    public int dfs(int i, int k, char[] cs, int[][] memo) {
        if (k == 0) {
            return i == 0 ? 0 : Integer.MAX_VALUE / 2;
        }
        if (memo[i][k] != -1) {
            return memo[i][k];
        }
        int res = Integer.MAX_VALUE;
        for (int j = i - 1; j > k - 2; j--) {
            res = Math.min(res, dfs(j, k - 1, cs, memo) + modCnt(cs, j, i - 1));
        }
        return memo[i][k] = res;
    }

    public int modCnt(char[] cs, int left, int right) {
        int cnt = 0;
        while (left < right) {
            if (cs[left++] != cs[right--]) {
                cnt++;
            }
        }
        return cnt;
    }

    public int palindromePartition_dp(String s, int k) {
        int n = s.length();
        int[][] f = new int[n + 1][k + 1];
        for (int[] row : f) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        char[] cs = s.toCharArray();
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                // [0, i) 枚举分割位置
                for (int m = 0; m < i; m++) {
                    int left = m, right = i - 1;
                    int cnt = 0;
                    while (left < right) {
                        if (cs[left++] != cs[right--]) {
                            cnt++;
                        }
                    }
                    f[i][j] = Math.min(f[i][j], f[m][j - 1] + cnt);
                }
            }
        }

        return f[n][k];
    }


}
