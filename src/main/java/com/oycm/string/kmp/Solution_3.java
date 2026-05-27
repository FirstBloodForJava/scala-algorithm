package com.oycm.string.kmp;

public class Solution_3 {

    /**
     * 1392. <a href="https://leetcode.cn/problems/longest-happy-prefix/description/">最长快乐前缀</a> 1876
     *
     * @param s 1 <= s.length <= 1e5
     * @return
     */
    public String longestPrefix(String s) {
        /*
        快乐前缀：是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
         */
        /*
        next 数组的 next[n-1] 长度
         */
        int n = s.length();
        int[] next = new int[n];
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            while (cnt > 0 && s.charAt(i) != s.charAt(cnt)) {
                cnt = next[cnt - 1];
            }
            if (s.charAt(i) == s.charAt(cnt)) {
                cnt++;
            }
            next[i] = cnt;
        }

        return next[n - 1] != 0 ? s.substring(0, next[n - 1]) : "";
    }

}
