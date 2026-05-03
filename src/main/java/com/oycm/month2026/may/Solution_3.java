package com.oycm.month2026.may;

public class Solution_3 {

    /**
     * 796. <a href="https://leetcode.cn/problems/rotate-string/description/">旋转字符串</a> 1167
     *
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString(String s, String goal) {
        /*
        给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
        s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
         */
        /*
        s 长记为 n，s 旋转操作后就是 2s 长为 n 的子串
        无非就是判断 goal 是否为 2s 长为 n 的子串
         */
        return s.length() == goal.length() && (s + s).contains(goal);
    }

}
