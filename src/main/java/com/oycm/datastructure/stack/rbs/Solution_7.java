package com.oycm.datastructure.stack.rbs;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_7 {

    /**
     * 1249. <a href="https://leetcode.cn/problems/minimum-remove-to-make-valid-parentheses/description/">移除无效的括号</a> 1657
     *
     * @param s 由小写字母和 ( | ) 组成
     * @return 删除最少数目的 '(' ')' 使得剩下的括号字符串有效
     */
    public String minRemoveToMakeValid(String s) {
        /*
        多次遍历可以实现, 删除栈中有效的括号, 剩下的就是无效需要删除的括号
         */
        int n = s.length();
        char[] cs = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            // 括号
            if (!Character.isLetter(c)) {
                if (!stack.isEmpty() && cs[stack.peek()] == '(' && c == ')') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        if (stack.size() == n) {
            return "";
        }
        if (stack.size() == 0) {
            return s;
        }
        // 标记删除
        while (!stack.isEmpty()) {
            cs[stack.pop()] = '*';
        }
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] != '*') {
                cs[idx++] = cs[i];
            }
        }
        return new String(cs, 0, idx);
    }

}
