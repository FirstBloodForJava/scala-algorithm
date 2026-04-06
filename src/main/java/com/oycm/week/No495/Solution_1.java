package com.oycm.week.No495;

public class Solution_1 {

    /**
     * 3884. <a href="https://leetcode.cn/problems/first-matching-character-from-both-ends/description/">双端字符匹配</a> 1161
     *
     * @param s
     * @return 最小的下标 i，使得 s[i] == s[n - i - 1]
     */
    public int firstMatchingIndex(String s) {
        /*
        6 = 12 / 2
        5 = 11 / 2
         */
        int ans = -1;
        int n = s.length();
        for (int i = n / 2; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(n - i - 1)) {
                ans = i;
            }
        }

        return ans;
    }
}
