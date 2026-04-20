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
            if (isLongPressedName(s, t)) {
                System.out.println(t);
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

    public boolean isLongPressedName(String typed, String name) {
        /*
        遍历 typed name,
            如果 typed[j] == name[i] 都自增；
            如果不相等，看 typed[j] 是不是和前一个字符多次输入，如果不是，则就是不匹配
        最后看 是否完全遍历所有 name 字符
         */
        int n = name.length(), m = typed.length();
        int i = 0, j = 0;
        // 是否扩张
        boolean expend = false;
        int same = 0;
        while (j < m) {
            if (i < n && name.charAt(i) == typed.charAt(j)) {
                if (expend) {
                    expend = false;
                    if (same < 3) {
                        return false;
                    }
                }
                // 和前面字符相同
                if (i > 0 && name.charAt(i) == name.charAt(i - 1)) {
                    same++;
                } else {
                    same = 1;
                }
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
                expend = true;
                same++;
            } else {
                return false;
            }
        }
        return i == n && (!expend || same >= 3);
    }


    public static void main(String[] args) {
        Solution_10 solution = new Solution_10();
        System.out.println(solution.expressiveWords("dddiiiinnssssssoooo", new String[]{"dinnssoo","ddiinnso","ddiinnssoo"}));
    }


}
