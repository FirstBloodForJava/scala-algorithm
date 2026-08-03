package com.oycm.week.lc2020.No183;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_1 {

    /**
     * 1403. <a href="https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order/description/">非递增顺序的最小子序列</a> 1288
     *
     * @param nums
     * @return
     */
    public List<Integer> minSubsequence(int[] nums) {
        /*
        给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格大于 未包含在该子序列中的各元素之和。
        如果存在多个解决方案，只需返回 长度最小 的子序列。如果仍然有多个解决方案，则返回 元素之和最大 的子序列。

        注意，题目数据保证满足所有约束条件的解决方案是 唯一 的。同时，返回的答案应当按 非递增顺序 排列。
         */
        /*
        1 <= nums.length <= 500
        1 <= nums[i] <= 100
         */
        /*
        排序 + 前缀和
        元素都大于 0，要想子序列长度最小，肯定要从越大的数开始选。而且长度相同时，要元素之和最大，也是要求从大数开始选。
         */
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        List<Integer> ans = new ArrayList<>();
        int i = nums.length - 1;
        int temp = 0;
        while (sum >= temp) {
            temp += nums[i];
            sum -= nums[i];
            ans.add(nums[i]);
            i--;
        }

        return ans;
    }

}
