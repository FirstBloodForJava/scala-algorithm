package com.leetcode.interview_question.h;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_8 {

    /**
     * 面试题 08.08. <a href="https://leetcode.cn/problems/permutation-ii-lcci/">有重复字符串的排列组合</a>
     *
     * @param S
     * @return
     */
    public String[] permutation(String S) {
        /*
        有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
         */
        char[] cs = S.toCharArray();
        Arrays.sort(cs);
        char[] path = new char[S.length()];
        List<String> ans = new ArrayList<>();
        dfs(0, path, cs, new boolean[S.length()], ans);
        return ans.toArray(new String[0]);
    }

    public void dfs(int i, char[] path, char[] cs, boolean[] onPath, List<String> ans) {
        if (i == path.length) {
            ans.add(new String(path));
            return;
        }

        for (int j = 0; j < path.length; j++) {
            // 剪枝
            if (j > 0 && cs[j - 1] == cs[j] && !onPath[j - 1]) {
                continue;
            }
            if (!onPath[j]) {
                onPath[j] = true;
                path[i] = cs[j];
                dfs(i + 1, path, cs, onPath, ans);
                // 回溯
                onPath[j] = false;
            }
        }
    }

}
