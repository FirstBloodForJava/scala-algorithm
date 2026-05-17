package com.oycm.hot100.permutation;

import java.util.ArrayList;
import java.util.List;

public class Solution_57 {

    /**
     * 17. <a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/">电话号码的字母组合</a>
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        /*
        给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
         */
        List<String> ans = new ArrayList<>();
        dfs(0, digits.toCharArray(), new char[digits.length()], ans);
        return ans;
    }
    public static final String[] MAPPING = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public void dfs(int i, char[] cs, char[] path, List<String> ans) {
        if (i == cs.length) {
            ans.add(new String(path));
            return;
        }
        for (char c : MAPPING[cs[i] - '2'].toCharArray()) {
            path[i] = c;
            dfs(i + 1, cs, path, ans);
        }
    }

}
