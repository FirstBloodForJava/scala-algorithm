package com.oycm.dp.d.lis.basic;

import java.util.List;

public class Solution_3 {

    /**
     * 2826. <a href="https://leetcode.cn/problems/sorting-three-groups/description/">将三个组排序</a> 1721
     *
     * @param nums
     * @return
     */
    public int minimumOperations(List<Integer> nums) {
        /*
        给你一个整数数组 nums。nums 的每个元素是 1，2 或 3。
        在每次操作中，你可以删除 nums 中的一个元素。返回使 nums 成为 非递减 顺序所需操作数的 最小值。
         */
        /*
        求 n - nums 最长非递减子序列
         */
        int gn = 0;
        for (Integer x : nums) {
            int j = lowerBound(nums, gn, x);
            nums.set(j, x);
            if (j == gn) {
                gn++;
            }

        }
        return nums.size() - gn;
    }

    public int lowerBound(List<Integer> nums, int r, int target) {
        int l = -1;
        // 开区间，查询 nums 中第一个大于 target 的下标
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums.get(mid) > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }





}
