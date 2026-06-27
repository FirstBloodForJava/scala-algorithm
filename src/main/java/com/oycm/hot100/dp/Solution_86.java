package com.oycm.hot100.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_86 {

    /**
     * 139. <a href="https://leetcode.cn/problems/word-break/description/">单词拆分</a>
     *
     * @param s        1 <= s.length <= 300
     * @param wordDict 1 <= wordDict.length <= 1000; 1 <= wordDict[i].length <= 20
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        /*
        给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
         */
        /*
        对于 wordDict[i] 单词，如果要选，则 s 一定要包含 wordDict[i]，不包含，则一定不能选
         */
        /*
        题解思路：由于 wordDict 中数量较多，且长度较短，应该枚举 s，看 s 是否能划分成小段，使得每段都在 wordDict 中
         */
        // 计算 wordDict 中最长的字符串，如果 s 中最长为 maxLen 的字符串不在单词中，maxLen + 1 也一定不存在
        int maxLen = 0;
        for (String w : wordDict) {
            maxLen = Math.max(maxLen, w.length());
        }
        Set<String> words = new HashSet<>(wordDict);
        int n = s.length();
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n, maxLen, s, words, memo) == 1;
    }

    private int dfs(int i, int maxLen, String s, Set<String> words, int[] memo) {
        if (i == 0) { // 成功拆分！
            return 1;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
            if (words.contains(s.substring(j, i)) && dfs(j, maxLen, s, words, memo) == 1) {
                return memo[i] = 1; // 记忆化
            }
        }
        return memo[i] = 0; // 记忆化
    }


    public boolean dfs(int i, String s, List<String> wordDict) {

        if (i < 0) return s.isEmpty();
        String w = wordDict.get(i);
        int j = s.indexOf(w);
        if (j < 0) return dfs(i - 1, s, wordDict);
        // 选，从 s 中移除 wordDict[i]
        String pre = s.substring(0, j);
        String suf = s.substring(j + w.length());
        return dfs(i - 1, s, wordDict) || (dfs(i, pre, wordDict) && dfs(i, suf, wordDict));
    }

    public boolean wordBreak_dp(String s, List<String> wordDict) {
        int maxLen = 0;
        for (String w : wordDict) {
            maxLen = Math.max(maxLen, w.length());
        }
        Set<String> words = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        // [0, i) 拆分 [j , i) 的条件是前面的能匹配
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
                if (f[j] && words.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[n];
    }

}
