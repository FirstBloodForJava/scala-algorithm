package com.oycm.algorithm.bb;

public class Solution_10 {

    /**
     * 809. <a href="https://leetcode.cn/problems/expressive-words/description/">情感丰富的文字</a> 1605
     *
     * @param s
     * @param words
     * @return
     */
    public int expressiveWords(String s, String[] words) {
        /*
        判断 words 中的字符串，是否能通过在字母后面添加相同字母，和 s 相等，且该字母的数量要 大于等于 3
        和 925 题目有点相似，多个一个数量要求
         */
        int ans = 0;

        for (String t : words) {
            if (expand(s, t)) {
                ans++;
            }
        }

        return ans;
    }

    private boolean expand(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            // 扩展后 s[i] 的数量
            char ch = s.charAt(i);
            int cnti = 0;
            while (i < s.length() && s.charAt(i) == ch) {
                ++cnti;
                ++i;
            }
            int cntj = 0;
            while (j < t.length() && t.charAt(j) == ch) {
                ++cntj;
                ++j;
            }
            // 扩展后数量 小于 扩展前
            if (cnti < cntj) {
                return false;
            }
            if (cnti != cntj && cnti < 3) {
                return false;
            }
        }
        return i == s.length() && j == t.length();
    }


    public static void main(String[] args) {
        Solution_10 solution = new Solution_10();
        System.out.println(solution.expressiveWords("heeellooo", new String[]{"helo"}));
    }


}
