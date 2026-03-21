package com.oycm.algorithm.ab.min;

public class Solution_5 {

    /**
     * 76. <a href="https://leetcode.cn/problems/minimum-window-substring/description/">最小覆盖子串</a>
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        /*
        初始化 t 在窗口中的字符数(用负数表示), 以及不同字母数 kinds

        在窗口中遍历 s 的字符, c 入窗, diff[c]++
            如果 diff[c] 变成 0, curKinds++

        当 curKinds == kinds 开始缩短窗口, c 出窗
            right - left 找 最短
            如果 diff[c] = 0（判断要在 diff[c]-- 之前）, curKinds-- 数量减少
         */

        int[] diff = new int[128];
        int kinds = 0;
        for (char c : t.toCharArray()) {
            if (diff[c] == 0) {
                kinds++;
            }
            diff[c]--;
        }
        char[] cs = s.toCharArray();
        int m = s.length();
        int ansLeft = -1, ansRight = m;

        int curKinds = 0;
        int left = 0;

        for (int right = 0; right < m; right++) { // 移动子串右端点
            char c = cs[right];
            // 右端点入窗口
            diff[c]++;
            if (diff[c] == 0) {
                curKinds++;
            }

            // 字符数相等时, 开始移除左端点的字符
            while (curKinds == kinds) {
                if (right - left < ansRight - ansLeft) {
                    ansLeft = left;
                    ansRight = right;
                }

                char x = cs[left];
                if (diff[x] == 0) {
                    curKinds--;
                }
                diff[x]--;
                left++;
            }
        }

        return ansLeft < 0 ? "" : s.substring(ansLeft, ansRight + 1);
    }

}
