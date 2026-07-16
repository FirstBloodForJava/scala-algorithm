package com.leetcode.interview_question.a;

public class Solution_4 {

    /**
     * 面试题 01.04. <a href="https://leetcode.cn/problems/palindrome-permutation-lcci/description/">回文排列</a>
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        /*
        给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
        回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
        回文串不一定是字典当中的单词。
         */
        /*
        排列，只需要看里面的字符数量情况
        当长度为奇数时，中间的字符可以出现奇数次，其余字符必须出现偶数次；
        当长度为偶数时，字符数量必须时偶数次
         */
        int[] cnt = new int[128];
        for (char c : s.toCharArray()) {
            cnt[c]++;
        }
        int odd = s.length() % 2;
        for (int x : cnt) {
            if (x % 2 != 0) {
                odd--;
            }
            if (odd < 0) {
                return false;
            }
        }

        return true;
    }

}
