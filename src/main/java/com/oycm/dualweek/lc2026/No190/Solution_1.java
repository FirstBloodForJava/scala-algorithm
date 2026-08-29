package com.oycm.dualweek.lc2026.No190;

public class Solution_1 {

    /**
     * 象到达目标格子的最少移动步数
     *
     * @param source
     * @param target
     * @return
     */
    public int minBishopMoves(int[] source, int[] target) {
        /*
        给你一个 8 x 8 的棋盘，行和列的下标从 1 开始。
        给你一个数组 source = [sr, sc]，表示 象 的起始位置，以及一个数组 target = [tr, tc]。
        在一步移动中，象可以在棋盘范围内沿着单个 对角线 方向移动任意数量的格子。
        返回象 恰好 到达 target 位置所需的 最少 移动次数。如果它永远无法到达 target，则返回 -1。
         */
        /*
        s 和 t 恰好在对角线上
        不在对角线上，先移动到同行或同列，如果 间隔是偶数，则最快可该差值到达，否则到达不了
         */
        int sr = source[0], sc = source[1];
        int tr = target[0], tc = target[1];
        int r = Math.abs(tr - sr);
        int c = Math.abs(tc - sc);
        if (r == c) {
            return 1;
        }

        if (r < c) {
            // 行移动受限
            int nc = sc + (tr - sr) - tc;
            if (nc % 2 != 0) {
                return -1;
            }
            return 2;
        } else {
            int nr = sr + (tc - sc) - tr;
            if (nr % 2 != 0) {
                return -1;
            }
            return 2;
        }

    }
}
