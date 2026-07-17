package com.leetcode.interview_question.a;

public class Solution_9 {

    /**
     * 面试题 01.09. <a href="https://leetcode.cn/problems/string-rotation-lcci/description/">字符串轮转</a>
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        /*
        字符串轮转。给定两个字符串 s1 和 s2 ，请编写代码检查 s2 是否为 s1 旋转而成。
        旋转 s1 的最后一个字符移到最前面，不断操作，是否能得到 s2
        比如，waterbottle 是 erbottlewat 旋转后的字符串）。
        erbottlewaterbottlewat
         */
        /*
        s1 == s2 的前提下，s1 + s1 是否包含 s2，
        可以用 工具类或 kmp 判断是否为子串
         */
        if (s1.length() != s2.length()) return false;
        return (s1 + s2).contains(s2);
    }

}
