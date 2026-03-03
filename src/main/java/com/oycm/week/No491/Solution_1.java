package com.oycm.week.No491;

public class Solution_1 {

    /**
     * 3856. <a href="https://leetcode.cn/problems/trim-trailing-vowels/description/">移除尾部元音字母</a>
     *
     * @param s
     * @return
     */
    public String trimTrailingVowels(String s) {
        int end = s.length();
        char[] cs = s.toCharArray();
        for (int i = end - 1; i >= 0 ; i--) {
            if (cs[i] == 'a' || cs[i] == 'e' || cs[i] == 'i' || cs[i] == 'o' || cs[i] == 'u') {
                end--;
            } else {
                break;
            }
        }

        return s.substring(0, end);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_1().trimTrailingVowels("idea"));
    }

}
