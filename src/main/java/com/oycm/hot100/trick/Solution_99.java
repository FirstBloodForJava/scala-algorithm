package com.oycm.hot100.trick;

public class Solution_99 {

    /**
     * 31. <a href="https://leetcode.cn/problems/next-permutation/description/">下一个排列</a>
     *
     * @param nums 1 <= nums.length <= 100; 0 <= nums[i] <= 100
     */
    public void nextPermutation(int[] nums) {
        /*
        整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
        整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
        更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
        如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
        arr = [1,2,3] 的下一个排列是 [1,3,2] 。
        arr = [3,2,1] 的下一个排列是 [1,2,3]
         */
        // 从右往左，找到第一个 nums[i] < nums[i+1] 的下标
        int idx = nums.length - 2;
        while (idx >= 0 && nums[idx] >= nums[idx + 1]) {
            idx--;
        }
        if (idx >= 0) {
            /*
            [idx+1, n-1] 是非递增的 nums[i] >= nums[i+1]
            在后面找到第一个 大于 nums[idx] 的数和他交换，
             */
            int j = nums.length - 1;
            while (j >= 0 && nums[idx] >= nums[j]) {
                j--;
            }
            swap(nums, idx, j);
        }
        // [idx+1, n-1] 反转
        reverse(nums, idx + 1);
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left++, right--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
