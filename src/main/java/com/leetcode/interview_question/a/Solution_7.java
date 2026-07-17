package com.leetcode.interview_question.a;

public class Solution_7 {

    /**
     * 面试题 01.07. a href="https://leetcode.cn/problems/rotate-matrix-lcci/description/">旋转矩阵</a>
     * <
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        /*
        给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
        不占用额外内存空间能否做到？
         */
        /*
        第 i 行元素旋转后，移动到第 n-1-i 列
        第 j 列元素旋转后，移动到第 j 行
        (i, j) -> (j, n-1-i)
        (i, j) 沿对角线翻转 (j, i)
        (j, i) 行翻转 (j, n-1-i)
         */

    }

}
