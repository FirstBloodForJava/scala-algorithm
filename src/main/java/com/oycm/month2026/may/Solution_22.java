package com.oycm.month2026.may;

import com.oycm.hot100.binary_search.Solution_66;

public class Solution_22 {

    /**
     * 33. <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/description/">搜索旋转排序数组</a>
     *
     * @param nums   nums.length [1, 5000], nums 中的每个值都不相同
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        /*
        整数数组 nums 按升序排列，数组中的值 互不相同。
        如果数组 nums[0] > nums[n-1] 说明数组进行了旋转。
        旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1。
         */
        /*
        在两段升序数组中，查询第一个 大于等于 target 的下标。
        二分查找 x = nums[mid] 和 target 位置关系，根据数组分段情况判断 x 个 target 的位置关系。
        这样写，代码较复杂。
        不如分类讨论 x 和 target 的位置关系情况：
            x 在 第一段，target 在第二段 x > nums[n-1] && target <= nums[n-1]，l = mid
            x 在 第二段，target 在第一段 target > nums[n-1] && x <= nums[n-1]，r = mid
            x 和 target 在同段，就可以按正常的二分查找返回
         */
        return new Solution_66().search(nums, target);
    }
}
