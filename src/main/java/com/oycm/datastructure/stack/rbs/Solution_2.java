package com.oycm.datastructure.stack.rbs;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_2 {


    /**
     * 921. <a href="https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/description/">使括号有效的最少添加</a>
     *
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        /*
        (() 使括号成对的 最少括号添加次数, (()()( 消不掉的括号就是要补全的括号
         */
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else if (c == ')' && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

}
