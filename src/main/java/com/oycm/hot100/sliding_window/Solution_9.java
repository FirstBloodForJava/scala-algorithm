package com.oycm.hot100.sliding_window;

public class Solution_9 {

    /**
     * 3. <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">无重复字符的最长子串</a>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        /*
        给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
         */
        /*
        s 长度记为 n；暴力做法
        i [0, n) 循环，查找 i 开始不含重复字符测子串长度
        初始化 l = 0, r = 0, 保证 [l ,r] 区间字符串数量均 <= 1，字符数量 > 1 时，不断右移 l
         */
        int ans = 0;
        int n = s.length();
        int l = 0, r = 0;
        char[] cs = s.toCharArray();
        int[] cnt = new int[128];
        while (r < n) {
            cnt[cs[r]]++;
            while (cnt[cs[r]] > 1) {
                cnt[cs[l]]--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

}
