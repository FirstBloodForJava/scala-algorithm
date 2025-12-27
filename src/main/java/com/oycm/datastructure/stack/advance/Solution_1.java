package com.oycm.datastructure.stack.advance;

import java.util.*;

public class Solution_1 {

    /**
     * 3170. <a href="https://leetcode.cn/problems/lexicographically-minimum-string-after-removing-stars/description/">删除星号以后字典序最小的字符串</a> 1772
     * <p>
     * s 可能包含任意数量的 '*' 字符, 按以下操作 删除所有的 '*' 字符
     * <p>
     * 删除最左边的 '*' 字符，同时删除该星号字符左边一个字典序 最小 的字符。如果有多个字典序最小的字符，你可以删除它们中的任意一个。
     *
     * @param s
     * @return 执行删除后, 字典序最小的字符串
     */
    public String clearStars(String s) {
        /*
        如果要删除最小的 字典序字符 删除最靠后的, 如果有两个字符, 删除最靠右的最小字符, 才能是字典序最小
         */
        Set<Integer> del = new HashSet<>();
        PriorityQueue<Integer> min = new PriorityQueue<>((a, b) -> s.charAt(a) == s.charAt(b) ? b - a : s.charAt(a) - s.charAt(b));
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '*') {
                min.add(i);
            } else {
                del.add(i);
                del.add(min.poll());
            }
        }
        char[] ans = new char[n - del.size()];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (!del.contains(i)) {
                ans[j++] = s.charAt(i);
            }
        }

        return new String(ans);
    }

}
