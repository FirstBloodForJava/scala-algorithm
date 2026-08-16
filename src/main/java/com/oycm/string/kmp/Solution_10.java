package com.oycm.string.kmp;

public class Solution_10 {

    public String shortestPalindrome(String s) {
        /*
        给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
         */
        /*
        添加的字符串要短，那么就是要找到最长前缀回文子串长度。
        s 的前缀最长回文字符串记为 s1，s 反转后的字符串记为 s'，由于 s1 是回文串，那么 s' 的长为 s1 的字符串一定等于 s1
        把 s 作为 模式串，s' 作为查找串，那么查找到 s' 末尾对应的长度，就是前缀最长回文长度。
         */
        int n = s.length();
        int[] next = new int[n];
        int cnt = 0;
        char[] cs = s.toCharArray();
        for (int i = 1; i < n; i++) {
            while (cnt > 0 && cs[i] != cs[cnt]) {
                cnt = next[cnt - 1];
            }
            if (cs[i] == cs[cnt]) {
                cnt++;
            }
            next[i] = cnt;
        }
        int j = 0;
        // 反转字符串 s'
        for (int i = n - 1; i >= 0; i--) {
            while (j > 0 && cs[i] != cs[j]) {
                j = next[j - 1];
            }
            if (cs[i] == cs[j]) {
                j++;
            }
        }
        if (j == n) return s;
        // 后缀不匹配字符串，反转拼接 s
        return new StringBuilder(s.substring(j)).reverse() + s;
    }

}
