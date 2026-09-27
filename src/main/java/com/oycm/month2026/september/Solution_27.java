package com.oycm.month2026.september;

public class Solution_27 {

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
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] links = new int[n];
        int[] st = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            char ch = cs[i];
            if (ch == '(') {
                st[++top] = i;
            } else if (ch == ')') {
                int j = st[top--];
                links[i] = j;
                links[j] = i;
            }
        }

        StringBuilder ans = new StringBuilder();
        int step = 1;
        for (int i = 0; i < n; i += step) {
            char ch = cs[i];
            if (ch == '(' || ch == ')') {
                i = links[i]; // 跳到对应的括号位置
                step = -step; // 反向移动
            } else {
                ans.append(ch);
            }
        }
        return ans.toString();
    }

}
