package com.oycm.string.ac_automaton;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    /**
     * 1408. 数组中的字符串匹配
     * <br>
     * 1408. <a href="https://leetcode.cn/problems/string-matching-in-an-array/description/">数组中的字符串匹配</a> 1223
     *
     * @param words
     * @return
     */
    public List<String> stringMatching(String[] words) {
        /*
        给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。
        请你按 任意 顺序返回 words 中是其他单词的 子字符串 的所有单词。
         */
        /*
        思路：
        用 words 作为模式串构建字典树（记录单词在数组中的下标），再构建 fail 指针。
        枚举 words[i] 作为待查找文本，看字典树中是否有下标非 i 匹配的模式串
         */
        int n = words.length;
        boolean[] find = new boolean[n];
        AhoCorasickIdx ac = new AhoCorasickIdx();
        for (int i = 0; i < n; i++) {
            ac.put(words[i], i);
        }
        // 构建 fail 指针
        ac.buildFail();

        // words[i] 作为查找串
        for (int i = 0; i < n; i++) {
            String word = words[i];
            NodeIdx cur = ac.root;
            for (char c : word.toCharArray()) {
                cur = cur.son[c - 'a'];
                for (NodeIdx match = cur; match != ac.root; match = match.last) {
                    // 当前后缀有匹配更短符合要求的子串
                    if (match.cnt >= 0 && match.cnt != i) {
                        find[match.cnt] = true;
                    }
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (find[i]) {
                ans.add(words[i]);
            }
        }

        return ans;
    }

}
