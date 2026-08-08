package com.leetcode.interview_question.h;

import java.util.ArrayList;
import java.util.List;

public class Solution_7 {

    /**
     * 面试题 08.07. <a href="https://leetcode.cn/problems/permutation-i-lcci/description/">无重复字符串的排列组合</a>
     *
     * @param S
     * @return
     */
    public String[] permutation(String S) {
        /*
        无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
         */
        /*
        全排列
         */
        char[] path = new char[S.length()];
        List<String> ans = new ArrayList<>();
        dfs(0, path, S.toCharArray(), new boolean[S.length()], ans);
        return ans.toArray(new String[0]);
    }

    public void dfs(int i, char[] path, char[] cs, boolean[] onPath, List<String> ans) {
        if (i == path.length) {
            ans.add(new String(path));
            return;
        }

        for (int j = 0; j < path.length; j++) {
            if (!onPath[j]) {
                onPath[j] = true;
                path[i] = cs[j];
                dfs(i + 1, path, cs, onPath, ans);
                // 回溯
                onPath[i] = false;
            }
        }
    }
}
