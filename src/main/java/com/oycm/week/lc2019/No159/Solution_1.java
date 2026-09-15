package com.oycm.week.lc2019.No159;

public class Solution_1 {

    /**
     * 1232. <a href="https://leetcode.cn/problems/check-if-it-is-a-straight-line/description/">缀点成线</a> 1247
     *
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        /*
        给定一个整数数组 coordinates ，其中 coordinates[i] = [x, y] ， [x, y] 表示横坐标为 x、纵坐标为 y 的点。请你来判断，这些点是否在该坐标系中属于同一条直线上。
         */
        int dy = coordinates[1][1] - coordinates[0][1];
        int dx = coordinates[1][0] - coordinates[0][0];
        for (int i = 2; i < coordinates.length; i++) {
            /*
            计算相邻两点斜率是否都是固定值
            y1/x1 = y2/x2 => y1 * x2 = y2 * x1
             */
            if (dy * (coordinates[i][0] - coordinates[i - 1][0]) != dx * (coordinates[i][1] - coordinates[i - 1][1])) {
                return false;
            }
        }
        return true;
    }

}
