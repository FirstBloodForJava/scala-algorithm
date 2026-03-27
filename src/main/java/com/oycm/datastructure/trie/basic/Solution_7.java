package com.oycm.datastructure.trie.basic;

import java.util.*;

public class Solution_7 {

    /**
     * 1268. <a href="https://leetcode.cn/problems/search-suggestions-system/description/">搜索推荐系统</a> 1573
     *
     * @param products   字符串数组
     * @param searchWord 待搜索字符串
     * @return
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        /*
        依次输入单词 searchWord 的每一个字母后, 找出 products 数组中前缀与 searchWord 相同的最多三个产品
        前缀相同的可推荐产品超过 3 个，返回字典序最小的 3 个
         */
        /*
        先用字典树初始化 products, 遍历 searchWord 过程中，存在相同前缀时
            使用 dfs 遍历处最小的 3 棵字典树
            记录相同前缀的前 3 个字符串
         */
        Trie root = new Trie();
        for (String product : products) {
            Trie cur = root;
            for (char c : product.toCharArray()) {
                c -= 'a';
                if (cur.son[c] == null) {
                    cur.son[c] = new Trie();
                }
                cur = cur.son[c];
                cur.words.add(product);
                if (cur.words.size() > 3) {
                    cur.words.poll();
                }
            }

        }
        List<List<String>> ans = new ArrayList<>();
        Trie cur = root;

        for (char c : searchWord.toCharArray()) {
            if (cur == null) {
                ans.add(new ArrayList<>());
            } else {
                List<String> list = new ArrayList<>();
                cur = cur.son[c - 'a'];
                if (cur != null) {
                    while (!cur.words.isEmpty()) {
                        list.add(cur.words.poll());
                    }
                    Collections.reverse(list);
                }
                ans.add(list);

            }
        }

        return ans;
    }

    static class Trie {
        Trie[] son = new Trie[26];
        PriorityQueue<String> words = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public static void main(String[] args) {
        new Solution_7().suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"},
                "mouse");
        PriorityQueue<String> words = new PriorityQueue<>(Comparator.reverseOrder());
        words.add("a");
        words.add("b");
        System.out.println(words.poll());
        System.out.println();
    }

}
