package com.oycm.algorithm.abc.min;


public class Solution_6 {

    /**
     * 3298. <a href="https://leetcode.cn/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-ii/description/">统计重新排列后包含另一个字符串的子字符串数目 II</a> 1909
     *
     * @param word1
     * @param word2
     * @return word1 子串中重新排列后是 word2 前缀的数量
     */
    public static long validSubstringCount(String word1, String word2) {
        /*
        找到 word1 中最短的字符串包含 word2 所有字符，[l, r] 包含所有 word2 字符，以 l 开始的子串方案数 = n - r
        int[] 数组记录 word2 中字符的数量，cnt2 记录不同字符数
        固定 r，求 l 的最大值
        固定 l，求 r 的最小值
         */
        int[] words = new int[26];

        int cnt = 0;
        for (char c : word2.toCharArray()) {
            int i = c - 'a';
            if (words[i] == 0) {
                cnt++;
            }
            words[i]++;

        }
        long ans = 0;
        int l = 0, r = 0, n = word1.length();
        char[] chars = word1.toCharArray();
        while (l < n) {
            while (r < n && cnt > 0) {
                int i = chars[r] - 'a';
                words[i]--;
                if (words[i] == 0) {
                    cnt--;
                }
                r++;
            }
            // 要判断是否符合条件，否则需要在 内存循环加判断，cnt == 0 时，r 先不自增
            if (cnt == 0) {
                ans += n - r + 1;
            }

            // 出, 必须是 word2 中的字符才 自增
            int i = chars[l] - 'a';
            if (words[i] == 0) {
                cnt++;
            }
            words[i]++;
            l++;
        }

        return ans;
    }

    public static long fixedRight(String word1, String word2) {
        int[] words = new int[26];
        int cnt = 0;
        for (char c : word2.toCharArray()) {
            int i = c - 'a';
            if (words[i] == 0) {
                cnt++;
            }
            words[i]++;
        }

        long ans = 0;
        char[] chars = word1.toCharArray();
        /*
        固定 r 求 每次 l 的最大值
         */
        int l = 0;
        for (char c : chars) {
            int i = c - 'a';
            words[i]--;
            if (words[i] == 0) {
                cnt--;
            }
            while (cnt == 0) {
                // 不在 word2 的数量是始终 < 0 的，删除之后才会等于 0
                int j = chars[l++] - 'a';
                if (words[j] == 0) {
                    cnt++;
                }
                words[j]++;
            }
            ans += l;
        }

        return ans;
    }

    public static void main(String[] args) {
        validSubstringCount("bcca", "abc");
        fixedRight("bcca", "abc");
    }
}
