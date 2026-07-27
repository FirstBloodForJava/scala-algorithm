package com.oycm.month2026.july;

public class Solution_28 {

    /**
     * 3517. <a href="https://leetcode.cn/problems/smallest-palindromic-rearrangement-i/description/">最小回文排列 I</a>
     *
     * @param s
     * @return
     */
    public String smallestPalindrome(String s) {
        /*
        给你一个 回文 字符串 s。
        返回 s 的按字典序排列的 最小 回文排列。
        如果一个字符串从前往后和从后往前读都相同，那么这个字符串是一个 回文 字符串。
        排列 是字符串中所有字符的重排。=
        如果字符串 a 按字典序小于字符串 b，则表示在第一个不同的位置，a 中的字符比 b 中的对应字符在字母表中更靠前。
        如果在前 min(a.length, b.length) 个字符中没有区别，则较短的字符串按字典序更小。
         */
        /*
        奇数回文串，肯定有一个奇数数量字符，这个肯定要放中间位置。
        偶数回文串，字符数量都是偶数，越小的字符要靠前放。
        先统计字符数量，从小到大遍历字符，数量如果大于 1，则前后各放一个。
         */
        int[] cnt = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            cnt[c - 'a']++;
        }
        int l = 0, r = cs.length - 1;
        for (int i = 0; i < cnt.length; i++) {
            while (cnt[i] > 1) {
                cs[l++] = (char) ('a' + i);
                cs[r--] = (char) ('a' + i);
                cnt[i] -= 2;
            }
        }
        return new String(cs);
    }

}
