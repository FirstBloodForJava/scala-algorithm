package com.oycm.algorithm.i.permutation;

public class Solution_9 {
}

class Solution_9_1 {

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
                if (rowsValid[i][k] || colsValid[j][k] || subBoard[i/3][j/3][k]) {
                    return false;
                }
                // 标记为 true
                rowsValid[i][k] = colsValid[j][k] = subBoard[i/3][j/3][k] = true;
            }
        }
        
        return true;
    }

}
