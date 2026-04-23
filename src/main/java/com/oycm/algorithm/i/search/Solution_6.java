package com.oycm.algorithm.i.search;

public class Solution_6 {

    /**
     * 79. <a href="https://leetcode.cn/problems/word-search/description/">单词搜索</a>
     *
     * @param board m x n 二维字符网格, board[i][j] 只是大小写字母
     * @param word
     * @return word 是否存在于网格图中
     */
    public boolean exist(char[][] board, String word) {
        /*
        优化思路：
            字符数量
            搜索顺序：第一个字母数量大于最后一个字母数量，意味着从 根节点进入递归的次数就比较多
         */
        // 统计字符出现的次数
        int[] cnt = new int[128];
        for (char[] row : board) {
            for (char c : row) {
                cnt[c]++;
            }
        }
        char[] w = word.toCharArray();
        int[] wordCnt = new int[128];
        for (char c : w) {
            if (++wordCnt[c] > cnt[c]) {
                return false;
            }
        }

        if (cnt[w[w.length - 1]] < cnt[w[0]]) {
            w = new StringBuilder(word).reverse().toString().toCharArray();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(i, j, 0, board, w)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private boolean dfs(int i, int j, int k, char[][] board, char[] word) {
        if (board[i][j] != word[k]) {
            return false;
        }
        if (k == word.length - 1) {
            return true;
        }
        board[i][j] = 0;
        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1];
            if (0 <= x && x < board.length && 0 <= y && y < board[x].length && dfs(x, y, k + 1, board, word)) {
                // 回溯
                board[i][j] = word[k];
                return true;
            }
        }
        // 恢复现场
        board[i][j] = word[k];
        return false;
    }

}
