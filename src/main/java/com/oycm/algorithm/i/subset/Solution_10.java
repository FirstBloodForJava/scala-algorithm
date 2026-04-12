package com.oycm.algorithm.i.subset;

public class Solution_10 {

    /**
     * 1255. <a href="https://leetcode.cn/problems/maximum-score-words-formed-by-letters/description/">得分最高的单词集合</a> 1882
     *
     * @param words   单词表
     * @param letters 字母表, 可能有重复字母
     * @param score
     * @return
     */
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        /*
        单词得分：拼写出的单词集合里包含的所有字母的得分之和
        单词拼写游戏的规则概述如下：
            玩家需要用字母表 letters 里的字母来拼写单词表 words 中的单词
            可以只使用字母表 letters 中的部分字母，但是每个字母最多被使用一次。
            单词表 words 中每个单词只能计分（使用）一次。
            'a', 'b', ... 'z' 得分对应 score[i] [0, 25]。
            求使用 letters 拼写 words 中单词的最高总得分
         */
        /*
        是否使用 letters 中字母 拼写 words[i]
         */
        int[] cs = new int[26];
        for (char c : letters) {
            c -= 'a';
            cs[c]++;
        }
        dfs(0, words, cs, score, 0);
        return maxScore;
    }

    int maxScore = 0;

    public void dfs(int i, String[] words, int[] cs, int[] score, int curScore) {
        if (i == words.length) {
            maxScore = Math.max(maxScore, curScore);
            return;
        }
        dfs(i + 1, words, cs, score, curScore);
        boolean enable = true;
        int canScore = 0;
        for (char c : words[i].toCharArray()) {
            c -= 'a';
            cs[c]--;
            if (cs[c] < 0) {
                enable = false;
            }
            canScore += score[c];
        }
        if (enable) {
            dfs(i + 1, words, cs, score, curScore + canScore);
        }

        for (char c : words[i].toCharArray()) {
            c -= 'a';
            cs[c]++;
        }

    }

}
