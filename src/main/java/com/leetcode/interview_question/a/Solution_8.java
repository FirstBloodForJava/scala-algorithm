package com.leetcode.interview_question.a;

public class Solution_8 {

    /**
     * 面试题 01.08. <a href="https://leetcode.cn/problems/zero-matrix-lcci/description/">零矩阵</a>
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        /*
        编写一种算法，若 M × N 矩阵中某个元素为 0，则将其所在的行与列清零。
         */
        /*
        原地标记
        用第一行，第一列 标记 行/列是否需要标记为 0，由于后面的遍历会修改第一行第一列的标记，所以需要先用变量标记 第一行/第一列是否能设置为 0
        用一个变量记录 第一列是否有 0。
        正序更新，第一列第一行的数据，可能会错误的被更新，
            例如，第一行遍历后都为 0，matrix[0][0] 可能是改行的其它元素标记为 0 的
            第二行的遍历也是如此
        所以需要倒序遍历更新，使用遍历判断第一列的元素是否要标记为 0
         */
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flagCol0 = false;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }

    }

}
