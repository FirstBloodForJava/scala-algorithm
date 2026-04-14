package com.oycm.algorithm.i.divide;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_6 {


    /**
     * 140. <a href="https://leetcode.cn/problems/word-break-ii/description/">单词拆分 II</a>
     * <p>
     * 词典中的同一个单词可能在分段中被重复使用多次
     *
     * @param s        字符串
     * @param wordDict 字符串数组
     * @return 字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> ans = new ArrayList<>();
        dfs(0, s, new ArrayList<>(), ans, set);
        return ans;
    }

    public void dfs(int i, String s, List<String> path, List<String> ans, Set<String> set) {
        if (i == s.length()) {
            ans.add(String.join(" ", path));
            return;
        }

        for (int j = i; j < s.length(); j++) {
            String sub = s.substring(i, j + 1);
            // 单词在
            if (set.contains(sub)) {
                path.add(sub);
                dfs(j + 1, s, path, ans, set);
                path.remove(path.size() - 1);
            }
        }


    }



}
