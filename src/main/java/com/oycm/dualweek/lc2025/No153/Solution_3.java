package com.oycm.dualweek.lc2025.No153;

public class Solution_3 {

    /**
     * 3500. <a href="https://leetcode.cn/problems/minimum-cost-to-divide-array-into-subarrays/description/">将数组分割为子数组的最小代价</a> 2569
     *
     * @param nums
     * @param cost
     * @param k
     * @return
     */
    public long minimumCost(int[] nums, int[] cost, int k) {
        /*
        给你两个长度相等的整数数组 nums 和 cost，和一个整数 k。
        你可以将 nums 分割成多个子数组。第 i 个子数组由元素 nums[l..r] 组成，其代价为：
            (nums[0] + nums[1] + ... + nums[r] + k * i) * (cost[l] + cost[l + 1] + ... + cost[r])。
        注意，i 表示子数组的顺序：第一个子数组为 1，第二个为 2，依此类推。
        返回通过任何有效划分得到的 最小 总代价。
        子数组 是一个连续的 非空 元素序列。
         */
        /*
        1 <= nums.length <= 1000
        cost.length == nums.length
        1 <= nums[i], cost[i] <= 1000
        1 <= k <= 1000
         */
        /*
        f[i][j] 表示长为 [0, i] 的子数组划分为 j 个子数组的最小代价。
         */
        return 0;
    }

}
