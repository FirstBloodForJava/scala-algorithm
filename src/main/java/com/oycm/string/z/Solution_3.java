package com.oycm.string.z;

public class Solution_3 {

    /**
     * 3045. <a href="https://leetcode.cn/problems/count-prefix-and-suffix-pairs-ii/description/">统计前后缀下标对 II</a> 2328
     *
     * @param words
     * @return
     */
    public long countPrefixSuffixPairs(String[] words) {
        /*
        给你一个下标从 0 开始的字符串数组 words。
        定义一个 布尔 函数 isPrefixAndSuffix ，它接受两个字符串参数 str1 和 str2：
            当 str1 同时是 str2 的前缀（prefix）和后缀（suffix）时，isPrefixAndSuffix(str1, str2) 返回 true，否则返回 false。
        例如，isPrefixAndSuffix("aba", "ababa") 返回 true，因为 "aba" 既是 "ababa" 的前缀，也是 "ababa" 的后缀，
        但是 isPrefixAndSuffix("abc", "abcd") 返回 false。
         */
        /*
        对于字符串 t，如果判断某个长度的字符串前后缀是否相等？
        z[i] 表示 s[i :] 与 t 前缀最长公共长度，如果 z[i] = n-i，那么 t[i :] 和其等长的前缀是相等的。
         */
        long ans = 0;
        Trie root = new Trie();
        for (String t : words) {
            char[] ts = t.toCharArray();
            int n = ts.length;
            int[] z = new int[n];
            int l = 0, r = 0;
            for (int i = 1; i < n; i++) {
                z[i] = Math.max(Math.min(z[i - l], r - i + 1), 0);
                while (i + z[i] < n && ts[i + z[i]] == ts[z[i]]) {
                    l = i;
                    r = i + z[i];
                    z[i]++;
                }
            }
            z[0] = n;

            Trie cur = root;
            for (int i = 0; i < n; i++) {
                int c = ts[i] - 'a';
                if (cur.son[c] == null) {
                    cur.son[c] = new Trie();
                }
                cur = cur.son[c];
                // 长为 i+1 的前缀和后缀相等
                if (z[n - 1 - i] == i + 1) {
                    // j 的前后缀相等，加上前面存在 i 数量
                    ans += cur.cnt;
                }
            }
            // i 数量增加
            cur.cnt++;
        }
        return ans;
    }

    static class Trie {
        Trie[] son = new Trie[26];
        int cnt;
    }

}
