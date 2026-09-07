package com.oycm.month2026.september;

import java.util.Arrays;

public class Solution_7 {

    /**
     * 940. <a href="https://leetcode.cn/problems/distinct-subsequences-ii/description/">不同的子序列 II</a> 1985
     *
     * @param s
     * @return
     */
    public int distinctSubseqII(String s) {
        /*
        给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
        字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
        例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
         */
        int mod = 1000000007;
        int n = s.length();
        long[][] f = new long[n + 1][26];
        for (int i = 0; i < n; i++) {
            f[i + 1] = f[i].clone();
            f[i + 1][s.charAt(i) - 'a'] = (1 + Arrays.stream(f[i]).sum()) % mod;
        }
        return (int) (Arrays.stream(f[n]).sum() % mod);
    }

}
