package com.oycm.algorithm.i.permutation;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_9_37 {

    /**
     * 37. <a href="https://leetcode.cn/problems/sudoku-solver/">解数独</a>
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        /*
        先标记数独中有哪些数填了，遍历所有的空位
         */
        boolean[][] rowsValid = new boolean[9][9];
        boolean[][] colsValid = new boolean[9][9];
        boolean[][][] subBoard = new boolean[3][3][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int k = c - '1';
                // 标记为 true
                rowsValid[i][k] = colsValid[j][k] = subBoard[i / 3][j / 3][k] = true;
            }
        }
        dfs(board, rowsValid, colsValid, subBoard, 0, 0);
    }

    public boolean dfs(char[][] board, boolean[][] rowsValid, boolean[][] colsValid, boolean[][][] subBoard, int x, int y) {

        while (x < 9 && y < 9 && board[x][y] != '.') {
            y++;
            if (y == 9) {
                x++;
                y = 0;
            }
        }
        if (x == 9 || y == 9) {
            return true;
        }
        for (int i = 0; i < 9; i++) {
            // 都未填 i
            if (!rowsValid[x][i] && !colsValid[y][i] && !subBoard[x / 3][y / 3][i]) {
                rowsValid[x][i] = colsValid[y][i] = subBoard[x / 3][y / 3][i] = true;
                board[x][y] = (char) ('1' + i);
                if (dfs(board, rowsValid, colsValid, subBoard, y == 8 ? x + 1 : x, y == 8 ? 0 : y + 1)) {
                    return true;
                }
                // 回溯
                rowsValid[x][i] = colsValid[y][i] = subBoard[x / 3][y / 3][i] = false;
                board[x][y] = '.';
            }

        }

        return false;
    }

}

class Solution_9_37_2 {

    public void solveSudoku(char[][] board) {
        boolean[][] rowHas = new boolean[9][9];
        boolean[][] colHas = new boolean[9][9];
        boolean[][][] subBoxHas = new boolean[3][3][9];
        // 需要填的位置
        List<int[]> emptyPos = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    emptyPos.add(new int[]{i, j}); // 记录空格子的位置
                } else {
                    int x = board[i][j] - '1';
                    rowHas[i][x] = colHas[j][x] = subBoxHas[i / 3][j / 3][x] = true;
                }
            }
        }

        PriorityQueue<int[]> emptyPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] pos : emptyPos) {
            int i = pos[0];
            int j = pos[1];
            int candidates = getCandidates(i, j, rowHas, colHas, subBoxHas);
            emptyPQ.offer(new int[]{candidates, i, j}); // 待定数字个数最少的在堆顶
        }

        dfs(board, rowHas, colHas, subBoxHas, emptyPQ);
    }

    // 计算 (i, j) 这个空格子的待定数字个数
    private int getCandidates(int i, int j, boolean[][] rowHas, boolean[][] colHas, boolean[][][] subBoxHas) {
        int candidates = 9;
        for (int x = 0; x < 9; x++) {
            if (rowHas[i][x] || colHas[j][x] || subBoxHas[i / 3][j / 3][x]) {
                candidates--;
            }
        }
        return candidates;
    }

    // 每次递归，选一个空格子，枚举填入的数字
    private boolean dfs(char[][] board, boolean[][] rowHas, boolean[][] colHas, boolean[][][] subBoxHas, PriorityQueue<int[]> emptyPQ) {
        if (emptyPQ.isEmpty()) { // 所有格子都已填入数字
            return true; // 完成数独
        }

        // 数独玩法：优先考虑待定数字个数最少的空格子
        int[] top = emptyPQ.poll();
        int i = top[1];
        int j = top[2];

        int candidates = 0; // 受之前填入的数字影响，实际待定数字个数可能比入堆时的少，需要重新计算
        // 枚举没填过的数字 x，填入 board[i][j]
        for (int x = 0; x < 9; x++) {
            if (rowHas[i][x] || colHas[j][x] || subBoxHas[i / 3][j / 3][x]) {
                continue; // x 填过了
            }

            // 填入 board[i][j]
            board[i][j] = (char) ('1' + x);
            // 标记行、列、宫包含数字 x
            rowHas[i][x] = colHas[j][x] = subBoxHas[i / 3][j / 3][x] = true;

            // 填下一个空格子
            if (dfs(board, rowHas, colHas, subBoxHas, emptyPQ)) {
                return true; // 完成数独
            }

            // 恢复现场（撤销）
            // 注意 board[i][j] 无需恢复现场，因为我们会直接覆盖掉之前填入的数字
            rowHas[i][x] = colHas[j][x] = subBoxHas[i / 3][j / 3][x] = false;
            // 统计待定数字个数
            candidates++;
        }

        // 回溯
        emptyPQ.offer(new int[]{candidates, i, j});
        return false;
    }
}

class Solution_9__36 {

    /**
     * 36. <a href="https://leetcode.cn/problems/valid-sudoku/description/">有效的数独</a>
     *
     * @param board
     * @return 判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     */
    public boolean isValidSudoku(char[][] board) {
        /*
        数字 1-9 在每一行只能出现一次
        数字 1-9 在每一列只能出现一次。
        数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
         */
        /*
        每行每列可以通过记录数字出现的次数来判断
        怎么判断 3 x 3 宫是否符合要求？
            (0,0), (0,1), (0,2)
            (1,0), (1,1), (1,2)
            (2,0), (2,1), (2,2)
            这样 3 x 3 的 子矩阵看成 [i/3][j/3][] 3 x 3 x 9 来表示是否存在
         */
        boolean[][] rowsValid = new boolean[9][9];
        boolean[][] colsValid = new boolean[9][9];
        boolean[][][] subBoard = new boolean[3][3][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int k = c - '1';
                if (rowsValid[i][k] || colsValid[j][k] || subBoard[i / 3][j / 3][k]) {
                    return false;
                }
                // 标记为 true
                rowsValid[i][k] = colsValid[j][k] = subBoard[i / 3][j / 3][k] = true;
            }
        }

        return true;
    }

}
