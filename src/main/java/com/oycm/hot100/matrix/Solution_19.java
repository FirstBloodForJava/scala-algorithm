package com.oycm.hot100.matrix;

import java.util.ArrayList;
import java.util.List;

public class Solution_19 {

    /**
     * 54. <a href="https://leetcode.cn/problems/spiral-matrix/description/">螺旋矩阵</a>
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        /*
        给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
         */
        /*
        标记法
        定义能走的 4 个方向 右下左上，当碰到边界或已经走过的点时，就需要拐弯
         */
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length, n = matrix[0].length;
        int size = m * n;
        List<Integer> ans = new ArrayList<>(size);
        int di = 0;
        int i = 0;
        int j = 0;
        for (int k = 0; k < size; k++) {
            ans.add(matrix[i][j]);
            // 标记为访问
            matrix[i][j] = Integer.MAX_VALUE;
            // 计算下一步位置
            int x = i + dirs[di][0];
            int y = j + dirs[di][1];
            // 看位置是否需要转弯
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == Integer.MAX_VALUE) {
                di = (di + 1) % 4;
            }
            i += dirs[di][0];
            j += dirs[di][1];
        }

        return ans;
    }

    public List<Integer> spiralOrder_1(int[][] matrix) {
        /*
        [
            [1,2,3,4],
            [5,6,7,8],
            [9,10,11,12]
        ]
        右 1 -> 2 -> 3 -> 4 移动 4 次
        下 8 -> 12 移动 2 次
        左 11 -> 10 -> 9 移动 3 次
        上 5 移动 1 次
        右 6 -> 7 移动 2 次
        可以发现规律，再移动多少次后换方向，次数也是交替减少 4 -> 3 -> 2; 2 -> 1
         */
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length, n = matrix[0].length;
        int size = m * n;
        List<Integer> ans = new ArrayList<>(size);
        int i = 0;
        // 先移动，再加入答案
        int j = -1;
        for (int di = 0; ans.size() < size; di = (di + 1) % 4) {
            for (int k = 0; k < n; k++) {
                i += dirs[di][0];
                j += dirs[di][1];
                ans.add(matrix[i][j]);
            }
            int temp = n;
            n = m - 1;
            m = temp;
        }
        return ans;
    }


}
