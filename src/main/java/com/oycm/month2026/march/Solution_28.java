package com.oycm.month2026.march;

public class Solution_28 {

    /**
     * 2573. <a href="https://leetcode.cn/problems/find-the-string-with-lcp/description/">找出对应 LCP 矩阵的字符串</a> 2682
     *
     * @param lcp n x n 的矩阵
     * @return 求满足条件的 最小的字符串 word
     */
    public String findTheString(int[][] lcp) {
        /*
        n 个小写英文字母组成的字符串 word 和 lcp n x n 的矩阵, 存在以下关系:
            lcp[i][j] 等于子字符串 word[i,...,n-1] 和 word[j,...,n-1] 之间的最长公共前缀的长度
         */
        int n = lcp.length;
        if (n != lcp[0][0]) return "";
        char[] word = new char[n];
        int i = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            for (int j = i; j < n; j++) {
                // word[i] == word[j]
                if (lcp[i][j] > 0) word[j] = c;
            }
            // 填下一位
            while (i < n && word[i] > 0) {
                i++;
            }
            // 没有空位
            if (i == n) {
                break;
            }
        }
        if (i < n) {
            return "";
        }
        // 验证 lcp
        for (i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int curLcp = word[i] != word[j] ? 0 : (i == n - 1 || j == n - 1 ? 1 : lcp[i + 1][j + 1] + 1);
                if (curLcp != lcp[i][j]) {
                    return "";
                }
            }
        }
        return new String(word);
    }

}
