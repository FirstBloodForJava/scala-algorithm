package com.oycm.string.hash;

import java.util.Random;

public class Solution_1 {

    /**
     * 28. 找出字符串中第一个匹配项的下标
     * <br>
     * 28. <a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/">找出字符串中第一个匹配项的下标</a>
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        /*
        给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
        如果 needle 不是 haystack 的一部分，则返回  -1 。
         */
        char[] p = needle.toCharArray();
        int m = p.length;
        final int mod1 = 1_070_777_777;
        final int mod2 = 1_000_000_007;
        final int base1 = (int) 8e8 + new Random().nextInt((int) 1e8);
        final int base2 = (int) 8e8 + new Random().nextInt((int) 1e8);
        int powM1 = 1;
        int powM2 = 1;
        long p1 = 0;
        long p2 = 0;
        for (int i = 0; i < m; i++) {
            powM1 = (int) ((long) powM1 * base1 % mod1);
            powM2 = (int) ((long) powM2 * base2 % mod2);
            p1 = (p1 * base1 + p[i]) % mod1;
            p2 = (p2 * base2 + p[i]) % mod2;
        }
        long pHash = p1 << 32 | p2;
        char[] t = haystack.toCharArray();
        int n = t.length;
        int[] preHash1 = new int[n + 1];
        int[] preHash2 = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preHash1[i + 1] = (int) (((long) preHash1[i] * base1 + t[i]) % mod1);
            preHash2[i + 1] = (int) (((long) preHash2[i] * base2 + t[i]) % mod2);
        }
        for (int i = 0; i < n - m + 1; i++) {
            int r = i + m;
            long subHash1 = ((preHash1[r] - (long) preHash1[i] * powM1) % mod1 + mod1) % mod1;
            long subHash2 = ((preHash2[r] - (long) preHash2[i] * powM2) % mod2 + mod2) % mod2;
            long hash = subHash1 << 32 | subHash2;
            if (pHash == hash) return i;
        }

        return -1;
    }

}
