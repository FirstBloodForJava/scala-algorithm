package com.oycm.week.No497;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 3899. <a href="https://leetcode.cn/problems/angles-of-a-triangle/description/">三角形的内角度数</a>
     *
     * @param sides sides.length = 3
     * @return 判断是否能够由 sides 中的三个元素作为边长，构成一个 面积为正 的三角形，存在，则按非递减顺序返回三个内角的大小，单位 度，否则返回空数组
     */
    public double[] internalAngles(int[] sides) {
        /*
        先判断三角形是否存在
        余弦定理
        a^2 = b^2 + c^2 - 2bc cosA
         */
        Arrays.sort(sides);
        int a = sides[0], b = sides[1], c = sides[2];
        if (a + b <= c) {
            return new double[0];
        }
        double rad = 180 / Math.PI;
        double A = Math.acos((double) (b * b + c * c - a * a) / (2 * b * c)) * rad;
        double B = Math.acos((double) (a * a + c * c - b * b) / (2 * a * c)) * rad;
        return new double[]{A, B, 180 - A - B};
    }

}
