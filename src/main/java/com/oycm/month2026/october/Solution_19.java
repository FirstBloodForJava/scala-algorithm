package com.oycm.month2026.october;

import java.util.ArrayList;
import java.util.List;

public class Solution_19 {

    /**
     * 2089. <a href="https://leetcode.cn/problems/find-target-indices-after-sorting-array/description/">找出数组排序后的目标下标</a> 1152
     *
     * @param nums
     * @param target
     * @return
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        /*
        给你一个下标从 0 开始的整数数组 nums 以及一个目标元素 target 。
        目标下标 是一个满足 nums[i] == target 的下标 i 。
        将 nums 按 非递减 顺序排序后，返回由 nums 中目标下标组成的列表。如果不存在目标下标，返回一个 空 列表。返回的列表必须按 递增 顺序排列。
         */
        int less = 0;
        int equal = 0;
        for (int x : nums) {
            if (x < target) {
                less++;
            } else if (x == target) {
                equal++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < equal; i++) {
            ans.add(less + i);
        }
        return ans;

    }
}
