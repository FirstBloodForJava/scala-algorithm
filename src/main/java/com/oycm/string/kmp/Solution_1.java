package com.oycm.string.kmp;

public class Solution_1 {

    /**
     * 28. <a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/">找出字符串中第一个匹配项的下标</a>
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        /*
        给你两个字符串 haystack 和 needle ，在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
        如果 needle 不是 haystack 的一部分，则返回  -1 。
         */
        int n = haystack.length();
        int m = needle.length();
        int[] next = new int[m];
        int cnt = 0;
        for (int i = 1; i < m; i++) {
            while (cnt > 0 && needle.charAt(i) != needle.charAt(cnt)) {
                cnt = next[cnt - 1];
            }
            if (needle.charAt(cnt) == needle.charAt(i)) {
                cnt++;
            }
            next[i] = cnt;
        }
        for (int i = 0, j = 0; i < n; i++) {
            char b = haystack.charAt(i);
            // 不匹配，查询前面 t 有公共真前缀和真后缀的最大长度，再比较下标
            while (j > 0 && b != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (b == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                // j 先加 1，i 还没有加上
                return i - j + 1;
            }
        }

        return -1;

    }

}
