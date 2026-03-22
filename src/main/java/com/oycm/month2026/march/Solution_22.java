package com.oycm.month2026.march;

public class Solution_22 {

    /**
     * 1886. <a href="https://leetcode.cn/problems/determine-whether-matrix-can-be-obtained-by-rotation/description/">判断矩阵经轮转后是否一致</a> 1407
     *
     * @param mat
     * @param target
     * @return
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        /*
        看当前位置 (i, j) 旋转后的位置去了哪里, 如果 所有原位置的值和新位置值都相同, 则返回 true
        (i, j)
            0   (i, j)
            90  (j, n - 1 - i)
            180 (n - 1 - i, n - 1 - j)
            270 (n - 1 - j, i)
         */
        // 1111
        int ok = (1 << 4) - 1, n = target.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = target[i][j];
                if (mat[i][j] != x) {
                    ok &= ~1;
                }
                if (mat[j][n - 1 - i] != x) {
                    ok &= ~2;
                }
                if (mat[n - 1 - i][n - 1 - j] != x) {
                    ok &= ~4;
                }
                if (mat[n - 1 - j][i] != x) {
                    ok &= ~8;
                }
                if (ok == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 48. <a href="https://leetcode.cn/problems/rotate-image/description/">旋转图像</a>
     *
     * @param matrix 顺时针旋转 matrix 90
     */
    public void rotate(int[][] matrix) {
        /*
        顺时针旋转 90,
            横看
            第一行的元素去了最后一列
            第二行的元素去了倒数第二列
            ...
            第 i 行的元素去了 (n - 1 -i) 列
            竖看
            第一列元素去了第一行
            第一列元素去了第二行
            ...
            第 j 列元素去了第 j 行
        所以 (i, j) -> (j, n - 1 - i)
        (i, j) -> (j, i) -> (j, n - 1 - i)
            先对角线翻转
            再行翻转
         */
        int n = matrix.length;
        // 对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int[] row : matrix) {
            for (int i = 0; i < n / 2; i++) {
                int temp = row[i];
                row[i] = row[n - 1 - i];
                row[n - 1 - i] = temp;
            }
        }
    }

}
