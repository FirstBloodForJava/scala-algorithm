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

    public List<Integer> sequentialDigits_1(int low, int high) {
        /*
        滑动窗口
         */
        int x0 = 12;
        int pow10 = 10;
        List<Integer> ans = new ArrayList<>();
        for (int length = 2; x0 <= high ; length++) {
            pow10 *= 10;
            // 枚举相同长度数字
            int x = x0;
            for (int i = length; i <= 9 && x <= high ; i++) {
                if (x >= low) ans.add(x);
                /*
                length = i - l + 1
                r+1 进入窗口，l 从窗口溢出 l = i + 1 - length
                 */
                x = x * 10 + i + 1 - (i + 1 - length) * pow10;
            }

            x0 = x0 * 10 + length + 1;
        }

        return ans;
    }
}
