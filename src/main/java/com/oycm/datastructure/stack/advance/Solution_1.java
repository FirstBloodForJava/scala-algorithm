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

    public String answer_1(String s) {
        /*
        相同字符的下标记录到一个 List 集合, 遇到 * 弹出前面不为空的最后一个元素, 最后把所有下标添加到集合排序后有序拼接即可
         */
        List<Integer>[] stacks = new ArrayList[26];
        Arrays.setAll(stacks, o -> new ArrayList<>());
        char[] cs = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (cs[i] != '*') {
                stacks[cs[i] - 'a'].add(i);
                continue;
            }
            for (List<Integer> stack : stacks) {
                if (!stack.isEmpty()) {
                    stack.remove(stack.size() - 1);
                    break;
                }
            }
        }
        List<Integer> all = new ArrayList<>();
        for (List<Integer> stack : stacks) {
            all.addAll(stack);
        }
        Collections.sort(all);
        StringBuilder ans = new StringBuilder(all.size());
        for (Integer i : all) {
            ans.append(cs[i]);
        }
        return ans.toString();
    }

    public String answer_2(String s) {
        /*
        把要删除的 最小字符 标记为 *, 重新遍历 cs 原地修改即可
         */
        int n = s.length();
        List<Integer>[] stacks = new ArrayList[26];
        Arrays.setAll(stacks, o -> new ArrayList<>());
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (cs[i] != '*') {
                stacks[cs[i] - 'a'].add(i);
                continue;
            }
            for (List<Integer> stack : stacks) {
                if (!stack.isEmpty()) {
                    cs[stack.remove(stack.size() - 1)] = '*';
                    break;
                }
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
