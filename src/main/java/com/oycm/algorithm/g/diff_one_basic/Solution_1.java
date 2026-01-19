package com.oycm.algorithm.g.diff_one_basic;

import java.util.List;

public class Solution_1 {

    /**
     * 2848. <a href="https://leetcode.cn/problems/points-that-intersect-with-cars/description/">与车相交的点</a> 1230
     *
     * @param nums
     * @return
     */
    public int numberOfPoints(List<List<Integer>> nums) {
        /*
        求 [nums[i][0], nums[i][1]] 表示 停放汽车的起始点和终点，求数轴上被车任意覆盖的点数目
        创建一个 数组 a，将 [nums[i][0], nums[i][1]] 范围内的下标都加一，则 a[i] 大于 0 的就是被覆盖的点。
        可以初始化一个都是 0 的数组，用来表示差分数组
        将 a [i, j] 区间都增加 1 就是 d[i] += 1, d[j+1] -= 1
        还原差分数组为 a，a[i] > 0 表示这个点被覆盖
         */
        int max = 0;
        for (int i = 0; i < nums.size(); i++) {
            max = Math.max(nums.get(i).get(1), max);
        }
        // 因为 max+1 减少 1，
        int[] d = new int[max + 2];
        for (int i = 0; i < nums.size(); i++) {
            d[nums.get(i).get(0)] += 1;
            d[nums.get(i).get(1) + 1] -= 1;
        }
        int ans = 0;
        int s = 0;
        for (int i = 0; i < d.length; i++) {
            s += d[i];
            if (s > 0) {
                ans++;
            }
        }

        return ans;
    }

}
