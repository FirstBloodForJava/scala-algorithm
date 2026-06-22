package com.oycm.string.manacher;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 647. <a href="https://leetcode.cn/problems/palindromic-substrings/description/">回文子串</a>
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
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
        int boxM = 0, boxR = 0;
        int res = 0;
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
            // 以 i 为回文中心的最长回文串长 hl，回文半径就是以 i 为回文中心所以回文子串
            res += hl / 2;
        }

        return res;
    }

}
