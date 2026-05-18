package com.oycm.hot100.binary_search;

public class Solution_65 {

    /**
     * 34. <a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/">在排序数组中查找元素的第一个和最后一个位置</a>
     *
     * @param nums   nums.length [0, 1e5]
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        /*
        给你一个按照非递减（nums[i] <= nums[i+1]）顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
        如果数组中不存在目标值 target，返回 [-1, -1]。
         */
        /*
        target 的开始位置，在非递减数组总查询第一个大于等于 target 的下标
        target 的结束位置，在非递减数组总查询第一个大于等于 target+1 的下标，再减 1

         */
        int[] ans = {-1, -1};
        int start = lowerBound(nums, target);
        if (start == nums.length || nums[start] != target) {
            return ans;
        }
        ans[0] = start;
        ans[1] = lowerBound(nums, target + 1) - 1;

        return ans;
    }

    public int lowerBound(int[] nums, int target) {
        int l = -1;
        int r = nums.length;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] < target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }


}
