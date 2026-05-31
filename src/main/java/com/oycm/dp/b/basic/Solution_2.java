package com.oycm.dp.b.basic;

public class Solution_2 {

    /**
     * 62. <a href="https://leetcode.cn/problems/unique-paths/description/">不同路径</a>
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        /*
        一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
        问总共有多少条不同的路径？
         */
        int[] f = new int[n + 1];
        f[1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[j + 1] = f[j + 1] + f[j];
            }
        }

        return f[n];
    }

}
