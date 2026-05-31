package com.oycm.dualweek2024.No140;

public class Solution_4 {

    /**
     * 3303. <a href="https://leetcode.cn/problems/find-the-occurrence-of-first-almost-equal-substring/description/">第一个几乎相等子字符串的下标</a> 2509
     *
     * @param s
     * @param pattern
     * @return
     */
    public int minStartingIndex(String s, String pattern) {
        /*
        给你两个字符串 s 和 pattern。
        如果一个字符串 x 修改 至多 一个字符会变成 y ，那么我们称它与 y 几乎相等。
        请你返回 s 中下标 最小 的 子字符串 ，它与 pattern 几乎相等 。如果不存在，返回 -1 。
        子字符串 是字符串中的一个 非空、连续的字符序列。
         */
        /*
        前后缀分解：
            前缀分解：子串 s[i : ] 与 pattern 前缀匹配长度，记为 pre[i]；
            后缀分解：子串 s[ : j] 与 pattern 后缀匹配长度，记为 suf[j]；
        m = pattern.length，当 j - i + 1 = m；如果 pre[i] + suf[j] >= m-1，则修改 1 个字符使得子串 s[i : j] == pattern
        前缀分解：构造 pattern + s 字符串，计算它的 z 数组，则 pre[i] = preZ[i+m]；
        后缀分解：构造 rev(pattern) + rev(s)（rev 表示反转字符串） 字符串，计算它的 sufZ 数组，
            sufZ[i+m] 表示 n-1-i 结尾字符串 s 后缀匹配长度。suf[j] = sufZ[n-1-j+m]
        枚举 i = 0, 1, 2, ..., n-m，当前需要匹配的子串为 [i, i+m-1]
            前缀匹配长度为：preZ[i+m]
            后缀匹配长度为 suf[i+m-1] = sufZ[n-1 -(i+m-1) + m] = sufZ[n-i]
            preZ[i+m] + sufZ[n-i] >= m-1
        枚举 j = m, m+1, ..., n，设 j = i+m，则 i = j-m
            preZ[j] + sufZ[n-(j-m)] = preZ[j] + sufZ[n+m-j]
         */
        int[] preZ = calcZ((pattern + s).toCharArray());
        int[] sufZ = calcZ((rev(pattern) + rev(s)).toCharArray());
        int n = s.length();
        int m = pattern.length();
        for (int i = 0; i <= n - m; i++) {
            if (preZ[i + m] + sufZ[n - i] >= m - 1) {
                return i;
            }
        }

        return -1;
    }

    public int[] calcZ(char[] cs) {
        int n = cs.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && cs[i + z[i]] == cs[z[i]]) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    public String rev(String s) {
        return new StringBuilder(s).reverse().toString();
    }

}
