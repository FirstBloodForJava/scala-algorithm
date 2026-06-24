package com.oycm.hot100.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution_69 {

    /**
     * 20. <a href="https://leetcode.cn/problems/valid-parentheses/description/">有效的括号</a>
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        /*
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
        有效字符串需满足：
            左括号必须用相同类型的右括号闭合。
            左括号必须以正确的顺序闭合。
            每个右括号都有一个对应的相同类型的左括号。
         */
        /*
        (, {, [ 入栈，如果匹配到 ), }, ]，对应的栈顶元素要是对应的左括号
         */
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                st.push(c);
            } else {
                if (st.isEmpty() || map.get(c) != st.pop()) return false;
            }
        }
        return st.isEmpty();
    }

}
