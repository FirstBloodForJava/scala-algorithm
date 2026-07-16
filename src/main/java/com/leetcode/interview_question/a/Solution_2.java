package com.leetcode.interview_question.a;

public class Solution_2 {

    /**
     * 面试题 01.02. <a href="https://leetcode.cn/problems/check-permutation-lcci/description/">判定是否互为字符重排</a>
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        /*
        给定两个由小写字母组成的字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
         */
        /*
        两个字符串每种字符数量是否一样
        长度不一样，肯定不同
        先统计一个字符串的字符数量，如果两者有不同点，一定会在某个位置出现字符数量为负数的情况，否则就完全一样。
         */
        if (s1.length() != s2.length()) return false;
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            c -= 'a';
            cnt[c]++;
        }
        for (char c : s2.toCharArray()) {
            c -= 'a';
            if (cnt[c] == 0) return false;
            cnt[c]--;
        }

        return true;
    }
}
