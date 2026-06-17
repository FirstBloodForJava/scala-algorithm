package com.oycm.month2026.june;

public class Solution_17 {

    /**
     * 3614. <a href="https://leetcode.cn/problems/process-string-with-special-operations-ii/description/">用特殊操作处理字符串 II</a> 2011
     *
     * @param s
     * @param k
     * @return
     */
    public char processStr(String s, long k) {
        /*
        给你一个字符串 s，由小写英文字母和特殊字符：'*'、'#' 和 '%' 组成。
        同时给你一个整数 k。
        请根据以下规则从左到右处理 s 中每个字符，构造一个新的字符串 result：
            如果字符是 小写 英文字母，则将其添加到 result 中。
            字符 '*' 会 删除 result 中的最后一个字符（如果存在）。
            字符 '#' 会 复制 当前的 result 并追加到其自身后面。
            字符 '%' 会 反转 当前的 result。
        返回最终字符串 result 中第 k 个字符（下标从 0 开始）。如果 k 超出 result 的下标索引范围，则返回 '.'。
         */
        char[] cs = s.toCharArray();
        int n = cs.length;
        long[] size = new long[n];
        long sz = 0;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '*') {
                sz = Math.max(sz - 1, 0);
            } else if (c == '#') {
                sz *= 2;
            } else if (c != '%') {
                sz++;
            }
            size[i] = sz;
        }

        if (k >= size[n - 1]) {
            return '.';
        }

        // 迭代
        for (int i = n - 1; ; i--) {
            char c = cs[i];
            sz = size[i];
            if (c == '#') {
                if (k >= sz / 2) { // k 在复制后的右半边
                    k -= sz / 2;
                }
            } else if (c == '%') {
                k = sz - 1 - k; // 反转前的下标为 sz-1-k 的字母就是答案
            } else if (c != '*' && k == sz - 1) { // 找到答案
                return c;
            }
        }
    }

}
