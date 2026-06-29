package com.oycm.algorithm.f.prefix_sum_compress;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 1177. <a href="https://leetcode.cn/problems/can-make-palindrome-from-substring/description/">构建回文串检测</a> 1848
     *
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        /*
        给你一个字符串 s，请你对 s 的子串进行检测。
        每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[right]，并从中选择 最多 k 项替换成任何小写英文字母。
        如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。
        返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。
        每次检测都是独立的。
         */
        /*
        [left, right] 区间的字符 可以随意排列顺序，只需要要看区间字符的数量情况。
            如果区间的所有字符数量都是偶数，那么不用替换字符，也能变成回文串。
            如果区间中有 1 种字符数量是奇数，那么不用替换，只需要将单个奇数字符放中间
            如果区间中有个 2 种字符数量是奇数，两种字符互相替换 1 次
        字符数量前缀和快速计算字符数量
         */
        /*
        只考虑单个字符的奇偶性，可以使用异或运算来压缩
         */
        int n = s.length();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int bit = s.charAt(i) - 'a';
            sum[i + 1] = sum[i] ^ (1 << bit);
        }
        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2];
            // 1 bit 数量就是奇数数量
            int m = Integer.bitCount(sum[r + 1] ^ sum[l]);
            ans.add(m / 2 <= k);
        }

        return ans;
    }

}
