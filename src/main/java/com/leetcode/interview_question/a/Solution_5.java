package com.leetcode.interview_question.a;

public class Solution_5 {

    /**
     * 面试题 01.05. <a href="https://leetcode.cn/problems/one-away-lcci/description/">一次编辑</a>
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        /*
        字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。
        给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
         */
        /*
        分类讨论：
        先规定 first <= second
            长度相等
            first + 1 = second 当 first[i] 和 second[i] 不匹配 first[i] 位置插入，和 second[i] 删除时等价的
            其它情况肯定不会在一次编辑后相等
         */
        if (first.length() > second.length()) {
            String temp = first;
            first = second;
            second = temp;
        }

        if (first.length() == second.length()) {
            int mod = 1;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) == second.charAt(i)) {
                    continue;
                }
                mod--;
                if (mod < 0) return false;
            }

        } else if (first.length() + 1 == second.length()) {
            int mod = 0;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) == second.charAt(i + mod)) {
                    continue;
                }
                mod++;
                if (mod > 1 || first.charAt(i) != second.charAt(i + mod)) {
                    return false;
                }
            }

        } else {
            return false;
        }

        return true;
    }

}
