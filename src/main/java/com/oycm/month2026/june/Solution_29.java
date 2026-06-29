package com.oycm.month2026.june;

public class Solution_29 {

    /**
     * 1967. <a href="https://leetcode.cn/problems/number-of-strings-that-appear-as-substrings-in-word/description/">作为子字符串出现在单词中的字符串数目</a> 1232
     *
     * @param patterns
     * @param word
     * @return
     */
    public int numOfStrings(String[] patterns, String word) {
        /*
        给你一个字符串数组 patterns 和一个字符串 word ，统计 patterns 中有多少个字符串是 word 的子字符串。返回字符串数目。
        子字符串 是字符串中的一个连续字符序列。
         */
        int ans = 0;
        for (String s : patterns) {
            ans += word.contains(s) ? 1 : 0;
        }

        return ans;
    }

}
