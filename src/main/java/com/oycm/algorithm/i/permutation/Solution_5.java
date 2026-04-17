package com.oycm.algorithm.i.permutation;

public class Solution_5 {

    /**
     * 52. <a href="https://leetcode.cn/problems/n-queens-ii/description/">N 皇后 II</a>
     *
     * @param n
     * @return n 皇后问题 不同的解决方案的数量
     */
    public int totalNQueens(int n) {
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
        ↘这条斜向 坐标差是不变的; ↗这条斜向上的和是不变的
         */

        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[n * 2 - 1];
        boolean[] diag2 = new boolean[n * 2 - 1];
        dfs(0, col, diag1, diag2);
        return ans;
    }

    int ans = 0;

    public void dfs(int i, boolean[] enable, boolean[] diag1, boolean[] diag2) {
        int n = enable.length;
        if (i == n) {
            ans++;
            return;
        }
        // i, j 放置皇后
        for (int j = 0; j < n; j++) {
            // [-n+1, n-1] 范围扩大 n-1
            int rc = i - j + n - 1;
            // 判断能否放皇后
            if (!enable[j] && !diag1[i + j] && !diag2[rc]) {
                enable[j] = diag1[i + j] = diag2[rc] = true; // 皇后占用了 c 列和两条斜线
                dfs(i + 1, enable, diag1, diag2);
                // 恢复现场
                enable[j] = diag1[i + j] = diag2[rc] = false;
            }
        }
    }
}
