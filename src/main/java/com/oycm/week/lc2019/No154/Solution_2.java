package com.oycm.week.lc2019.No154;

public class Solution_2 {

    /**
     * 1190. <a href="https://leetcode.cn/problems/reverse-substrings-between-each-pair-of-parentheses/description/">反转每对括号间的子串</a> 1486
     *
     * @param s
     * @return
     */
    public String reverseParentheses(String s) {
        /*
        给出一个字符串 s（仅含有小写英文字母和括号）。
        请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
        注意，您的结果中 不应 包含任何括号。
         */
        /*
        1 <= s.length <= 2000
        s 中只有小写英文字母和括号
        题目测试用例确保所有括号都是成对出现的
         */
        /*
        (u(love)i) 递归解决
        查询第一个 () 子串，找到其去掉括号后的字符串
         */
        int start = s.indexOf("(");

        if (start == -1) return s;

        int end = s.lastIndexOf(")");

        String res = reverseParentheses(s.substring(start + 1, end));

        return s.substring(0, start) + new StringBuilder(res).reverse() + reverseParentheses(s.substring(end + 1));
    }

}
