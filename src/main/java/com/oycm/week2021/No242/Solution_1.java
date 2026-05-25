package com.oycm.week2021.No242;

public class Solution_1 {

    /**
     * 1869. <a href="https://leetcode.cn/problems/longer-contiguous-segments-of-ones-than-zeros/description/">哪种连续子字符串更长</a> 1205
     *
     * @param s [1, 100]
     * @return
     */
    public boolean checkZeroOnes(String s) {
        /*
        给你一个二进制字符串 s。
        如果字符串中由 1 组成的 最长连续子字符串 严格长于 由 0 组成的 最长连续子字符串，返回 true ；否则，返回 false。
         */
        /*
        分组循环：
            外层循环符合遍历组之前准备工作，遍历组之后的更新
            内存循环负责遍历组，找到最远结束点
         */
        int[] f = {0, 0};
        int i = 0;
        int n = s.length();
        while (i < n) {
            int start = i;
            i++;
            char c = s.charAt(start);
            while (i < n && c == s.charAt(i)) {
                i++;
            }
            f[c - '0'] = Math.max(f[c - '0'], i - start);
        }

        return f[1] > f[0];
    }

}
