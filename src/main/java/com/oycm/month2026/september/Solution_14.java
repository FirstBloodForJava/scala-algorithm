package com.oycm.month2026.september;

public class Solution_14 {

    /**
     * 836. <a href="https://leetcode.cn/problems/rectangle-overlap/description/">矩形重叠</a> 1443
     *
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        /*
        矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。矩形的上下边平行于 x 轴，左右边平行于 y 轴。
        如果相交的面积为 正 ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
        给出两个矩形 rec1 和 rec2 。如果它们重叠，返回 true；否则，返回 false 。
         */
        /*
        转换成一维问题，长和宽的线段是否相交
         */
        return isIntervalOverlap(rec1[0], rec1[2], rec2[0], rec2[2]) && isIntervalOverlap(rec1[1], rec1[3], rec2[1], rec2[3]);
    }

    public boolean isIntervalOverlap(int s1, int e1, int s2, int e2) {
        // [s1, e1] 和 [s2, e2] 区间是否相交
        return Math.max(s1, s2) < Math.min(e1, e2);
    }

}
