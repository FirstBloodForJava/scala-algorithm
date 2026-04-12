package com.oycm.algorithm.i.subset;

import java.util.List;

public class Solution_8 {

    /**
     * 1239. <a href="https://leetcode.cn/problems/maximum-length-of-a-concatenated-string-with-unique-characters/description/">串联字符串的最大长度</a> 1720
     *
     * @param arr 字符串数组
     * @return 返回所有可行解 s 中最长长度
     */
    public int maxLength(List<String> arr) {
        /*
        字符串 s 是将 arr 的含有 不同字母 的 子序列 字符串 连接 所得的字符串
         */
        /*
        不能有相同字母
        // todo 可位运算优化
         */
        dfs(0, arr, new char[26], 0);
        return ans;
    }

    int ans = 0;

    public void dfs(int i, List<String> arr, char[] cs, int length) {
        if (i == arr.size()) {
            ans = Math.max(ans, length);
            return;
        }
        dfs(i + 1, arr, cs, length);
        /*
        判断是否能选 arr[i]
         */
        boolean enable = true;
        for (char c : arr.get(i).toCharArray()) {
            c -= 'a';
            cs[c]++;
            if (cs[c] > 1) {
                enable = false;
            }
        }
        if (enable) {
            dfs(i + 1, arr, cs, length + arr.get(i).length());
        }
        // 回溯
        for (char c : arr.get(i).toCharArray()) {
            c -= 'a';
            cs[c]--;
        }
    }


}
