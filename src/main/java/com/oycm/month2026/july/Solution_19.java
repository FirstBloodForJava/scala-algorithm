package com.oycm.month2026.july;

public class Solution_19 {

    /**
     * 1081. <a href="https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/description/">不同字符的最小子序列</a> 2185
     *
     * @param s
     * @return
     */
    public String smallestSubsequence(String s) {
        /*
        返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
         */
        /*
        题解思路：
        cbacdcbc 答案 acdb
         */
        char[] cs = s.toCharArray();
        int[] left = new int[26];
        for (char c : cs) {
            left[c - 'a']++;
        }
        StringBuilder ans = new StringBuilder();
        boolean[] on = new boolean[26];
        for (char c : cs) {
            left[c - 'a']--;
            if (on[c - 'a']) {
                // 前面已选中 c
                continue;
            }
            /*
            如果 c 比前面最后一个字符 x 还小，后面还存在字符 x，可以去掉前面 x 字符，最后再添加上
             */
            while (!ans.isEmpty() && c < ans.charAt(ans.length() - 1) && left[ans.charAt(ans.length() - 1) - 'a'] > 0) {
                on[ans.charAt(ans.length() - 1) - 'a'] = false;
                ans.deleteCharAt(ans.length() - 1);
            }
            ans.append(c);
            on[c - 'a'] = true;
        }

        return ans.toString();
    }

}
