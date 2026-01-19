package com.oycm.algorithm.f.prefix_sum_two;


import com.oycm.DataCreateUtils;

public class Solution_6 {

    /**
     * 1292. 元素和小于等于阈值的正方形的最大边长
     *
     * @param mat
     * @param threshold
     * @return
     */
    public static int maxSideLength(int[][] mat, int threshold) {
        /*
        三层循环,
        (r1, c1) 为左上角, r1++, c1++ 且 r1 < m && c1 < n; 计算矩阵和, 是否 <= threshold
         */
        int ans = 0;
        int m = mat.length, n = mat[0].length;
        int[][] sums = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] <= threshold) {
                    ans = 1;
                }
                sums[i + 1][j + 1] = sums[i + 1][j] + sums[i][j + 1] - sums[i][j] + mat[i][j];
            }
        }
        if (ans == 0) {
            return ans;
        }

        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int r2 = i + 1, c2 = j + 1; r2 < m && c2 < n; r2++, c2++) {
                    int sum = sums[r2 + 1][c2 + 1] - sums[i][c2 + 1] - sums[r2 + 1][j] + sums[i][j];
                    if (sum <= threshold) {
                        ans = Math.max(ans, r2 - i + 1);
                    } else {
                        break;
                    }
                }
            }
        }*/
        // 优化点: 第三层循环从 i+ans 开始循环, 符合条件则 ans++, 最内层循环至多执行 min(m, n) 次数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                while (i + ans < m && j + ans < n && sums[i + ans + 1][j + ans + 1] - sums[i][j + ans + 1] - sums[i + ans + 1][j] + sums[i][j] <= threshold) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxSideLength(DataCreateUtils.twoDimensionInts("[[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]]"), 4));
    }
}
