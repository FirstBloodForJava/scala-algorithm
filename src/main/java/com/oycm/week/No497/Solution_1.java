package com.oycm.week.No497;

public class Solution_1 {

    /**
     * 3898. <a href="https://leetcode.cn/problems/find-the-degree-of-each-vertex/description/">统计每个顶点的度</a>
     *
     * @param matrix n x n 的二维整数数组
     * @return ans 数组，ans[i] 表示顶点 i 的度
     */
    public int[] findDegrees(int[][] matrix) {
        /*
        matrix[i][j] 表示顶点 i j 之间是否存在一条边，1 表示存在，0 表示不存在
        顶点 i 与顶点 j 之间存在一条边
        顶点的 度（degree）定义为与该顶点相连的边的数量
         */
        /*
        统计每行的和
         */
        int n = matrix.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int x : matrix[i]) {
                ans[i] += x;
            }
        }

        return ans;
    }

}
