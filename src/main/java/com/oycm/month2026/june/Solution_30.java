package com.oycm.month2026.june;

public class Solution_30 {

    /**
     * 1358. 包含所有三种字符的子字符串数目
     * 1358. <a href="https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters/description/">包含所有三种字符的子字符串数目</a> 1646
     *
     * @param s
     * @return
     */
    public int numberOfSubstrings(String s) {
        /*
        给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
        请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
         */
        /*
        l 从 0 遍历到 n-3
        如果 [l, r] 子串 a,b,c 都至少出现过一次，那么 [l, r] 后面追加第 r+1, r+2, ... n-1 字符得到子串都符合要求，不需要继续遍历。
        计算 [l-1, n-1] 有多少子串符合要求，还需重新遍历吗？子需要记录 [l, r] a,b,c 字符数量，移除 s[l]，减少其对于的字符数量
        当 [l,r] 符合条件时，移除左边的字符，左边能移除的数量就是当前以 r 结尾，符合要求的子串。
         */
        int ans = 0;
        int[] cnt = new int[3];
        int left = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            cnt[c - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                cnt[cs[left++] - 'a']--;
            }
            ans += left;
        }

        return ans;
    }

}
