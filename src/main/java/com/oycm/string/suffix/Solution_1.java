package com.oycm.string.suffix;

public class Solution_1 {

    /**
     * 1163. <a href="https://leetcode.cn/problems/last-substring-in-lexicographical-order/description/">按字典序排在最后的子串</a> 1864
     *
     * @param s
     * @return
     */
    public String lastSubstring(String s) {
        /*
        给你一个字符串 s ，找出它的所有子串并按字典序排列，返回排在最后的那个子串。
         */
        /*
        只有后缀子字符串才可能是排在最后的子字符串。
            任意一个非后缀字符串 s1，s1 向后延伸得到的后缀字符串 s2 比 s1 大，会排在 s1 的后面。
        定义 si 表示从 s[i] 开始的后缀子字符串。设 si 为已知最大后缀子字符串，j 指向待比较的后缀子字符串，初始 i=0, j=1。
        暴力的做法，j 从小到达枚举 sj，如果 si < sj，更新 i = j，最终 si 就是答案。
        当字符串为 aaaaaz，时间复杂度是 n^2 的。因为中间出现了重复字符，能否跳过一些后缀字符串的匹配。
        设 s[i] 和 s[j] 在第 k 个字符不匹配，即 s[i+k] != s[j+k]，假设 k > 0（k = 0 时没有重复字符，直接根据 s[i] 和 s[j] 大小即可判断）。
        s[i+1] = s[j+1], s[i+2] = s[j+2], ... s[i+k-1] = s[j+k-1]。
        如果 si[k] < sj[k]，即 s[i+k] < s[j+k]
            当 i+k > j，对于 m [1, k] 的后缀字符串 s(i+m)，s(i+m) 始终小于 s(j+m)
            当 i+k <= j，
        如果 si[k] > sj[k]，即 s[i+k] < s[j+k]
            对于 m [1, k]
         */
        int n = s.length();
        int i = 0, j = 1;
        while (j < n) {
            int k = 0;
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int t = i;
                i = j;
                j = Math.max(t + k + 1, j + 1);
            } else {
                j = j + k + 1;
            }
        }
        return s.substring(i);
    }

}
