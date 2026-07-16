package com.leetcode.interview_question.a;

public class Solution_1 {

    /**
     * 面试题 01.01. <a href="https://leetcode.cn/problems/is-unique-lcci/description/">判定字符是否唯一</a>
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        /*
        实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
        只包含小写字母
         */
        /*
        统计字符出现的数量，如果数量出现大于 1 的情况，返回 false
         */
        int bit = 0;
        for (char c : astr.toCharArray()) {
            c -= 'a';
            if ((bit & (1 << c)) > 0) {
                return false;
            }
            bit |= 1 << c;

        }

        return true;
    }
}
