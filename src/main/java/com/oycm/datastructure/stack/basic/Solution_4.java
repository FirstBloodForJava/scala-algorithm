package com.oycm.datastructure.stack.basic;


public class Solution_4 {

    /**
     * 2390. <a href="https://leetcode.cn/problems/removing-stars-from-a-string/description/">从字符串中移除星号</a> 1348
     *
     * 一步操作中，可以选中 s 中的 一个 星号，移除左侧最近的那个非星号字符，并移除该星号自身。
     *
     * @param s 包含若干星号 * 的字符串
     * @return
     */
    public static String removeStars(String s) {
        /*
        如果左侧全是 *，* 是需要保留的（题目不会有这种情况）
         */
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                res.deleteCharAt(res.length() - 1);
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeStars("leet**cod*e"));
        System.out.println(removeStars("erase*****"));
        // 题目不会出现这种情况
//        System.out.println(removeStars("*****"));
    }
}
