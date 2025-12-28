package com.oycm.datastructure.stack.adjoin;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_3 {

    /**
     * 1544. <a href="https://leetcode.cn/problems/make-the-string-great/description/">整理字符串</a> 1344
     * 0 <= i <= s.length-2
     * 若 s[i] 是小写字符，则 s[i+1] 不可以是相同的大写字符;
     * 若 s[i] 是大写字符，则 s[i+1] 不可以是相同的小写字符;
     * @param s
     * @return 删除符合上述条件的字符
     */
    public String makeGood(String s) {
        /*
        怎么写判断 'a' 和 'A', 'A' 和 'a' 匹配的问题 ?
         */

        Deque<Integer> stack = new ArrayDeque<>();
        char[] cs = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (Character.toLowerCase(cs[stack.peek()]) == Character.toLowerCase(cs[i]) && cs[stack.peek()] != cs[i]) {
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
