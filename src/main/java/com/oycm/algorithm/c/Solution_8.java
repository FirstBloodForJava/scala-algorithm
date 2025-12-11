package com.oycm.algorithm.c;


public class Solution_8 {

    /**
     * 1957. <a href="https://leetcode.cn/problems/delete-characters-to-make-fancy-string/description/">删除字符使字符串变好</a> 1358
     * <p>
     * 一个字符串没有三个连续相同的字符，则认为 它是一个好字符串
     *
     * @param s
     * @return 求删除最少的字符，使它变成一个好字符串
     */
    public String makeFancyString(String s) {
        int cnt = 0;
        int n = s.length();
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < n; i++) {
            cnt++;
            if (cnt < 3) {
                r.append(s.charAt(i));
            }
            if (i < n - 1 && s.charAt(i) != s.charAt(i + 1)) {
                cnt = 0;
            }
        }
        return r.toString();
    }
}
