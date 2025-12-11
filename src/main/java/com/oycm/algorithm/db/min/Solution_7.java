package com.oycm.algorithm.db.min;


import java.util.Arrays;

public class Solution_7 {

    /**
     * 475. <a href="https://leetcode.cn/problems/heaters/description/">供暖器</a>
     *
     * @param houses  房屋的位置 数组
     * @param heaters 供暖器位置 数组
     * @return 求覆盖所有房屋的 供暖器 最小加热半径
     */
    public static int findRadius(int[] houses, int[] heaters) {
        /*
        先思考，给定一个半径 r，怎么判断 供暖器 是否能给所有房屋供暖？
        把 houses, heaters 按升序排列。
            houses[i] < heaters[j] - r, 任意供暖器都不能给 房屋 i 供暖, 半径不符合要求
            houses[i] > heaters[j] + r, j 供暖器不能给房屋 i 及 i 之后的房屋供暖, 当 j == heaters.length, 半径不符合要求
            heaters[j] - r <= houses[i] <= heaters[j] + r, 则 j 供暖器能给 i 房屋供暖
        不能供暖用 0 表示, 能供暖用 1 表示, 半径 [0, n] 表示供暖结果的数组具有单调性，可以使用二分查找 大于等于 1 的第一个 index
         */
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int n = houses.length, h = heaters.length;
        int max1 = Math.max(Math.abs(heaters[0] - houses[0]), Math.abs(heaters[0] - houses[n - 1]));
        int max2 = Math.max(Math.abs(heaters[h - 1] - houses[0]), Math.abs(heaters[h - 1] - houses[n - 1]));
        int l = -1;
        int r = Math.min(max1, max2) + 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (enable(houses, heaters, mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return r;
    }

    public static boolean enable(int[] houses, int[] heaters, int radio) {
        int j = 0;
        int h = heaters.length;
        for (int house : houses) {
//            if (house < heaters[j] - radio) {
//                return false;
//            }
            while (j < h && (house < heaters[j] - radio || house > heaters[j] + radio)) {
                j++;
            }
            if (j == h) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(findRadius(new int[]{1, 2, 3, 5, 15}, new int[]{2, 30}));
//        System.out.println(findRadius(new int[]{1, 2, 3}, new int[]{2}));
//        System.out.println(findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
//        System.out.println(findRadius(new int[]{1, 5}, new int[]{2}));
    }

}
