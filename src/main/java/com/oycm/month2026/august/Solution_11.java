package com.oycm.month2026.august;

import java.util.HashSet;
import java.util.Set;

public class Solution_11 {

    /**
     * 2996. <a href="https://leetcode.cn/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/description/">大于等于顺序前缀和的最小缺失整数</a> 1406
     *
     * @param nums
     * @return
     */
    public int missingInteger(int[] nums) {
        /*
        给你一个下标从 0 开始的整数数组 nums 。
        如果一个前缀 nums[0..i] 满足对于 1 <= j <= i 的所有元素都有 nums[j] = nums[j - 1] + 1 ，那么我们称这个前缀是一个 顺序前缀。
        特殊情况是，只包含 nums[0] 的前缀也是一个 顺序前缀 。
        请你返回 nums 中没有出现过的 最小 整数 x ，满足 x 大于等于 最长 顺序前缀的和。
         */
        /*
        两次遍历：
            第一次先找到最长顺序前缀，及其对应和
            第二次查找大于等于 s 不在 nums 中存在的最小整数
         */
        /*
        [46,8,2,4,1,4,10,2,4,10,2,5,7,3,1]
        只有 nums[0] 可以单独一个算 顺序前缀
         */
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int n = nums.length;
        int sum = nums[0];
        int j = 1;
        // 只考虑从 0 开始的数组
        while (j < n && nums[j] == nums[j - 1] + 1) {
            sum += nums[j++];
        }
        while (set.contains(sum)) {
            sum++;
        }
        return sum;
    }

}
