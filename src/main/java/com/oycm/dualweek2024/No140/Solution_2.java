package com.oycm.dualweek2024.No140;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 3301. <a href="https://leetcode.cn/problems/maximize-the-total-height-of-unique-towers/description/">高度互不相同的最大塔高和</a> 1448
     *
     * @param maximumHeight
     * @return
     */
    public long maximumTotalSum(int[] maximumHeight) {
        /*
        给你一个数组 maximumHeight ，其中 maximumHeight[i] 表示第 i 座塔可以达到的 最大 高度。
        你的任务是给每一座塔分别设置一个高度，使得：
            第 i 座塔的高度是一个正整数，且不超过 maximumHeight[i] 。
            所有塔的高度互不相同。
        请你返回设置完所有塔的高度后，可以达到的 最大 总高度。如果没有合法的设置，返回 -1。
        1 <= maximumHeight.length <= 1e5
        1 <= maximumHeight[i] <= 1e9
         */
        /*
        对数组按倒序排序，第 i 个塔能取的最大高度为 min(maximumHeight[i-1] - 1, maximumHeight[i])
            如果 maximumHeight[i-1] = maximumHeight[i] i-1 取前一个高度减一最优
            如果 maximumHeight[i-1] > maximumHeight[i] i 保持不变最优，高度降低会导致其它更矮的塔高度被占用，要降低高度
         */
        Arrays.sort(maximumHeight);
        int n = maximumHeight.length;
        long sum = maximumHeight[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maximumHeight[i] = Math.min(maximumHeight[i+1] - 1, maximumHeight[i]);
            if (maximumHeight[i] <= 0) return -1;
            sum += maximumHeight[i];
        }
        return sum;
    }

}
