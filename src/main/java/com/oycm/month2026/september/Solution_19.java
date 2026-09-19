package com.oycm.month2026.september;

public class Solution_19 {

    /**
     * 1401. <a href="https://leetcode.cn/problems/circle-and-rectangle-overlapping/description/">圆和矩形是否有重叠</a> 1709
     *
     * @param radius
     * @param xCenter
     * @param yCenter
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        /*
        给你一个以 (radius, xCenter, yCenter) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2) ，其中 (x1, y1) 是矩形左下角的坐标，而 (x2, y2) 是右上角的坐标。
        如果圆和矩形有重叠的部分，请你返回 true ，否则返回 false 。
        换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
         */
        /*
        题解思路：
        找矩形到圆心最近的点 (x, y)
        x1 <= x <= x2; y1 <= y <= y2
        d^2 = (x - xCenter)^2 + (y - yCenter)^2
        最小化 d^2 结果就是在 [x1, x2], [y1, y2] 中找到力 xCenter, yCenter 最近的数
        如果 xCenter 在 [x1, x2] 中，x = xCenter
        如果 xCenter 在 x1 左边， x = x1
        如果 xCenter 在 x2 右边，x = x2
        x = max(x1, min(xCenter, x2))
         */
        int x = Math.max(x1, Math.min(xCenter, x2));
        int y = Math.max(y1, Math.min(yCenter, y2));
        return (x - xCenter) * (x - xCenter) + (y - yCenter) * (y - yCenter) <= radius * radius;
    }

}
