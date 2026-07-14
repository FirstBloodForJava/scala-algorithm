package com.oycm.string.ac_automaton;

public class Solution_2 {

    /**
     * 1967. 作为子字符串出现在单词中的字符串数目
     * <br>
     * 1967. <a href="https://leetcode.cn/problems/number-of-strings-that-appear-as-substrings-in-word/description/">作为子字符串出现在单词中的字符串数目</a> 1232
     *
     * @param patterns
     * @param word
     * @return
     */
    public int numOfStrings(String[] patterns, String word) {
        /*
        给你一个字符串数组 patterns 和一个字符串 word ，统计 patterns 中有多少个字符串是 word 的子字符串。返回字符串数目。
        子字符串 是字符串中的一个连续字符序列。
         */
        AhoCorasick ac = new AhoCorasick();
        for (String pattern : patterns) {
            ac.put(pattern);
        }
        ac.buildFail();
        Node cur = ac.root;
        int ans = 0;

        for (char c : word.toCharArray()) {
            /*
            包含了匹配和未匹配的情况，未匹配会自动移动到 fail 节点
             */
            cur = cur.son[c - 'a'];
            /*
            可能匹配更短的模式串，要继续在 last 链上找
             */
            for (Node match = cur; match.cnt >= 0; match = match.last) {
                ans += match.cnt;
                // 避免重复统计
                match.cnt = -1;
            }
        }

        return ans;
    }

}
