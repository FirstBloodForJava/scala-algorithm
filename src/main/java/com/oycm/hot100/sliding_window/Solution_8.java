package com.oycm.hot100.sliding_window;

import java.util.ArrayList;
import java.util.List;

public class Solution_8 {

    /**
     * 438. <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/">找到字符串中所有字母异位词</a>
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        /*
        异位词：字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。
        给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
         */
        /*
        p 长度记为 m
        s 长为 m 的子串，是否含有和 p 相同的字符数量
        定长滑动窗口
         */
        int n = s.length(), m = p.length();
        int[] cnt = new int[26];
        // 不同字符数量
        int k = 0;
        for (char c : p.toCharArray()) {
            c -= 'a';
            if (cnt[c] == 0) {
                k++;
            }
            cnt[c]++;
        }
        List<Integer> ans = new ArrayList<>();
        if (m > n) {
            return ans;
        }
        char[] cs = s.toCharArray();
        int l = 0;
        for (int r = 0; r < n; r++) {
            int c = cs[r] - 'a';
            cnt[c]--;
            if (cnt[c] == 0) {
                k--;
            }
            if (r - l + 1 == m) {
                if (k == 0) {
                    ans.add(l);
                }
                c = cs[l] - 'a';
                if (cnt[c] == 0) {
                    k++;
                }
                cnt[c]++;
                l++;
            }
        }

        return ans;
    }

    public List<Integer> findAnagrams_2(String s, String p) {
        /*
        可以不使用变量 k，使用计数数组来当作计数使用
         */
        int n = s.length(), m = p.length();
        int[] cnt = new int[26];
        for (char c : p.toCharArray()) {
            cnt[c - 'a']++;
        }
        List<Integer> ans = new ArrayList<>();
        char[] cs = s.toCharArray();
        int l = 0;
        for (int r = 0; r < n; r++) {
            int c = cs[r] - 'a';
            cnt[c]--;
            while (cnt[c] < 0) {
                // 要么 c 是非 p 中字符，或者 p 中字符出现多了
                cnt[cs[l++] - 'a']++;
            }
            if (r - l + 1 == m) {
                ans.add(l);
            }
        }

        return ans;
    }

}
