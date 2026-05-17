package com.oycm.hot100.permutation;

import java.util.ArrayList;
import java.util.List;

public class Solution_61 {

    /**
     * 131. <a href="https://leetcode.cn/problems/palindrome-partitioning/description/">分割回文串</a>
     *
     * @param s 字符串
     * @return 将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案
     */
    public List<List<String>> partition(String s) {
        /*
        给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串。
        返回 s 所有可能的分割方案。
         */
        /*
        假设 s 的字符之间有个逗号，看这个逗号 选/不选
        i 和 i + 1 之间是否分割，注意：i == n-1 时
         */
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(0, 0, s, path, ans);
        return ans;
    }

    private void dfs(int i, int start, String s, List<String> path, List<List<String>> ans) {
        if (i == s.length()) {
            // 分割完成
            ans.add(new ArrayList<>(path));
            return;
        }

        /*
        不分割，不选 i 和 i+1 之间的逗号
         */
        if (i < s.length() - 1) dfs(i + 1, start, s, path, ans);

        /*
        分割，选 i 和 i+1 之间的逗号（把 s[i] 作为子串的最后一个字符）
        如果 [start, i] 是回文串，考虑后面 [i+1, n-1] 怎么分割
         */
        if (isPalindrome(s, start, i)) {
            path.add(s.substring(start, i + 1));
            // 考虑 i+1 后面的逗号怎么选
            // start=i+1 表示下一个子串从 i+1 开始
            dfs(i + 1, i + 1, s, path, ans);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

}
