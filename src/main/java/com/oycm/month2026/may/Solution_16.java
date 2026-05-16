package com.oycm.month2026.may;

public class Solution_16 {

    /**
     * 154. <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/description/">寻找旋转排序数组中的最小值 II</a>
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        /*
        已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
        例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
            若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
            若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
        数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]]。
        可能存在 重复 元素值的数组 nums，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
        找出数组中的最小元素
         */
        /*
        题解思路：这里与 153 的区别是有重复元素，如果 nums[mid] == nums[n-1] 就无法判断答案在左边还是再右边。
        当 nums[mid] == nums[n-1] 就去掉 n-1 这个元素，问题变成了 n-1 的子问题。
        这里会不会去掉最小值呢？不会
            因为 nums[mid] == nums[n-1]，如果是最小值，则最小值还在数组中；
            如果不是最小值，则排除了一个错误答案
         */
        int l = -1;
        int r = nums.length - 1;
        while (l + 1 < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == nums[r]) {
                r--;
            } else if (nums[mid] > nums[r]) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return nums[r];
    }

}
