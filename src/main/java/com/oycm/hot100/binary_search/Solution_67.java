package com.oycm.hot100.binary_search;

public class Solution_67 {

    /**
     * 153. <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/description/">寻找旋转排序数组中的最小值</a>
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        /*
        二分查找 x = nums[mid] 和 最小值的位置关系
            如果 x > nums[n-1] x 在第一段，最小值在第二段，最小值在 x 的右边 l = mid；
            如果 x <= nums[n-1] x 在第二段，或只有一个段 x 就是最小值，或 最小值在 x 的左边
         */
        int last = nums[nums.length - 1];
        int l = -1;
        int r = nums.length - 1;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > last) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return nums[r];
    }

}
