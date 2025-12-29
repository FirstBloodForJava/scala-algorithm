package com.oycm.datastructure.stack.rbs;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_3 {

    /**
     * 1021. <a href="https://leetcode.cn/problems/remove-outermost-parentheses/description/">删除最外层的括号</a> 1311
     *
     * @param s
     * @return
     */
    public String removeOuterParentheses(String s) {
        /*
        (?) 第一个左括号对应的右括号 就是 可拆分的子串
         */
        char[] cs = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        int n = s.length();
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '(' && flag) {
                cs[i] = '*';
                flag = false;
            } else if (stack.isEmpty() && cs[i] == ')') {
                cs[i] = '*';
                flag = true;
            } else if (cs[i] == '(') {
                stack.push(cs[i]);
            } else if (cs[i] == ')') {
                stack.pop();
            }
        }
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] != '*') {
                cs[idx++] = cs[i];
            }
        }
        return new String(cs, 0, idx);
    }

    public static void main(String[] args) {

    }

}
