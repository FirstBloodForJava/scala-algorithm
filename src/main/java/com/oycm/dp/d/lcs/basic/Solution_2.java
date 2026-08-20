package com.oycm.dp.d.lcs.basic;

public class Solution_2 {

    /**
     * 583. <a href="https://leetcode.cn/problems/delete-operation-for-two-strings/description/">两个字符串的删除操作</a>
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        /*
        给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
        每步 可以删除任意一个字符串中的一个字符。
         */
        /*
        找出最长公共子序列长度 n+m - lcs
         */
        int n = word1.length();
        int m = word2.length();
        int[] f = new int[m + 1];
        char[] cs = word2.toCharArray();
        for (char x : word1.toCharArray()) {
            int pre = f[0];
            for (int j = 0; j < m; j++) {
                int temp = f[j + 1];
                if (x == cs[j]) {
                    f[j + 1] = pre + 1;
                } else {
                    // f[j+1] = f[i][j+1]; f[j] = f[i+1][j]
                    f[j + 1] = Math.max(f[j], f[j + 1]);
                }
                pre = temp;
            }
        }

        return n + m - f[m] * 2;
    }

}
