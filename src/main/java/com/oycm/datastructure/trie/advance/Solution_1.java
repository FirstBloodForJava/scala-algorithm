package com.oycm.datastructure.trie.advance;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {

    /**
     * 211. <a href="https://leetcode.cn/problems/design-add-and-search-words-data-structure/description/">添加与搜索单词 - 数据结构设计</a>
     */
    class WordDictionary {

        Trie root;

        public WordDictionary() {
            root = new Trie();
        }

        /**
         * 将 word 添加到数据结构中
         *
         * @param word 由小写英文字母组成
         */
        public void addWord(String word) {
            /*

             */
            Trie cur = root;
            for (char c : word.toCharArray()) {
                Trie son = cur.son.get(c);
                if (son == null) {
                    son = new Trie();
                    cur.son.put(c, son);
                }
                cur = son;
            }
            cur.end = true;
        }

        /**
         * @param word 由小写英文字母组成, . 表示任意字符
         * @return 数据结构中 是否 存在字符串与 word 匹配,
         */
        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        public boolean dfs(String word, int idx, Trie cur) {
            if (word.length() == idx) return cur.end;
            char c = word.charAt(idx);
            if (c == '.') {
                for (Trie son : cur.son.values()) {
                    if (dfs(word, idx + 1, son)) {
                        return true;
                    }
                }
            } else {
                Trie son = cur.son.get(c);
                return son != null && dfs(word, idx + 1, son);
            }
            return false;
        }
    }

    class Trie {
        Map<Character, Trie> son = new HashMap<>();
        boolean end;
    }

}

