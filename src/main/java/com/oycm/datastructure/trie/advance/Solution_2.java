package com.oycm.datastructure.trie.advance;

public class Solution_2 {

    /**
     * 676. <a href="https://leetcode.cn/problems/implement-magic-dictionary/description/">实现一个魔法字典</a>
     */
    static class MagicDictionary {
        /*
        注意 存在 hello, search("hello") 为 false
         */
        Trie trie;

        public MagicDictionary() {
            trie = new Trie();
        }

        /**
         * @param dictionary 单词列表中的单词 互不相同
         */
        public void buildDict(String[] dictionary) {
            for (String s : dictionary) {
                trie.insert(s);
            }
        }

        /**
         * @param searchWord 待搜索单词
         * @return 根据字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配, 必须要替换一个字符串
         */
        public boolean search(String searchWord) {
            return trie.search(searchWord);
        }
    }

    static class Trie {
        Trie[] son = new Trie[26];
        boolean end;

        public void insert(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                c -= 'a';
                if (cur.son[c] == null) {
                    cur.son[c] = new Trie();
                }
                cur = cur.son[c];
            }
            cur.end = true;
        }

        public boolean search(String word) {
            return dfs(word, 0, 1, this);
        }

        public boolean dfs(String word, int idx, int cnt, Trie cur) {
            if (word.length() == idx) return cur.end && cnt == 0;
            // 通配符搜索
            if (cnt > 0) {
                for (int i = 0; i < cur.son.length; i++) {
                    if (cur.son[i] != null && i != word.charAt(idx) - 'a' && dfs(word, idx + 1, cnt - 1, cur.son[i])) {
                        return true;
                    }
                }

            }
            Trie son = cur.son[word.charAt(idx) - 'a'];
            return son != null && dfs(word, idx + 1, cnt, son);
        }
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(magicDictionary.search("hello")); // 返回 False
        System.out.println(magicDictionary.search("hhllo")); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
        magicDictionary.search("hell"); // 返回 False
        magicDictionary.search("leetcoded"); // 返回 False
    }

}
