package com.oycm.algorithm.i.search;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 3211. <a href="https://leetcode.cn/problems/generate-binary-strings-without-adjacent-zeros/description/">生成不含相邻零的二进制字符串</a> 1353
     *
     * @param n
     * @return
     */
    public List<String> validStrings(int n) {
        /*
        有效 字符串：一个二进制字符串 x 的所有长度为 2 的子字符串中包含 至少 一个 "1"
         */
        /*
        枚举选哪个数
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
