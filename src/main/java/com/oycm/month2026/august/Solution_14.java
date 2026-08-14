package com.oycm.month2026.august;

public class Solution_14 {

    /**
     * 3090. <a href="https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/description/">每个字符最多出现两次的最长子字符串</a> 1329
     *
     * @param s
     * @return
     */
    public int maximumLengthSubstring(String s) {
        /*
        滑动窗口：字符串越长，出现相同字符的数量越多，具有单调性
         */
        int[] cnt = new int[26];
        char[] cs = s.toCharArray();
        int l = 0, r = 0, ans = 0;
        while (r < s.length()) {
            cnt[cs[r] - 'a']++;
            while (cnt[cs[r] - 'a'] > 2) {
                cnt[cs[l++] - 'a']--;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

}
