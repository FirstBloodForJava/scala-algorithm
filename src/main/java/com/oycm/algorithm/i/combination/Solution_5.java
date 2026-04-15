package com.oycm.algorithm.i.combination;

import java.util.ArrayList;
import java.util.List;

public class Solution_5 {

    /**
     * 301. <a href="https://leetcode.cn/problems/remove-invalid-parentheses/description/">删除无效的括号</a>
     *
     * @param s 由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效
     * @return 返回所有可能的结果
     */
    public List<String> removeInvalidParentheses(String s) {
        /*
        题解思路：先计算括号最小删除数量
        枚举去掉括号后的字符串，字符和括号符合要求，则记录答案
         */
        int lr = 0, rr = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == '(') {
                lr++;
            } else if (c == ')') {
                if (lr == 0) {
                    rr++;
                } else {
                    lr--;
                }
            }
        }
        List<String> ans = new ArrayList<>();
        dfs(0, s, lr, rr, ans);
        return ans;
    }

    public void dfs(int start, String str, int lr, int rr, List<String> ans) {
        if (lr == rr && lr == 0) {
            if (isValid(str)) {
                ans.add(str);
            }
            return;
        }

        for (int i = start; i <= str.length() - lr - rr; i++) {
            // ((( / ))) 去掉的遍历是一样的
            if (i != start && str.charAt(i) == str.charAt(i - 1)) {
                continue;
            }

            if (lr > 0 && str.charAt(i) == '(') {
                dfs(i, str.substring(0, i) + str.substring(i + 1), lr - 1, rr, ans);
            }

            if (rr > 0 && str.charAt(i) == ')') {
                dfs(i, str.substring(0, i) + str.substring(i + 1), lr, rr - 1, ans);
            }
        }

    }

    public boolean isValid(String s) {
        int lCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                lCnt++;
            } else if (c == ')') {
                lCnt--;
                if (lCnt < 0) {
                    return false;
                }
            }
        }
        return lCnt == 0;
    }


}
