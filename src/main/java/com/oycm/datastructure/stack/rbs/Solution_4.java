package com.oycm.datastructure.stack.rbs;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_4 {

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
        从左到右遍历 s，用 sb 记录非括号字符，如果出现 '('，将 sb 入栈，清空 sb，再遇到 ')' 时，吧 sb 反转，栈顶字符串插入到 sb 顶部
        */
        Deque<String> st = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.push(sb.toString());
                sb.setLength(0);
            } else if (c == ')') {
                sb.reverse();
                sb.insert(0, st.pop());
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
