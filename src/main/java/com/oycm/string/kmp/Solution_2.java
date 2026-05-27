package com.oycm.string.kmp;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 796. <a href="https://leetcode.cn/problems/rotate-string/">旋转字符串</a> 1167
     *
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString(String s, String goal) {
        /*
        给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
        s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
        例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
         */
        /*
        本质是判断 goal 是否为 s+s 的子串
         */
        return s.length() == goal.length() && !kmpSearch((s + s).toCharArray(), goal.toCharArray()).isEmpty();
    }

    private List<Integer> kmpSearch(char[] t, char[] pattern) {
        List<Integer> pos = new ArrayList<>();
        int m = pattern.length;
        int[] next = new int[m];
        int cnt = 0;
        for (int i = 1; i < m; i++) {
            while (cnt > 0 && pattern[i] != pattern[cnt]) {
                cnt = next[cnt - 1];
            }
            if (pattern[i] == pattern[cnt]) {
                cnt++;
            }
            next[i] = cnt;
        }

        for (int i = 0, j = 0; i < t.length; i++) {
            while (j > 0 && t[i] != pattern[j]) {
                j = next[j - 1];
            }
            if (t[i] == pattern[j]) {
                j++;
            }
            if (j == m) {
                // i - pre + 1 = m = j
                pos.add(i - j + 1);
                j = next[j - 1];
            }
        }

        return pos;
    }

}
