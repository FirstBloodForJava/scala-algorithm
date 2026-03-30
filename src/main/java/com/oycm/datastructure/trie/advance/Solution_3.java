package com.oycm.datastructure.trie.advance;

import java.util.*;

public class Solution_3 {

    /**
     * 212. <a href="https://leetcode.cn/problems/word-search-ii/description/">单词搜索 II</a>
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        /*
        网格图 dfs 搜索
         */
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public boolean exist(char[][] board, String word) {
        // 统计字符出现的次数
        int[] cnt = new int[128];
        for (char[] row : board) {
            for (char c : row) {
                cnt[c]++;
            }
        }

        // 优化一: word 中字符数不能超过矩阵中的字符数
        char[] w = word.toCharArray();
        int[] wordCnt = new int[128];
        for (char c : w) {
            if (++wordCnt[c] > cnt[c]) {
                return false;
            }
        }

        // 优化二: 从数量较少的字母开始搜索
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

    private boolean dfs(int i, int j, int k, char[][] board, char[] word) {
        if (board[i][j] != word[k]) {
            return false;
        }
        if (k == word.length - 1) {
            return true;
        }
        board[i][j] = 0; // 标记访问过
        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1]; // 相邻格子
            if (0 <= x && x < board.length && 0 <= y && y < board[x].length && dfs(x, y, k + 1, board, word)) {
                // 回溯
                board[i][j] = word[k];
                return true; // 搜到了！
            }
        }
        // 回溯
        board[i][j] = word[k]; // 恢复现场
        return false; // 没搜到
    }



    static class Trie {
        String word;
        boolean end;
        Map<Character,Trie> son = new HashMap<>();

        public void insert(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                cur = cur.son.computeIfAbsent(c, o -> new Trie());
            }
            cur.end = true;
            cur.word = word;
        }
    }

    static class Solution_3_1 {
        public List<String> findWords(char[][] board, String[] words) {
            /*
            矩阵 dfs 搜索 看是否在 字典树与之匹配
             */
            List<String> ans = new ArrayList<>();
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs(i, j, board, trie,ans);
                }
            }
            return ans;
        }

        public void dfs(int i, int j, char[][] board, Trie cur, List<String> ans) {
            if (!cur.son.containsKey(board[i][j])) {
                return;
            }
            char original = board[i][j];
            Trie next = cur.son.get(original);
            if (next.end) {
                ans.add(next.word);
                next.end = false;
            }

            board[i][j] = 0;

            for (int[] d : DIRS) {
                int x = i + d[0];
                int y = j + d[1];
                if (0 <= x && x < board.length && 0 <= y && y < board[x].length) {
                    dfs(x, y, board, next, ans);
                }
            }

            board[i][j] = original;
            // 访问到字典树最后一个字符
            if (next.son.isEmpty()) {
                cur.son.remove(original);
            }
        }

    }

}
