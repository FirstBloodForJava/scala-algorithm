package com.oycm.dp.b.advance;

import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * 1301. <a href="https://leetcode.cn/problems/number-of-paths-with-max-score/description/">最大得分的路径数目</a> 1853
     *
     * @param board 2 <= board.length == board[i].length <= 100
     * @return
     */
    public int[] pathsWithMaxScore(List<String> board) {
        /*
        给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。
        你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字符 1, 2, ..., 9 或者障碍 'X'。
        在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。
        一条路径的 「得分」 定义为：路径上所有数字的和。
        请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对 10^9 + 7 取余。
        如果没有任何路径可以到达终点，请返回 [0, 0]。
         */
        /*
        右下角到左上角，向上、向左或者左上方移动。等价 左上角到右下角，向下、向右或者右下角移动。
        1, 3
        3, 4
        定义 f[i][j][2] 表示 路径最大值及对应的方案数，如果 grid[i][j] = 'X'，则 f[i][j] = {0, 0}。
            (i, j) 可以从 (i-1, j-1), (i-1, j), (i, j-1)；三个方向过来，就算每个位置到该点的得分，如果得分相同，则合并方案数。
         */
        int mod = 1000000007;
        int n = board.size();
        // 最大得分
        int[] fScore = new int[n + 1];
        Arrays.fill(fScore, Integer.MIN_VALUE);
        fScore[0] = 0;
        // 最大得分对应方案数
        int[] fCnt = new int[n + 1];
        fCnt[0] = 1;

        for (String s : board) {
            // top left corner
            int tlcScore = fScore[0];
            int tlcCnt = fCnt[0];
            fScore[0] = Integer.MIN_VALUE;
            fCnt[0] = 0;
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                if (c == 'X') {
                    tlcScore = fScore[j + 1];
                    tlcCnt = fCnt[j + 1];
                    fScore[j + 1] = Integer.MIN_VALUE;
                    fCnt[j + 1] = 0;
                    continue;
                }
                // 正左，左上，正上
                int score = Math.max(fScore[j], Math.max(tlcScore, fScore[j + 1]));
                long cnt = 0;
                // 分数相同，合并方案数
                if (fScore[j] == score) {
                    cnt += fCnt[j];
                }
                if (tlcScore == score) {
                    cnt += tlcCnt;
                }
                if (fScore[j + 1] == score) {
                    cnt += fCnt[j + 1];
                }
                tlcScore = fScore[j + 1];
                tlcCnt = fCnt[j + 1];
                fScore[j + 1] = score;
                fCnt[j + 1] = (int) (cnt % mod);
                if (Character.isDigit(c)) {
                    // 数字加上得分
                    fScore[j + 1] += c - '0';
                }
            }
        }

        return fScore[n] < 0 ? new int[]{0, 0} : new int[]{fScore[n], fCnt[n]};
    }



}
