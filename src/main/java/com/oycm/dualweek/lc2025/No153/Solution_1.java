package com.oycm.dualweek.lc2025.No153;

public class Solution_1 {

    /**
     * 3498. <a href="https://leetcode.cn/problems/reverse-degree-of-a-string/description/">字符串的反转度</a> 1201
     *
     * @param s
     * @return
     */
    public int reverseDegree(String s) {
        /*
        给你一个字符串 s，计算其 反转度。
        反转度的计算方法如下：
            对于每个字符，将其在 反转 字母表中的位置（'a' = 26, 'b' = 25, ..., 'z' = 1）与其在字符串中的位置（下标从1 开始）相乘。
            将这些乘积加起来，得到字符串中所有字符的和。
        返回 反转度。
         */
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ans += (i + 1) * ('{' - c);
        }
        return ans;
    }

}
