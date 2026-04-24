package com.oycm.algorithm.i.search;

public class Solution_9_1255 {

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
        题解：状态压缩，用二进制比特位表示 words 单词所有子集，判断子集是否合法
         */
        int[] cs = new int[26];
        for (char c : letters) {
            c -= 'a';
            cs[c]++;
        }
        int n = words.length;
        int maxScore = 0;
        for (int i = 1; i < (1 << n); i++) {
            // 当前子集使用的字符数量情况
            int[] subWordCnt = new int[26];
            for (int k = 0; k < n; k++) {
                if ((i & (1 << k)) == 0) {
                    continue;
                }
                for (char c : words[k].toCharArray()) {
                    subWordCnt[c - 'a']++;
                }
            }
            boolean ok = true;
            int canScore = 0;
            for (int j = 0; j < 26 && ok; j++) {
                canScore += score[j] * subWordCnt[j];
                ok = subWordCnt[j] <= cs[j];
            }
            if (ok && canScore > maxScore) {
                maxScore = canScore;
            }
        }

        return maxScore;
    }

}
