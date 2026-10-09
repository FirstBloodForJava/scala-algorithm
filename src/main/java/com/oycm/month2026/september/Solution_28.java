package com.oycm.month2026.september;

public class Solution_28 {

    /**
     * 1614. <a href="https://leetcode.cn/problems/maximum-nesting-depth-of-the-parentheses/description/">括号的最大嵌套深度</a> 1323
     *
     * @param s
     * @return
     */
    public int maxDepth(String s) {
        /*
        给定 有效括号字符串 s，返回 s 的 嵌套深度。嵌套深度是嵌套括号的 最大 数量。
         */

        int depth = 0;
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth++;
                ans = Math.max(depth, ans);
            } else if (c == ')') {
                depth--;
            }
        }
        return ans;
    }

}
