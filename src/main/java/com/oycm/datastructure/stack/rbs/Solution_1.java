package com.oycm.datastructure.stack.rbs;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution_1 {

    /**
     * 20. <a href="https://leetcode.cn/problems/valid-parentheses/description/">有效的括号</a>
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        /*
        ([])[}]
         */
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character,Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || stack.pop() != map.get(c)) {
                return false;
            }
        }

        return stack.isEmpty();
    }

}
