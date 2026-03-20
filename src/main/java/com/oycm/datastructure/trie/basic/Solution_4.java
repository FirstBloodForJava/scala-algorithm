package com.oycm.datastructure.trie.basic;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 720. <a href="https://leetcode.cn/problems/longest-word-in-dictionary/description/">词典中最长的单词</a>
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        /*
        返回能够通过 words 中其它单词逐步添加一个字母来构造得到的 words 中最长的单词
         */
        /*
        先把 words 从小到大排序
        构建字典树, 如果 当前单词的前一个单词时最后一个, 则更新答案; 否则继续遍历
        逐步才算
         */
        String ans = "";
        Arrays.sort(words);
        Trie root = new Trie();
        for (String word : words) {
            if (root.insert(word)) {
                if (word.length() > ans.length()) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    static class Trie {
        Trie[] son = new Trie[26];
        boolean end;

        public boolean insert(String word) {
            boolean flag = true;
            Trie cur = this;
            char[] cs = word.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                int idx = cs[i] - 'a';
                if (cur.son[idx] == null) {
                    cur.son[idx] = new Trie();
                }
                if (i < cs.length - 1) {
                    // 最后一个单词之前, 要全部是结尾
                    flag &= cur.son[idx].end;
                }
                cur = cur.son[idx];


            }
            cur.end = true;
            return flag;
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution_4().longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));
    }

}
