package com.oycm.algorithm.ba.extend;

public class Solution_4 {

    /**
     * 41. <a href="https://leetcode.cn/problems/first-missing-positive/description/">缺失的第一个正数</a>
     *
     * @param nums 未排序的整数数组
     * @return 其中没有出现的最小的正整数
     */
    public int firstMissingPositive(int[] nums) {
        /*
        [1, n] 在数组 nums 中交换位置
         */
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (1 <= nums[i] && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 交换
                int j = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

}
