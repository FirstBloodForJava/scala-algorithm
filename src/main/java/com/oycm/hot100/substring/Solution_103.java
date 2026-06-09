package com.oycm.hot100.substring;

public class Solution_103 {

    /**
     * 647. <a href="https://leetcode.cn/problems/palindromic-substrings/description/">回文子串</a>
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        /*
        给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
        回文字符串 是正着读和倒过来读一样的字符串。
        子字符串 是字符串中的由连续字符组成的一个序列。
         */
        int n = s.length();
        int ans = 0;
        char[] cs = s.toCharArray();
        // 合并枚举
        for (int i = 0; i < 2 * n - 1; i++) {
            int l = i / 2;
            int r = (i + 1) >> 1;
            while (l >= 0 && r < n && cs[l] == cs[r]) {
                ans++;
                l--;
                r++;
            }

        }

        return ans;
    }

}
