package com.oycm.datastructure.stack.basic;


public class Solution_2 {

    /**
     * 844. <a href="https://leetcode.cn/problems/backspace-string-compare/description/">比较含退格的字符串</a> 1228
     * <p>
     * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符（删除前面的字符）。
     *
     * @param s 字符串, 只含有小写字母以及字符 '#'
     * @param t 字符串, 只含有小写字母以及字符 '#'
     * @return
     */
    public static boolean backspaceCompare(String s, String t) {
        /*
        时间复杂度 O(s + t + m)
         */
        int c1 = 0;
        char[] s1 = s.toCharArray();
        for (int i = 0; i < s1.length; i++) {
            if (c1 > 0 && s1[i] == '#') {
                c1--;
            } else if (s1[i] != '#') {
                s1[c1++] = s1[i];
            }
        }
        int c2 = 0;
        char[] t1 = t.toCharArray();
        for (int i = 0; i < t1.length; i++) {
            if (c2 > 0 && t1[i] == '#') {
                c2--;
            } else if (t1[i] != '#') {
                t1[c2++] = t1[i];
            }
        }
        if (c1 != c2) {
            return false;
        }
        int i = 0;
        while (i < c1) {
            if (s1[i] != t1[i]) {
                return false;
            }
            i++;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));
    }


}
