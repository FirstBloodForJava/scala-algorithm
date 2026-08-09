package com.leetcode.interview_question.h;

import java.util.ArrayList;
import java.util.List;

public class Solution_9 {

    /**
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        /*
        括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
        说明：解集不能包含重复的子集。
         */
        this.n = n;
        path = new char[2 * n];
        ans = new ArrayList<>();

        return ans;
    }

    private int n;
    private char[] path;
    private List<String> ans;

    public void dfs(int i, int left) {
        if (i == 2 * n) {
            ans.add(new String(path));
            return;
        }
        if (left < n) {
            path[i] = '(';
            dfs(i + 1, left + 1);
        }
        if (i - left < left) {
            // 右括号数量小于左括号
            path[i] = ')';
            dfs(i + 1, left);
        }
    }
}
