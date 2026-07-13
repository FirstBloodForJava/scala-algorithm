package com.oycm.month2026.july;

import java.util.ArrayList;
import java.util.List;

public class Solution_13 {

    /**
     * 1291. 顺次数
     * <br>
     * 1291. <a href="https://leetcode.cn/problems/sequential-digits/description/">顺次数</a> 1374
     *
     * @param low
     * @param high
     * @return
     */
    public List<Integer> sequentialDigits(int low, int high) {
        /*
        我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。

请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
         */
        /*
        10 <= low <= high <= 10^9
         */
        init();
        List<Integer> ans = new ArrayList<>();

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < sqd.length; i++) {
                if (j >= sqd[i].length) continue;
                if (low <= sqd[i][j] && sqd[i][j] <= high) {
                    ans.add(sqd[i][j]);
                }
            }
        }

        return ans;
    }

    public static int[][] sqd = new int[8][];

    public static boolean initiated = false;

    public void init() {
        if (initiated) return;
        initiated = true;
        for (int i = 0; i < sqd.length; i++) {
            sqd[i] = new int[9 - i - 1];
            sqd[i][0] = (i + 1) * 10 + i + 2;
            for (int j = 1; j < sqd[i].length; j++) {
                sqd[i][j] = sqd[i][j - 1] * 10 + i + j + 2;
            }
        }
    }
}
