package com.oycm.hot100.dp;

public class Solution_90 {

    /**
     * 32. <a href="https://leetcode.cn/problems/longest-valid-parentheses/description/">最长有效括号</a>
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        /*
        给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号 子串 的长度。
         */
        /*
        ((()))) 连续字符串只要 左括号数量 >= 右括号数量，后面左右括号数量相等时，就是一个正确括号
        [1, 1, 1, -1, -1, -1, -1]
        使用前缀和，定义 sums[i] 表示前 i 个元素和，hash 表记录前面前缀和 及下标
        计算 sums[i] 后查找前面最远和 sums[i] 相等的 key，两个下标差就是符合条件的括号字符串
        如果 sums[i] 出现了小于 0 的情况，则后面的括号不可能和前面的组成有效括号，清空 map，标记 sum 和为 0
         */
        /*
        栈 邻项消除，标记一段区域为合法括号，然后再遍历数组，找最长连续合法括号长度
         */
        int ans = 0;
        int n = s.length();
        char[] cs = s.toCharArray();
        boolean[] valid = new boolean[n];
        int j = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '(') {
                cs[j++] = (char) i;
            } else if (j > 0) {
                int left = cs[--j];
                valid[left] = valid[i] = true;
            }
        }
        int cnt = 0;
        for (boolean b : valid) {
            if (b) {
                cnt++;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 0;
            }
        }

        return Math.max(cnt, ans);
    }

}
