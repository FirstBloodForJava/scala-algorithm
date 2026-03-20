package com.oycm.datastructure.trie.basic;

import com.oycm.DataCreateUtils;

import java.util.List;

public class Solution_3 {

    /**
     * 648. <a href="https://leetcode.cn/problems/replace-words/description/">单词替换</a>
     *
     * @param dictionary 许多 词根 组成的词典
     * @param sentence   用空格分隔单词形成的句子
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        /*
        将句子中的所有 衍生词 用 词根 替换掉。如果 衍生词 有许多可以形成它的 词根，则用 最短 的 词根 替换它
         */
        /*
        先构建一个 字典树类，初始化字典树
         */
        Trie root = new Trie();
        for (String s : dictionary) {
            root.insert(s);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = root.findPre(words[i]);
        }

        return String.join(" ", words);
    }

    static class Trie {
        Trie[] son = new Trie[26];
        boolean end;

        public void insert(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.son[idx] == null) {
                    cur.son[idx] = new Trie();
                }
                cur = cur.son[idx];
            }
            cur.end = true;
        }

        public String findPre(String word) {
            StringBuilder ans = new StringBuilder();
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.son[idx] == null) {
                    return word;
                }
                ans.append(c);
                cur = cur.son[idx];
                if (cur.end) {
                    return ans.toString();
                }
            }
            return ans.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_3().replaceWords(DataCreateUtils.toListString("[\"cat\",\"bat\",\"rat\"]"), "the cattle was rattled by the battery"));
    }

}
