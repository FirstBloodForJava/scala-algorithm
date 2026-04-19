package com.oycm.algorithm.bb;

import java.util.Arrays;

public class Solution_8 {

    /**
     * 1385. <a href="https://leetcode.cn/problems/find-the-distance-value-between-two-arrays/description/">两个数组间的距离值</a> 1235
     *
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        /*
        距离值定义：为符合此距离要求的元素数目。
        对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d。
         */
        /*
        由于对 arr1, arr2 下标没有要求，可以先对数组进行排序。
        arr1[i], arr2 不存在元素在 [arr1[i] - d, arr1[i] + d]
        题解思路：排序后维持每个 arr1[i] - d >= arr2[j] 的最小下标 j
        如果 j == arr2.length 较小值都满足 arr2[j] < arr1[i] - d, 数轴的角度来看，肯定后续的符合要求
        如果 arr2[j] <= x + d, 则不符合要求，在区间范围
         */
        int ans = 0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int j = 0;
        for (int x : arr1) {
            while (j < arr2.length && arr2[j] < x - d) {
                j++;
            }
            if (j == arr2.length || arr2[j] > x + d) {
                ans++;
            }
        }

        return ans;
    }

}
