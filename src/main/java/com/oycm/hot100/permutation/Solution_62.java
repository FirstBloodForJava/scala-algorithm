package com.oycm.hot100.permutation;

import com.oycm.algorithm.i.permutation.Solution_4;

import java.util.List;

public class Solution_62 {

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
        本质是求 [0, n-1] n 个数的全排列，排列有限制条件：任意两两之间不在向上或向下的斜线上。
        对于每一行，恰好只能有一个皇后，记录每一行的皇后放哪一列，可以得到一个 [0, n-1] 的全排列，排列结果的下标表示第几行，值表示几列
        如果判断皇后是否在一条斜向上
            向上斜线 y = x + b => x-y = -b
            向下斜线 y = -x + b => x+y = b
            在一条斜向上的坐标和或坐标差是定值，可以用一个 2*n - 1的数组来标记这一行是否被使用
         */
        return new Solution_4().solveNQueens(n);
    }

}
