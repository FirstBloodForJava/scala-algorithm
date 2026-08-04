package com.oycm.month2026.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_4 {

    /**
     * 3731. <a href="https://leetcode.cn/problems/find-missing-elements/description/">找出缺失的元素</a> 1217
     *
     * @param nums
     * @return
     */
    public List<Integer> findMissingElements(int[] nums) {
        /*
        给你一个整数数组 nums ，数组由若干 互不相同 的整数组成。
        数组 nums 原本包含了某个范围内的 所有整数 。但现在，其中可能 缺失 部分整数。
        该范围内的 最小 整数和 最大 整数仍然存在于 nums 中。
        返回一个 有序 列表，包含该范围内缺失的所有整数，并 按从小到大排序。如果没有缺失的整数，返回一个 空 列表。
         */
        /*
        排序：初始 start = nums[i]，如果 start + 1 != nums[i+1] start++ 就是缺失数字
        i < n - 1
         */
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int start = nums[i] + 1;
            while (start < nums[i + 1]) {
                ans.add(start++);
            }
        }

        return ans;
    }

}
