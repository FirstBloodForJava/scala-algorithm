package com.oycm.string.manacher;

import java.util.Arrays;

public class Solution_3 {

    /**
     * 214. <a href="https://leetcode.cn/problems/shortest-palindrome/description/">最短回文串</a>
     *
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        /*
        给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。
        找到并返回可以用这种方式转换的最短回文串。
         */
        /*
        0 <= s.length <= 5 * 1e4
        s 仅由小写英文字母组成
         */
        /*
        找到以 s[0] 为左端点的最长回文串长度，s 长度减去这个长度就是需要添加的数量。
        先求出 halfLen 数组，遍历 s[0, i] i 从 n-1 到 0，判断子串是否为回文串
         */
        if (s.isEmpty()) return s;
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
        int maxLen = -1;
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
            // 可以在计算中判断 [0, i] 是否为回文串
            if (hl == i) maxLen = hl - 1;
        }
        /*
        中心点 mid = (2 + (2 * i) + 2) / 2， halfLen[mid] - 1 >= i + 1
         */

        return new StringBuilder(s.substring(maxLen + 1)).reverse() + s;
    }

}
