package com.oycm.month2026.july;

import java.util.List;

public class Solution_5 {

    /**
     * 1301. 最大得分的路径数目
     * <br>
     * 1301. <a href="https://leetcode.cn/problems/number-of-paths-with-max-score/description/">最大得分的路径数目</a> 1853
     *
     * @param board
     * @return
     */
    public int[] pathsWithMaxScore(List<String> board) {
        /*
        给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。
        你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字符 1, 2, ..., 9 或者障碍 'X'。
        在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。
        一条路径的 「得分」 定义为：路径上所有数字的和。
        请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对 10^9 + 7 取余。
        如果没有任何路径可以到达终点，请返回 [0, 0] 。
         */
        /*
        右下角到左上角，向上，向左，左上方移动，等价左上角到右下，向下，向右，右下方移动。
        f[i][j] =
            grid[i][j] = 'X', = 0;
            f[i-1][j], f[i][j-1], f[i-1][j-1] 三者取最大值的次数
        f[1][j] 要记录路径 得分最大值以及方案数
         */
        int n = board.size();
        char[][] grid = new char[n][];
        for (int i = 0; i < board.size(); i++) {
            grid[i] = board.get(i).toCharArray();
        }
        int[][][] f = new int[n + 1][n + 1][2];
        f[0][0] = new int[]{0, 1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                // 有障碍物，无法到达
                if (c == 'X') {
                    f[i + 1][j + 1] = new int[]{0, 0};
                } else if (Character.isLetter(c)) {
                    // S/E
                    f[i + 1][j + 1] = mergeMax(f[i][j + 1], f[i + 1][j], f[i][j]);
                } else {
                    f[i + 1][j + 1] = mergeMax(f[i][j + 1], f[i + 1][j], f[i][j]);
                    if (f[i + 1][j + 1][1] > 0) {
                        int d = c - '0';
                        f[i + 1][j + 1][0] += d;
                    }

                }
            }
        }

        return f[n][n];
    }

    public int[] mergeMax(int[]... f) {
        int max = 0;
        long cnt = 0;
        for (int[] p : f) {
            if (p[0] == max) {
                cnt += p[1];
            } else if (p[0] > max) {
                max = p[0];
                cnt = p[1];
            }
        }
        return new int[]{max, (int) (cnt % 1000000007)};
    }

}
