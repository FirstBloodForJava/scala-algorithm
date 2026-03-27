package com.oycm.datastructure.trie.basic;

public class Solution_5 {

    /**
     * 2416. <a href="https://leetcode.cn/problems/sum-of-prefix-scores-of-strings/description/">字符串的前缀分数和</a> 1725
     *
     * @param words 非空 字符串 数组
     * @return
     */
    public int[] sumPrefixScores(String[] words) {
        /*
        定义字符串 term 的 分数 等于以 term 作为 前缀 的 words[i] 的数目
        返回一个长度为 n 的数组 answer ，其中 answer[i] 是 words[i] 的每个非空前缀的分数 总和
         */
        /*
        trie son, cnt a
         */
        int n = words.length;
        int[] ans = new int[n];
        Trie root = new Trie();
        for (String word : words) {
            Trie cur = root;
            for (char c : word.toCharArray()) {
                c -= 'a';
                if (cur.son[c] == null) {
                    cur.son[c] = new Trie();
                }
                cur = cur.son[c];
                cur.cnt++;
            }
        }
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            Trie cur = root;
            for (char c : words[i].toCharArray()) {
                cur = cur.son[c - 'a'];
                cnt += cur.cnt;
            }
            ans[i] = cnt;
        }
        return ans;
    }

    static class Trie {
        int cnt;
        Trie[] son = new Trie[26];
    }
}
