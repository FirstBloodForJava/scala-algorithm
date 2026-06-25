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
        /*
        从右往左，找到第一个可以增大的数（nums[i] < nums[i+1]），两种相交换的排列字典序是大于当前的，但是不一定是最小的。
        如果把右边第一个大于 nums[i] 的数交换过来，后面剩余的数从小到达排列，就是下一个最小字典序
        [1, 5, 4, 3, 1] => [3, 5, 4, 1, 1] => [3, 1, 1, 4, 5]
        当我们找到 nums[i] < nums[i+1] 这个下标 i 时，(i, n-1] 这边数组是什么情况呢？数组是非递增 nums[j] >= nums[j+1]
        把 nums[i] 和 右边第一个大于 nums[i] 的交换位置后，会影响 (i, n-1] 子数组的顺序吗？
        不会，假如交换的位置是 j，则意味着 [j+1, n-1] 的子数组都是小于等于 nums[i]，原来的顺序没变，意味着交换后 nums[j, n-1] 还是非递增的，
        接下来就看 [i+1, j] 的顺序是否被影响，nums[j-1] >= nums[j]，nums[j] 变得更小，显然 [i+1, j] 还是非递增的，拼接 [i+1, j] [j, n-1]，整数后缀子数组，还是非递增，
        所以子要把后面的子数组翻转即可 [idx+1, n-1] 翻转。
        当整个子序列最大时，就是非递增的，此时从右往左会找到 -1，此时也是把 [0, n-1] 翻转，就是最小字典序（升序）
         */
        // 从右往左，找到第一个 nums[i] < nums[i+1] 的下标
        int idx = nums.length - 2;
        while (idx >= 0 && nums[idx] >= nums[idx + 1]) {
            idx--;
        }
        if (idx >= 0) {
            /*
            [idx+1, n-1] 是非递增的 nums[i] >= nums[i+1]
            在后面找到第一个 大于 nums[idx] 的数和他交换
            [idx+1, n-1] 始终非递增
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
