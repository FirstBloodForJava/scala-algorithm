package com.oycm.datastructure.stack.adjoin;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_2 {

    /**
     * 1047.<a href="https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/">删除字符串中的所有相邻重复项</a>  1286
     *
     * @param s
     * @return 重复选择两个相邻且相同的字母删除 后的结果
     */
    public String removeDuplicates(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        char[] cs = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (cs[stack.peek()] == cs[i]) {
                // * 标记删除
                cs[stack.pop()] = '*';
                cs[i] = '*';
            } else {
                stack.push(i);
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

}
