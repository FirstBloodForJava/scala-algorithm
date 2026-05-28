package com.oycm.string.kmp;

public class Solution_6 {

    /**
     * 1668. <a href="https://leetcode.cn/problems/maximum-repeating-substring/description/">最大重复子字符串</a> 1396
     *
     * @param sequence
     * @param word
     * @return
     */
    public int maxRepeating(String sequence, String word) {
        /*
        给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。
        单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0。
        给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
         */
        /*
        使用 kmp，匹配时，j 回退到 0，同时判断是否和上一次找到的下标连续 i - pre + 1 == 2m
        "aaabaaaab aaabaaaabaaaabaaaabaaaaba" 这种情况计算错误，必须找出所有的子串下标
         */
        int k = 0;
        int m = word.length();
        int[] next = new int[m];
        for (int i = 1, cnt = 0; i < m; i++) {
            while (cnt > 0 && word.charAt(i) != word.charAt(cnt)) {
                cnt = next[cnt - 1];
            }
            if (word.charAt(i) == word.charAt(cnt)) {
                cnt++;
            }
            next[i] = cnt;
        }
        int[] f = new int[sequence.length()];
        int cnt = 0;
        for (int i = 0, j = 0; i < sequence.length(); i++) {
            while (j > 0 && sequence.charAt(i) != word.charAt(j)) {
                j = next[j - 1];
            }
            if (sequence.charAt(i) == word.charAt(j)) {
                j++;
            }
            if (j == m) {
                int pre = i - j + 1;
                f[pre] = (pre >= m ? f[pre - m] : 0) + 1;
                k = Math.max(f[pre], k);
                j = next[j - 1];
            }
        }

        return k;
    }

}
