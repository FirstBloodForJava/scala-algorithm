package com.oycm.hot100.permutation;

public class Solution_60 {

    /**
     * 79. <a href="https://leetcode.cn/problems/word-search/description/">单词搜索</a>
     *
     * @param board m x n 二维字符网格, board[i][j] 只有大小写字母
     * @param word
     * @return word 是否存在于网格图中
     */
    public boolean exist(char[][] board, String word) {
        /*
        给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false。
        单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
        同一个单元格内的字母不允许被重复使用。
         */
        /*
        dfs 搜索 (i, j) 作为起点，查找是否存在一条路径的组成的单词时 word，
        由于用一个单元格不能重复访问，需要标记已经访问的坐标，这里我们可以通过将 board[i][j] 标记一个不可能和 word[i] 匹配的字符，这样就不会走到这个点
        优化点：
            先统计 word 中字符数量情况，如果 board 的字符数量小于 word 需要的字符数量，则直接返回 false
            先从数量少的起点开始查找，word[0] 和 word[n-1] 字符出现的数量统计，如果 word[n-1] 字符数量小于 word[0] 可以反转字符 word 进行查询，这样递归次数和深度都会较少点
         */
        int[] cnt = new int[128];
        char[] cs = word.toCharArray();

        int[] boardCnt = new int[128];
        for (char[] row : board) {
            for (char c : row) {
                boardCnt[c]++;
            }
        }
        for (char c : cs) {
            if (++cnt[c] > boardCnt[c]) return false;
        }

        if (cnt[cs[word.length() - 1]] > cnt[cs[0]]) {
            cs = new StringBuilder(word).reverse().toString().toCharArray();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(i, j, 0, board, cs)) return true;
            }
        }

        return false;
    }

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public boolean dfs(int i, int j, int k, char[][] board, char[] cs) {
        if (board[i][j] != cs[k]) {
            return false;
        }
        if (k == cs.length - 1) {
            return true;
        }
        // 标记访问
        board[i][j] = 0;
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && dfs(x, y, k + 1, board, cs)) {
                // 找打答案也回溯
                board[i][j] = cs[k];
                return true;
            }
        }
        // 回溯
        board[i][j] = cs[k];
        return false;
    }

}
