package com.oycm.datastructure.stack.adjoin;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1 {

    /**
     * 2696. <a href="https://leetcode.cn/problems/minimum-string-length-after-removing-substrings/description/">删除子串后的字符串最小长度</a> 1282
     * 从 s 中删除 任一个 "AB" 或 "CD" 子字符串
     *
     * @param s
     * @return 可获得的最终字符串的 最小 可能长度(删除后, 可能产生新的 AB CD 子串)
     */
    public int minLength(String s) {

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (stack.peek() == 'A' && c == 'B') {
                    stack.pop();
                }else if (stack.peek()== 'C' && c == 'D') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.size();
    }

}
