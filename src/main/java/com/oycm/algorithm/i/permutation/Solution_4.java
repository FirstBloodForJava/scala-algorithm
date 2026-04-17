package com.oycm.algorithm.i.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_4 {

    /**
     * 51. <a href="https://leetcode.cn/problems/n-queens/description/">N 皇后</a>
     * <p>
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     *
     * @param n [1, 9]
     * @return 返回所有不同的 n 皇后问题 的解决方案。方案中 'Q' 和 '.' 分别代表了皇后和空位。
     */
    public List<List<String>> solveNQueens(int n) {
        /*
        n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
         */
        /*
        题解思路：由于每行恰好放一个皇后，记录每行的皇后放在哪一列，可以得到 [0, n-1] 的全排列
        接下来就是如何判断斜向方向，两个皇后是否互相攻击?
        (0,0), (0,1), (0,2), (0,3)
        (1,0), (1,1), (1,2), (1,3)
        (2,0), (2,1), (2,2), (2,3)
        (3,0), (3,1), (3,2), (3,3)
        ↘这条斜线 坐标差是不变的; ↗这条斜线上的和是不变的
        因为 ↘ 对应 y = x + b 函数
             ↗ 对应 y = -x + b 函数，所以存在坐标和，坐标查等于固定的值
         */
        List<List<String>> ans = new ArrayList<>();
        // 皇后的位置 (i, queens[i])
        int[] queens = new int[n];

        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[n * 2 - 1];
        boolean[] diag2 = new boolean[n * 2 - 1];
        dfs(0, queens, col, diag1, diag2, ans);

        return ans;
    }

    public void dfs(int i, int[] queens, boolean[] enable, boolean[] diag1, boolean[] diag2, List<List<String>> ans) {
        int n = enable.length;
        if (i == n) {
            List<String> board = new ArrayList<>(n);
            for (int c : queens) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[c] = 'Q';
                board.add(new String(row));
            }
            ans.add(board);
            return;
        }
        // i, j 放置皇后
        for (int j = 0; j < n; j++) {
            // [-n+1, n-1] 范围扩大 n-1
            int rc = i - j + n - 1;
            // 判断能否放皇后
            if (!enable[j] && !diag1[i + j] && !diag2[rc]) {
                queens[i] = j;
                enable[j] = diag1[i + j] = diag2[rc] = true; // 皇后占用了 c 列和两条斜线
                dfs(i + 1, queens, enable, diag1, diag2, ans);
                // 恢复现场
                enable[j] = diag1[i + j] = diag2[rc] = false;
            }
        }
    }

}
