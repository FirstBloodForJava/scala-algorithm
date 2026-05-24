package com.oycm.hot100.dp_mult;

public class Solution_93 {

    /**
     * 5. <a href="https://leetcode.cn/problems/longest-palindromic-substring/">最长回文子串</a>
     *
     * @param s 1 <= s.length <= 1000
     * @return
     */
    public String longestPalindrome(String s) {
        /*
        给你一个字符串 s，找到 s 中最长的 回文 子串。
         */
        /*
        枚举 s i 从 [0, n-1], j 从 [n-1] j > i, 判断 [i, j] 是否为回文字段
         */
        int n = s.length();
        int mx = 0;
        String ans = null;
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                int l = i;
                int r = j;
                boolean isPalindrome = true;
                while (l <= r && isPalindrome) {
                    if (cs[l] != cs[r]) {
                        isPalindrome = false;
                    }
                    l++;
                    r--;
                }
                if (isPalindrome && j - i + 1 > mx) {
                    mx = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
            }
        }

        return ans;
    }

}
