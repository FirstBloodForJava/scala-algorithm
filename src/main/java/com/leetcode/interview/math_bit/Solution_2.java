package com.leetcode.interview.math_bit;

import java.util.HashMap;
import java.util.Map;

public class Solution_2 {

    /**
     * 直线上最多的点数
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        /*
        给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
         */
        /*
        1 <= points.length <= 300
        points[i].length == 2
        -1e4 <= xi, yi <= 1e4
        points 中的所有点 互不相同
         */
        /*
        y = kx + b, k = (y2 - y1)/(x2 - x1)
            x1 = x2, k = max
            x1 != x2, k = (y2 - y1)/(x2 - x1)
         */
        int ans = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> cnt = new HashMap<>();
            /*
            这里为什么不用考虑 i 前面的点呢？
            因为如果 j 前面的点和 j 在一条直线上，那么前面的 i 点在计算时，就已经计算了这条直线上的所有点
             */
            for (int j = i + 1; j < n; j++) {
                int dy = points[j][1] - points[i][1];
                int dx = points[j][0] - points[i][0];
                double k = dx == 0 ? Double.POSITIVE_INFINITY : (double) dy / dx;
                int c = cnt.merge(k, 1, Integer::sum);
                ans = Math.max(ans, c);
            }
        }

        return ans + 1;
    }

}
