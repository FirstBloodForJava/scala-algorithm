package com.oycm.month2026.march;

public class Solution_27 {

    /**
     * 2946. <a href="https://leetcode.cn/problems/matrix-similarity-after-cyclic-shifts/description/">循环移位后的矩阵相似检查</a> 1406
     *
     * @param mat m x n 的正整数矩阵
     * @param k
     * @return
     */
    public boolean areSimilar(int[][] mat, int k) {
        /*
        矩阵中的 奇数 行循环 右 移 k 次，偶数 行循环 左 移 k 次
        初始矩阵和最终矩阵完全相同，则返回 true ，否则返回 false
         */
        /*
        计算移动前的值，和移动后的值是否一致
        题解优化: 如果是两个数组 a 和 b，要判断 a 左移/右移后 是否 等于 b，那么 a 左移 k 次 和 右移 k 次是不一样的
        现在是 两个数组都是 a ，要判断 a 左移/右移后 是否等于 a 自己
        a 左移 k 次 和 b 比较，等价于 b 右移 k 次后 和 a 比较，b 是 a 的情况下，等价于 a 自己右移 k 次和自己比较
         */
        int m = mat.length, n = mat[0].length;
        k %= n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != mat[i][(j + k) % n]) {
                    return false;
                }
            }

        }

        return true;
    }

}
