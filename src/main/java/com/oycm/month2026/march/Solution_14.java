package com.oycm.month2026.march;

public class Solution_14 {

    /**
     * 1415. <a href="https://leetcode.cn/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/">长度为 n 的开心字符串中字典序第 k 小的字符串</a> 1576
     * <p>
     * 开心字符串:
     * 仅包含小写字母 ['a', 'b', 'c']
     * 对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）
     * <p>
     * 将长度为 n 的所有开心字符串按字典序排序，排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串
     *
     * @param n
     * @param k
     * @return
     */
    public String getHappyString(int n, int k) {
        /*
        题解
         */
        if (k > 3 << (n - 1)) {
            return "";
        }
        k--;
        char[] ans = new char[n];
        ans[0] = (char) ('a' + (k >> (n - 1)));
        for (int i = 1; i < n; i++) {
            ans[i] = (char) ('a' + (k >> (n - 1 - i) & 1));
            if (ans[i] >= ans[i - 1]) {
                ans[i]++;
            }
        }

        return new String(ans);
    }

}
