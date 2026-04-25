package com.oycm.month2026.april;

import java.util.Arrays;

public class Solution_25 {

    /**
     * 3464.
     * <a href="https://leetcode.cn/problems/maximize-the-distance-between-points-on-a-square/description/">正方形上的点之间的最大距离</a> 2806
     *
     * @param side 正方形的边长
     * @param points points[i] = [x, y] 表示一个点在正方形边界上的坐标
     * @param k 正整数
     * @return
     */
    public int maxDistance(int side, int[][] points, int k) {
        /*
        你需要从 points 中选择 k 个元素，使得任意两个点之间的 最小 曼哈顿距离 最大化 。
        返回选定的 k 个点之间的 最小 曼哈顿距离的 最大 可能值。
         */
        /*
        题解思路：
        把正方形四条边，按照左边界、上边界、右边界、下边界的顺时针顺序，拉成一条直线，转换成一维数组
        x = points[i][0]; y = points[i][1]
            x == 0, nums[i] = x;
            y == side, nums[i] = side + x
            x == side, nums[i] = side * 3 - y
            y == 0, nums[i] = side * 4 - x
        在一条直线上，相邻的点选的越多，最小值越小，具有单调性，注意，这里是当 k >= 4 时，才有这种单调性。
        问题变成在数组中，相邻元素的差至少为 low，且最后一个数 - 第一个数至多为 side * 4 - low
            第一个数为 x，第二个数为 y，y 到点 0 的距离为 side * 4 - y，x 和 y 的距离 x + (side * 4 - y) >= low; y - x <=

         */
        long[] a = new long[points.length];
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (x == 0) {
                a[i] = y;
            } else if (y == side) {
                a[i] = side + x;
            } else if (x == side) {
                a[i] = side * 3L - y;
            } else {
                a[i] = side * 4L - x;
            }
        }
        Arrays.sort(a);


        int left = 1;
        int right = (int) (side * 4L / k) + 1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(a, side, k, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;

    }

    private boolean check(long[] a, int side, int k, int low) {
        next:
        for (long start : a) { // 枚举第一个点
            // 最后一个数的最大值
            long end = start + side * 4L - low;
            long cur = start;
            for (int i = 0; i < k - 1; i++) {
                // 查找大于等于 cur + low 最近的点
                int j = lowerBound(a, cur + low);
                if (j == a.length || a[j] > end) { // 不能离第一个点太近
                    continue next;
                }
                cur = a[j];
            }
            return true;
        }
        return false;
    }

    private int lowerBound(long[] nums, long target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

}
