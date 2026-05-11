package com.oycm.hot100.matrix;

public class Solution_21 {

    /**
     * 240. <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/description/">搜索二维矩阵 II</a>
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        /*
        搜索 m x n 矩阵 matrix 中的一个目标值 target。
        该矩阵具有以下特性：
            每行的元素从左到右升序排列。
            每列的元素从上到下升序排列。
         */
        /*
        暴力的做法是直接遍历矩阵，看是否存在等于 target 的元素。
        没有利用矩阵的特性，该怎么使用这个特性作为切入点？
        题解思路：排除法
        利用矩阵左上角是最小值，右下角是最大值，另外两个角的值居中
        从右上角开始搜索：i = 0, j = n-1;
            如果 matrix[i][j] > target, i in [i+1, m) (i, j) 的数都大于 target，j 行排除，j--;
            如果 matrix[i][j] < target, j in [0, j) 的数都小于 target，i 行排除，i++
            i < n && j > 0 才继续搜索
         */
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }

        return false;
    }

}

class Solution_1351 {

    /**
     * 1351. <a href="https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/description/">统计有序矩阵中的负数</a>
     *
     * @param grid m * n 的矩阵 grid
     * @return
     */
    public int countNegatives(int[][] grid) {
        /*
        矩阵中的元素无论是按行还是按列，都以非严格递减顺序排列。 请你统计并返回 grid 中 负数 的数目。
        满足以下条件：
            行 grid[i][j] >= grid[i][j+1]
            列 grid[i][j] >= grid[i+1][j]
         */
        /*
        矩阵左上角是最大值，右下角是最小值，另外两个角的值居中
        从右上角开始搜索：i = 0, j = n-1;
            如果 matrix[i][j] >= 0, 最小值都 >= 0，则第 i 行所有值都是非负数，i++;
            如果 matrix[i][j] < 0，最大值都 < 0，则第 j 列所有值都是负数，计算负数个数 m - j，j 列计算完成 j--
            i < n && j > 0 才继续搜索
        一次就能排除一行/一列 是否符合要求
         */
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (grid[i][j] >= 0) {
                i++;
            } else {
                ans += m - i;
                j--;
            }
        }

        return ans;
    }
}
