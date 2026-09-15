package com.oycm.month2026.september;

public class Solution_15 {

    /**
     * 2472. <a href="https://leetcode.cn/problems/maximum-number-of-non-overlapping-palindrome-substrings/description/">不重叠回文子字符串的最大数目</a> 2013
     *
     * @param s
     * @param k
     * @return
     */
    public int maxPalindromes(String s, int k) {
        /*
        给你一个字符串 s 和一个 正 整数 k 。
        从字符串 s 中选出一组满足下述条件且 不重叠 的子字符串：
            每个子字符串的长度 至少 为 k 。
            每个子字符串是一个 回文串 。
        返回最优方案中能选择的子字符串的 最大 数目。
        子字符串 是字符串中一个连续的字符序列。
         */
        /*
        不重叠：区间没有交集
        划分型 dp 最优划分
        只需要考虑长度为 k 和 k+1 的字符串是否为回文，因为 k+2, k+4 如果是回文串，会让前缀更短，后缀更长
         */
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] f = new int[n + 1];
        for (int i = k; i <= n; i++) {
            f[i] = f[i - 1];
            if (isPalindromes(cs, i - k, i - 1)) {
                f[i] = Math.max(f[i], f[i - k] + 1);
            }
            if (i > k && isPalindromes(cs, i - k - 1, i - 1)) {
                f[i] = Math.max(f[i], f[i - k - 1] + 1);
            }
        }
        return f[n];
    }

    public boolean isPalindromes(char[] cs, int l, int r) {
        while (l < r) {
            if (cs[l] != cs[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

}
