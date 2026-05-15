package com.oycm.month2026.may;

public class Solution_15 {

    /**
     * 153. <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/description/">寻找旋转排序数组中的最小值</a>
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
        元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
        找出并返回数组中的 最小元素 。
         */
        /*
        注意：原数组旋转 m 次后，最小值下标位于 nums[m]，nums[m-1] > nums[m] 且 nums[m] < nums[m+1]
        [0, m-1] 升序 [m, n-1] 升序 nums[0] > nums[n-1]
        二分查找，x = nums[mid] 和最小值的位置关系，和谁比较能确定位置关系？
            如果 x > nums[n-1]，说明：
                数组分为了两个递增段；
                第一段所有元素都大于第二段元素
                x 在第一段；
                最小值在第二段；
                x 在最小值的 左边
            如果 x <= nums[n-1]，说明：
                x 在第二段，或者没有分成两段
                x 要么是最小值，要么在最小值 右边
         */
        int n = nums.length;
        int l = 0;
        int r = nums.length - 2;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[n - 1]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return nums[l];
    }

}
