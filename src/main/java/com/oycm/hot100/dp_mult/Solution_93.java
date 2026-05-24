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
        暴力：枚举 s i 从 [0, n-1], j 从 [n-1] j > i, 判断 [i, j] 是否为回文字段
         */
        /*
        从中间向两端枚举回文串：
            abcab a 为中心的子串，判断左右两边是否相等，相等，则继续向外扩展；不相等，则开始下一个中心点枚举
        有两种情况，回文串长度是奇数或偶数
            奇数枚举 l = r = i，l--, r++；循环结束时 [l+1, r-1] 这段就是回文串，长度为 r-l-1
            偶数枚举 l = i, r = r + 1; 要先判断 是否越界，以及 cs[l] 是否等于 cs[r]
        合并一个循环枚举：
            枚举 0, 1, 2, 3, ... 2n - 2
        当 i 是偶数时，枚举奇数长度中心；l = i/2 r= (i+1)/2 l 和 r 恰好是相等的
        当 i 时奇数时，枚举偶数长度中心；l = i/2 r= (i+1)/2 r 恰好比 l 多 1
         */
        int n = s.length();
        int mx = 0;
        int left = 0, right = 0;
        char[] cs = s.toCharArray();

        // 合并枚举
        for (int i = 0; i < 2 * n - 1; i++) {
            int l = i / 2;
            int r = (i + 1) >> 1;
            while (l >= 0 && r < n && cs[l] == cs[r]) {
                l--;
                r++;
            }
            if (r - l - 1 > mx) {
                mx = r - l - 1;
                left = l + 1;
                right = r;
            }

        }

        return s.substring(left, right);
    }

}
