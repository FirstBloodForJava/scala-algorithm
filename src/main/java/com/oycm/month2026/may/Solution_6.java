package com.oycm.month2026.may;

import java.util.Arrays;

public class Solution_6 {

    /**
     * 1861. <a href="https://leetcode.cn/problems/rotating-the-box/description/">旋转盒子</a> 1537
     *
     * @param boxGrid m x n 的字符矩阵
     * @return
     */
    public char[][] rotateTheBox(char[][] boxGrid) {
        /*
        m x n 的字符矩阵 boxGrid ，它表示一个箱子的侧视图。箱子的每一个格子可能为：
            '#' 表示石头
            '*' 表示固定的障碍物
            '.' 表示空位置
        这个箱子被 顺时针旋转 90 度 ，由于重力原因，部分石头的位置会发生改变。
        每个石头会垂直掉落，直到它遇到障碍物，另一个石头或者箱子的底部。
        重力 不会 影响障碍物的位置，同时箱子旋转不会产生惯性 ，也就是说石头的水平位置不会发生改变。
        题目保证初始时 boxGrid 中的石头要么在一个障碍物上，要么在另一个石头上，要么在箱子的底部。
         */
        /*
        先水平倒序遍历 boxGrid 的每一行，看石头是否能向右平移（这里没有利用题目的初始保证）
         */
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        char[][] ans = new char[n][m];
        for (char[] row : ans) {
            Arrays.fill(row, '.');
        }

        /*
        选择 (i, j)
            横看，第 i 行变到 第 (m - i - 1) 列
            竖看，第 j 列变到 第 j 行
            (i, j) -> (j , m - i - 1)
         */
        for (int i = 0; i < m; i++) {
            char[] row = boxGrid[i];
            // 倒序遍历，默认所有都是空位置
            int k = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (row[j] == '*') {
                    ans[j][m - i - 1] = '*';
                    k = j - 1;
                } else if (row[j] == '#') {
                    ans[k--][m - 1 - i] = '#';
                }
            }
        }

        return ans;
    }

}
