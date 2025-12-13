package com.oycm.algorithm.g;

public class Solution_2 {

    /**
     * 1893. <a href="https://leetcode.cn/problems/check-if-all-the-integers-in-a-range-are-covered/description/">检查是否区域内所有整数都被覆盖</a> 1307
     *
     * @param ranges
     * @param left
     * @param right
     * @return
     */
    public boolean isCovered(int[][] ranges, int left, int right) {
        /*
        闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖
        根据 ranges end 创建一个 max + 2 的差分数组，将 d[start] += 1, d[end+1] -= 1
        还原后的 数组 a[left, right] 都大于 0

         */
        int max = 0;
        for (int i = 0; i < ranges.length; i++) {
            max = Math.max(max, ranges[i][1]);
        }
        // ranges 的 max 在 [left,right] 中间
        if (max < right) {
            return false;
        }

        int[] d = new int[max + 2];
        for (int i = 0; i < ranges.length; i++) {
            d[ranges[i][0]] += 1;
            d[ranges[i][1] + 1] -= 1;
        }
        int s = 0;
        for (int i = 0; i < d.length; i++) {
            s += d[i];
            if (left <= i && i <= right && s == 0) {
                return false;
            }
        }

        return true;
    }
}
