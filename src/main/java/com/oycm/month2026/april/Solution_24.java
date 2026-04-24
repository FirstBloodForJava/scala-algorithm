package com.oycm.month2026.april;

public class Solution_24 {

    /**
     * 2833. <a href="https://leetcode.cn/problems/furthest-point-from-origin/description/">距离原点最远的点</a> 1294
     *
     * @param moves 字符串，仅有 ['L', 'R', '_'] 字符组成
     * @return
     */
    public int furthestDistanceFromOrigin(String moves) {
        /*
        你的初始位置就在原点（0），第 i 次移动过程中，你可以根据对应字符选择移动方向：
            如果 moves[i] = 'L' 或 moves[i] = '_' ，可以选择向左移动一个单位距离
            如果 moves[i] = 'R' 或 moves[i] = '_' ，可以选择向右移动一个单位距离
         */
        /*
        L 和 R 抵消后的绝对值 + '_' 数量
         */
        int abs = 0;
        int cnt = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                abs++;
            } else if (c == 'R') {
                abs--;
            } else {
                cnt++;
            }
        }
        return Math.abs(abs) + cnt;
    }

}
