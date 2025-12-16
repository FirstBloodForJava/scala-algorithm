package com.oycm.algorithm.h.basic;


import java.util.ArrayList;
import java.util.List;

public class Solution_20 {

    /**
     * 3211. <a href="https://leetcode.cn/problems/generate-binary-strings-without-adjacent-zeros/description/">生成不含相邻零的二进制字符串</a> 1353
     * <p>
     * 有效字符串：一个二进制字符串 x 的所有长度为 2 的子字符串中包含至少一个 1，x 则是有效字符串
     *
     * @param n
     * @return
     */
    public List<String> validStrings(int n) {
        /*
        字符串中连续 0 的数量不能 大于等于 2
        题解思路：回溯
         */
        List<String> ans = new ArrayList<>();
        char[] cs = new char[n];
        dfs(0, n, cs, ans);
        return ans;
    }

    private void dfs(int i, int n, char[] cs, List<String> ans) {
        if (i == n) {
            ans.add(new String(cs));
            return;
        }
        // 填 1
        cs[i] = '1';
        dfs(i + 1, n, cs, ans);

        // 填 0
        if (i == 0 || cs[i - 1] == '1') {
            cs[i] = '0';
            dfs(i + 1, n, cs, ans);
        }
    }
}
