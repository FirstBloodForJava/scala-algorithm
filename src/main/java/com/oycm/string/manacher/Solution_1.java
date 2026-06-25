package com.oycm.string.manacher;

import java.util.Arrays;

public class Solution_1 {

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
        ti = si * 2 + 2
        求 halfLen 数组程中，记录 halfLen 最大下标 maxI。
        设 hl = halfLen[maxI]
        再将字符串 t (maxI - hl, maxI + hl) 区间转化成字符串 s 区间。
            maxI - hl 恰好落在 t 字符串 # 字符左边，对应字符串 s 下标为 (maxI-hl)/2 - 1；
            maxI + hl 恰好落在 t 字符串 # 字符右边，对应字符串 s 下标为 (maxI+hl)/2 - 1；
        原字符串 s 最长回文子串开区间 ((maxI-hl)/2 - 1, (maxI+hl)/2 - 1)
        转换成左闭右开区间即 [(maxI-hl)/2, (maxI+hl)/2 - 1)
         */
        int n = s.length();
        char[] ts = new char[2 * n + 3];
        Arrays.fill(ts, '#');
        ts[0] = '^';
        ts[2 * n + 2] = '$';
        for (int i = 0; i < n; i++) {
            ts[2 * i + 2] = s.charAt(i);
        }
        // ts 末尾 #$ 两个字符，不在计算范围内
        int[] halfLen = new int[ts.length - 2];
        // boxM 表示 以 boxM 为回文中心，对应最长回文串的右端点 +1 为 boxR
        int boxM = 0, boxR = 0;
        int maxI = 0;
        for (int i = 2; i < halfLen.length; i++) {
            int hl = 1;
            if (i < boxR) {
                hl = Math.min(halfLen[2 * boxM - i], boxR - i);
            }
            while (ts[i - hl] == ts[i + hl]) {
                hl++;
                boxM = i;
                boxR = i + hl;
            }
            halfLen[i] = hl;
            if (hl > halfLen[maxI]) {
                maxI = i;
            }
        }
        int hl = halfLen[maxI];

        return s.substring((maxI - hl) / 2, (maxI + hl) / 2 - 1);
    }


}
