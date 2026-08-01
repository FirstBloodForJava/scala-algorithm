package com.oycm.dualweek.lc2026.No188;

public class Solution_1 {

    /**
     * 统计有效前缀数目
     * @param s
     * @return
     */
    public int countValidPrefixes(String s) {
        /*
        给你一个 二进制 字符串 s。
        如果 s 的某个 前缀 的字符可以重新排列成一个 交替 字符串，那么该前缀被认为是 有效 的。
        返回 s 中有效前缀的数量。
        二进制 字符串是仅由 '0' 和 '1' 组成的字符串。
        字符串的 前缀 是指从字符串的开头开始并延伸到其内任意点的 子字符串。
        子字符串 是字符串中连续且 非空 的字符序列。
        如果一个字符串中没有两个相邻字符相等，那么它被认为是 交替 的。
         */
        int ans = 0;
        int c0 = 0, c1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                c0++;
            } else {
                c1++;
            }
            if (Math.abs(c1 - c0) <= 1) {
                ans++;
            }
        }

        return ans;
    }
}
