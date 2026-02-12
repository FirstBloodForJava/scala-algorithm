package com.oycm.algorithm.e.enum_right_log_left.extension;

public class Solution_4 {

    /**
     * 3713. <a href="https://leetcode.cn/problems/longest-balanced-substring-i/description/">最长的平衡子串 I</a> 1490
     *
     * 平衡子串: 子串 中所有 不同 字符出现的次数都 相同
     * @param s
     * @return
     */
    public int longestBalanced(String s) {
        int ans = 0, n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < n; j++) {
                cnt[cs[j] - 'a']++;
                if (isBalance(cnt, cnt[cs[j] - 'a'])) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
    // 判断是否为平衡子字符串
    public boolean isBalance(int[] cnt, int t) {
        for (int i : cnt) {
            if (i > 0 && i != t) {
                return false;
            }
        }
        return true;
    }

}
