package com.oycm.hot100.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_59 {

    /**
     * 22. <a href="https://leetcode.cn/problems/generate-parentheses/description/">括号生成</a>
     *
     * @param n [1, 8]
     * @return 生成所有可能的并且 有效的 括号组合
     */
    public List<String> generateParenthesis(int n) {
        /*
        数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
         */
        /*
        枚举下一个左括号的位置：
        从左往右填的过程中，要时刻保证右括号的数量不能超过左括号的数量 r - l <= 0, l - r >= 0
        递归过程中，记录填了 i 个括号，balance 表示 左右括号数量之差( >= 0)，path 记录填左括号下标

         */
        List<String> ans = new ArrayList<>();
        dfs(0, 0, n, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(int i, int balance, int n, List<Integer> path, List<String> ans) {
        if (path.size() == n) {
            char[] cs = new char[n * 2];
            Arrays.fill(cs, ')');
            for (int j : path) {
                cs[j] = '(';
            }
            ans.add(new String(cs));
            return;
        }
        for (int right = 0; right <= balance; right++) {
            // 先填 right 个右括号，再填一个左括号， i+right 表示当前填的左括号下标
            // (i, i+right) 直接填右括号
            path.add(i + right);
            dfs(i + right + 1, balance - right + 1, n, path, ans);
            // 回溯
            path.remove(path.size() - 1);
        }
    }

    public List<String> generateParenthesis_1(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[2 * n];
        dfs(0, 0, n, path, ans);
        return ans;
    }

    public void dfs(int left, int right, int n, char[] path, List<String> ans) {
        if (right == n) {
            ans.add(new String(path));
            return;
        }
        // 选 (
        if (left < n) {
            path[left + right] = '(';
            dfs(left + 1, right, n, path, ans);
        }
        // 不选 (，选 )
        if (right < left) {
            path[left + right] = ')';
            dfs(left, right + 1, n, path, ans);
        }
    }


}
