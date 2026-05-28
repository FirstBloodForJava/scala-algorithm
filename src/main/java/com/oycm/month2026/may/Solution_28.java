package com.oycm.month2026.may;

public class Solution_28 {

    /**
     * 3093. <a href="https://leetcode.cn/problems/longest-common-suffix-queries/description/">最长公共后缀查询</a> 2118
     *
     * @param wordsContainer
     * @param wordsQuery
     * @return
     */
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        /*
        给你两个字符串数组 wordsContainer 和 wordsQuery。
        对于每个 wordsQuery[i] ，你需要从 wordsContainer 中找到一个与 wordsQuery[i] 有 最长公共后缀 的字符串。
            如果 wordsContainer 中有两个或者更多字符串有最长公共后缀，那么答案为长度 最短 的。
            如果有超过两个字符串有 相同 最短长度，那么答案为它们在 wordsContainer 中出现 更早 的一个。
         */
        /*
        wordsContainer[i] 字符串倒序建立字典树，每个节点维护最短长度和最短长度对应字符串下标
         */
        Trie root = new Trie();
        for (int i = 0; i < wordsContainer.length; i++) {
            char[] cs = wordsContainer[i].toCharArray();
            if (cs.length < root.minLen) {
                root.minLen = cs.length;
                root.idx = i;
            }
            Trie cur = root;
            for (int j = cs.length - 1; j >= 0; j--) {
                int c = cs[j] - 'a';
                if (cur.son[c] == null) {
                    cur.son[c] = new Trie();
                }
                cur = cur.son[c];
                if (cs.length < cur.minLen) {
                    cur.minLen = cs.length;
                    cur.idx = i;
                }
            }
        }
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String s = wordsQuery[i];
            Trie cur = root;
            for (int j = s.length() - 1; j >= 0 && cur.son[s.charAt(j) - 'a'] != null; j--) {
                cur = cur.son[s.charAt(j) - 'a'];
            }
            // 循环结束的 cur 就是最长公共后缀开始
            ans[i] = cur.idx;
        }
        return ans;
    }

    class Trie {
        Trie[] son = new Trie[26];
        int minLen = Integer.MAX_VALUE;
        int idx;
    }

}
