package com.oycm.hot100.binary_search;

public class Solution_63 {

    /**
     * 35. <a href="https://leetcode.cn/problems/search-insert-position/description/">搜索插入位置</a>
     *
     * @param nums   无重复元素 的 升序 排列数组
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        /*
        给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
         */
        /*
        开区间 二分查找，比较 nums[mid] 和 target 的关系，
            如果 nums[mid] == target，直接返回 mid；
            如果 nums[mid] > target，r = mid，r 及右边的都比 target 大，继续下一轮循环
            如果 nums[mid] < target，l = mid，l 及左边的都比 target 小，继续下一轮循环
            直到 l + 1 < r 不成立，此时 nums[l] 是最后一个小于 target 的下标
         */
        /*
        也可以转变成，求 大于等于 target 的第一个下标
         */
        int l = -1;
        int r = nums.length;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return l + 1;
    }

}
