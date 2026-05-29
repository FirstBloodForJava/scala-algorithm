package com.oycm.week2023.No332;

public class Solution_4 {

    /**
     * 2565. <a href="https://leetcode.cn/problems/subsequence-with-the-minimum-score/description/">最少得分子序列</a> 2432
     *
     * @param s
     * @param t
     * @return
     */
    public int minimumScore(String s, String t) {
        /*
        给你两个字符串 s 和 t。
        你可以从字符串 t 中删除任意数目的字符。
        如果没有从字符串 t 中删除字符，那么得分为 0 ，否则：
            令 left 为删除字符中的最小下标。
            令 right 为删除字符中的最大下标。
        字符串的得分为 right - left + 1 。
        请你返回使 t 成为 s 子序列的最小得分。
        一个字符串的 子序列 是从原字符串中删除一些字符后（也可以一个也不删除），剩余字符不改变顺序得到的字符串。（比方说 "ace" 是 "abcde" 的子序列，但是 "aec" 不是）。
         */
        /*
        题解思路：
        在 [left, right] 之间的字符，都是不影响得分的，可以都删除，且删除后更有机会让 t 是 s 的子序列，所以只考虑删除的字符串是 t 的子串，而不是子序列。
        删除子串后，t 就被分解成一个前缀 和 一个后缀。
        假设前缀匹配的是 s 的一个前缀 s[0 : i]，后缀匹配的是一个后缀 [i: n-1]
        定义如下
            pre[i] 表示子串 s[0 : i] 对应 t 的最长前缀结束下标；
            suf[i] 表示自创 s[i : n-1] 对应 t 的最长后缀开始下标；
            枚举 i，left = pre[i] + 1; right = suf[i+1] - 1
            ans = suf[i+1] - 1 - pre[i] - 1 + 1 = suf[i+1] - pre[i] - 1
         */
        int n = s.length();
        int m = t.length();
        int[] suf = new int[n + 1];
        suf[n] = m - 1;
        char[] cs = s.toCharArray();
        char[] ts = t.toCharArray();
        for (int i = n - 1, j = m - 1; i >= 0; i--) {
            if (cs[i] == ts[j]) {
                j--;
            }
            // t 是 s 子序列
            if (j < 0) return 0;
            suf[i] = j;
        }
        int ans = suf[0] + 1;
        for (int i = 0, j = 0; i < n; i++) {
            if (cs[i] == ts[j]) {
                j++;
                ans = Math.min(ans, suf[i + 1] - j + 1);
            }
        }

        return ans;
    }

}
