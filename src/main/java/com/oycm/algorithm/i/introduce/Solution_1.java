package com.oycm.algorithm.i.introduce;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 17. <a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/">电话号码的字母组合</a>
     *
     * @param digits 仅包含数字 2-9 的字符串
     * @return 返回所有它能表示的字母组合
     */
    public List<String> letterCombinations(String digits) {
        /*
        构造一个长为 n 的字符
        选第 1 个字符，剩下 n-1 个还未选好
        选第 2 个字符，剩下 n-2 个还未选好
        ...
        选第 n 个字符，都选好了记录答案
         */
        int n = digits.length();
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        char[] path = new char[n];
        dfs(0, n, path, ans, digits);
        return ans;
    }

    public static final String[] MAPPING = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public void dfs(int i, int n, char[] path, List<String> ans, String digits) {
        if (i == n) {
            ans.add(new String(path));
            return;
        }
        for (char c : MAPPING[digits.charAt(i) - '2'].toCharArray()) {
            path[i] = c;
            // 填下一个
            dfs(i + 1, n, path, ans, digits);
        }

    }

}
