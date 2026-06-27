package com.oycm.hot100.substring;

import java.util.Arrays;

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

    public int countSubstrings_manacher(String s) {
        /*
        以 i 为中心的回文子串的回文半径，就是以 i 为中心的所有回文串
         */
        int n = s.length();
        char[] cs = new char[2 * n + 3];
        Arrays.fill(cs, '#');
        cs[0] = '^';
        for (int i = 0; i < n; i++) {
            cs[2 * i + 2] = s.charAt(i);
        }
        cs[cs.length - 1] = '$';
        int ans = 0;
        int[] halfLen = new int[2 * n + 1];
        int boxI = 0, boxR = 0;
        for (int i = 2; i < halfLen.length; i++) {
            int hl = 1;
            if (i < boxR) {
                hl = Math.min(halfLen[2 * boxI - i], boxR - i);
            }
            while (cs[i - hl] == cs[i + hl]) {
                hl++;
                boxI = i;
                boxR = i + hl;
            }
            halfLen[i] = hl;
            /*
            回文半径，奇数长度上取整
            hl = 直径 + 1，所以直接除以 2 即可
             */
            ans += hl / 2;
        }

        return ans;
    }

}
