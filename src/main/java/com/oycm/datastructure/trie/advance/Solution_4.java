package com.oycm.datastructure.trie.advance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_4 {


    /**
     * 3093. <a href="https://leetcode.cn/problems/longest-common-suffix-queries/description/">最长公共后缀查询</a> 2118
     *
     * @param wordsContainer
     * @param wordsQuery
     * @return
     */
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        /*
        对于 wordsQuery[i], 从 wordsContainer 中找到一个与 wordsQuery[i] 有最长公共后缀的字符串
            如果有两个或更多字符串有最长公共前缀，返回长度最短的字符串
            如果最短的长度有多个，则返回更早出现的字符串
         */
        /*
        wordsContainer, wordsQuery 反转后，变成找最长公共前缀的字符串
        公共前缀为 空字符串
            长度越短，优先级越高；下标越小，优先级越高；长度优先下标
            word.length * wordsContainer.length + i; 用来表示 字符串的优先级
            wordsContainer[i].length 和最大为 5 * 10^5, word.length * wordsContainer.length + i 最大是多少?
            n 个 wordsContainer[i].length 和 <= 5 * 10^5
            n * max(wordsContainer[i].length) 的最大值是多少
            设 max(wordsContainer[i].length) 为 maxL, 元素尽可能多，则其余 wordsContainer[i] = 1 为最佳
                n - 1 + maxL <= 5 * 10^5
                maxL <= 5 * 10^5 - n + 1

                n * maxL <= n * (5 * 10^5 - n + 1) <= 5*10^5 n - n^2 + n, 在 [0, 5*10^5 + 1] 中间取最大值
                250001 * 249999 超过了 int 类型
         */
        int n = wordsQuery.length, m = wordsContainer.length;
        int[] ans = new int[n];
        Trie trie = new Trie();
        // 空前缀的答案
        long empty = Long.MAX_VALUE;
        trie.end = true;
        for (int i = 0; i < m; i++) {
            trie.insert(wordsContainer[i], m, i);
            empty = Math.min(empty, (long) m * wordsContainer[i].length() + i);
        }
        trie.val = empty;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = wordsQuery[i];
            Trie cur = trie;
            int j = word.length() - 1;
            while (j >= 0 && cur.son.containsKey(word.charAt(j))) {
                cur = cur.son.get(word.charAt(j));
                j--;
            }
            if (map.containsKey(word.substring(j + 1))) {
                ans[i] = map.get(word.substring(j + 1));
            } else {
                ans[i] = (int) (dfsMin(cur, Long.MAX_VALUE) % m);
                map.put(word.substring(j + 1), ans[i]);
            }

        }

        return ans;

    }

    public long dfsMin(Trie cur, long val) {
        if (cur.end) {
            return Math.min(cur.val, val);
        }
        long minVal = val;
        for (Trie next : cur.son.values()) {
            minVal = Math.min(minVal, dfsMin(next, val));
        }
        return minVal;

    }

    static class Trie {
        long val;
        boolean end;
        Map<Character, Trie> son = new HashMap<>();

        public void insert(String word, int n, int i) {
            Trie cur = this;
            char[] cs = word.toCharArray();
            for (int j = cs.length - 1; j >= 0; j--) {
                cur = cur.son.computeIfAbsent(cs[j], o -> new Trie());
            }
            cur.end = true;
            // wordsContainer 存在相同字符情况
            if (cur.val == 0) {
                cur.val = (long) word.length() * n + i;
            }
        }
    }

    public static void main(String[] args) {
        //int[] a1 = new Solution_4().stringIndices(new String[]{"abcd", "bcd", "xbcd"}, new String[]{"cd", "bcd", "xyz"});


        //int[] a2 = new Solution_4().stringIndices(new String[]{"abcd", "bcda"}, new String[]{"cdf", "afa"});

        //int[] a3 = new Solution_4().stringIndices(new String[]{"abcde", "abcde"}, new String[]{"abcde", "bcde", "cde", "de", "e"});

        int[] a4 = new Solution_4().stringIndices(new String[]{"abcdefgh", "poiuygh", "ghghgh"}, new String[]{"gh", "acbfgh", "acbfegh"});
        System.out.println(Arrays.toString(a4));


    }

}
