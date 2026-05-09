package com.oycm.hot100.substring;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {

    /**
     * 560. <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/">和为 K 的子数组</a>
     *
     * @param nums nums[i] [-1000, 1000], nums.length [1, 2e4]
     * @param k    [-1e7, 1e7]
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        /*
        给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
        子数组是数组中元素的连续非空序列。
         */
        /*
        暴力计算思路：
        外层循环 i [0, n-1];
            内存循环 j = i [j, n-1] 要遍历所有，不能提前结束，和为 k 时，增加次数
        前缀和 + 两数之和
        s(i) - s(?) = k; (?, i) 连续之数组和为 k
        todo 思考题可做
         */
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            ans += map.getOrDefault(sum - k, 0);
            map.merge(sum, 1, Integer::sum);
        }

        return ans;
    }

}
