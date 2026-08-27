package com.oycm.month2026.august;

public class Solution_27 {

    /**
     * 3720. <a href="https://leetcode.cn/problems/lexicographically-smallest-permutation-greater-than-target/description/">大于目标字符串的最小字典序排列</a> 1958
     *
     * @param s
     * @param target
     * @return
     */
    public String lexGreaterPermutation(String s, String target) {
        /*
        给你两个长度均为 n 且仅由小写英文字母组成的字符串 s 和 target。
        返回 s 的 字典序最小的排列，要求该排列 严格 大于 target。如果 s 不存在任何字典序严格大于 target 的排列，则返回一个空字符串。
        如果两个长度相同的字符串 a 和 b 在它们首次出现不同字符的位置上，字符串 a 对应的字母在字母表中出现在 b 对应字母的 后面 ，则字符串 a 字典序严格大于 字符串 b。
        排列 是字符串中所有字符的一种重新排列。
         */
        /*
        贪心思路：
        新字符串记为 s'，要想 s' 字典序最小，第一个 s'[r] > t[r] 的 r 要越大，且 s'[0, r) 等于 t[0, r)。
        具体做法：
        使用长为 26 记录 s 和 t 字符差异，倒序枚举 r
         */
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
            cnt[target.charAt(i) - 'a']--;
        }
        out:
        for (int r = n - 1; r >= 0; r--) {
            int b = target.charAt(r) - 'a';
            // 撤销相等的消耗
            cnt[b]++;
            for (int c : cnt) {
                if (c < 0) {
                    // [0, r-1] 无法相等
                    continue out;
                }
            }
            // [0, r-1] 相等，把 b 增加到最小值 i
            for (int i = b + 1; i < 26; i++) {
                if (cnt[i] == 0) {
                    // 这里 cnt[i] 会小于 0 吗？不好，能到这里，说明 所有的 cnt 都大于等于 0
                    continue;
                }
                StringBuilder ans = new StringBuilder(target.substring(0, r));
                ans.append((char) ('a' + i));
                cnt[i]--;
                // 后面从小到达填字符
                for (int k = 0; k < cnt.length; k++) {
                    for (int j = 0; j < cnt[k]; j++) {
                        ans.append((char) ('a' + k));
                    }
                }
                return ans.toString();
            }


        }

        return "";
    }

}
