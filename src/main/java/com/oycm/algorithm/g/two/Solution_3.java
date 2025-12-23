package com.oycm.algorithm.g.two;


public class Solution_3 {

    /**
     * 2132. <a href="https://leetcode.cn/problems/stamping-the-grid/description/">用邮票贴满网格图</a> 2364
     * <p>
     * 按以下要求将邮票贴入二进制矩阵，并满足以下限制和要求:
     * - 覆盖所有 空 格子;
     * - 不覆盖任何 被占据 的格子;
     * - 可以放入任意数目的邮票;
     * - 邮票可以相互有 重叠 部分;
     * - 邮票不允许 旋转;
     * - 邮票必须完全在矩阵 内
     *
     * @param grid        m x n 的二进制矩阵, 0 表示格子为空, 1 表示被占据
     * @param stampHeight 邮票的高度
     * @param stampWidth  邮票的宽度
     * @return
     */
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        /*
        先思考 一维情况, m = 1 时, stampHeight > m, 邮票怎么放都会超出矩阵;
            遍历 grid, grid[0][i] == 1, 这个位置不能放邮票; grid[0][i] == 0, 需要看 grid[0][i+stampWidth-1] 是不是 0, 如果不是, 则不符合要求
            curOne - preOne = diff > 1, diff >= stampWidth, 可以使邮票重叠让其放满, sums[i+1] == sums[j+stampWidth] 中间没有1, 则可以铺满邮票
        grid[i][j] == 0, s[i][j] == s[i+stampHeight][j+stampWidth] 中间没有 1, 则可以铺满

        */
        int m = grid.length;
        int n = grid[0].length;
        // 计算二维前缀和
        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
            }
        }

        /*
        初始化差分数组 m+2, n+2, 添加一行一列 0
        左上角[0, 0] 右下角 [stampHeight-1, stampWidth-1] 格式的矩阵和 是否都为 0, 才可以填邮票
        由于差分调整时前面添加了一行一列, 所以需要右移下移一位, 左上角[1, 1] 右下角 [stampHeight, stampWidth1],
        (r1, c1), (r2, c2) 的子矩阵和 = s[r2+1][c2+1] - s[r2+1][c1] - s[r1][c2+1] + s[r1][c1], 所以计算 子矩阵 i1, j1 需要 减少 1
         */
        int[][] d = new int[m + 2][n + 2];
        for (int i2 = stampHeight; i2 <= m; i2++) {
            for (int j2 = stampWidth; j2 <= n; j2++) {
                int i1 = i2 - stampHeight + 1;
                int j1 = j2 - stampWidth + 1;
                // 判断这个 stampHeight * stampWidth 的矩阵是否能填 填邮票
                if (s[i2][j2] - s[i2][j1 - 1] - s[i1 - 1][j2] + s[i1 - 1][j1 - 1] == 0) {
                    d[i1][j1]++;
                    d[i1][j2 + 1]--;
                    d[i2 + 1][j1]--;
                    d[i2 + 1][j2 + 1]++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                d[i + 1][j + 1] += d[i][j + 1] + d[i + 1][j] - d[i][j];
                /*
                还原 差分数组的过程中, 存在为 0 的格子, 且前缀和为 0(未填邮票)
                 */
                if (grid[i][j] == 0 && d[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
