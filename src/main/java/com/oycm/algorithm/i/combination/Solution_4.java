package com.oycm.algorithm.i.combination;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    /**
     * 22. <a href="https://leetcode.cn/problems/generate-parentheses/description/">括号生成</a>
     *
     * @param n [1, 8]
     * @return 生成所有可能的并且 有效的 括号组合
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(0, 0, n, new char[2 * n], ans);
        return ans;
    }

    public void dfs(int i, int open, int n, char[] path, List<String> ans) {
        /*
        枚举填哪个括号
        i 表示填了括号的数量，open 表示左括号数量
        什么时候可以填左括号 open < n
        什么时候可以填右括号, 左括号数量(open) > 右括号数量(i - open)
         */
        if (i == 2 * n) {
            ans.add(new String(path));
            return;
        }
        if (open < n) {
            path[i] = '(';
            dfs(i + 1, open + 1, n, path, ans);
        }
        if (open > i - open) {
            path[i] = ')';
            dfs(i + 1, open, n, path, ans);
        }
    }

}
