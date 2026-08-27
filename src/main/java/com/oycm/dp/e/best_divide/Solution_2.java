package com.oycm.dp.e.best_divide;

import java.util.HashSet;
import java.util.Set;

public class Solution_2 {

    /**
     * 2707. <a href="https://leetcode.cn/problems/extra-characters-in-a-string/description/">字符串中的额外字符</a> 1736
     *
     * @param s
     * @param dictionary
     * @return
     */
    public int minExtraChar(String s, String[] dictionary) {
        /*
        给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary。
        你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。
        s 中可能会有一些 额外的字符 不在任何子字符串中。请你采取最优策略分割 s ，使剩下的字符 最少 。
         */
        Set<String> set = new HashSet<>(dictionary.length, 1);
        for (String w : dictionary) {
            set.add(w);
        }
        int n = s.length();
        int[] f = new int[n + 1];

        for (int r = 0; r < n; r++) {
            if (set.contains(s.substring(0, r + 1))) {
                continue;
            }
            int res = r + 1;
            for (int l = 1; l <= r; l++) {
                if (set.contains(s.substring(l, r + 1))) {
                    // [l, r] 在 字典中，[0, l-1] 剩余字符数量
                    res = Math.min(res, f[l]);
                } else {
                    // [l, r] 作为剩余数量，[0, l-1] 剩余字符
                    res = Math.min(res, f[l] + r - l + 1);
                }
            }
            f[r + 1] = res;
        }

        return f[n];
    }


}
