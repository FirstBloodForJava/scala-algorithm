package com.leetcode.interview.math_bit;

public class Solution_8 {

    /**
     * 缺失数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        /*
        给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
         */
        /*
        n == nums.length
        1 <= n <= 1e4
        0 <= nums[i] <= n
        nums 中的所有数字都 独一无二
         */
        /*
        等差数列求和
        异或位运算
         */
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] < n && nums[i] != i) {
                int j = nums[i];
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return n;
    }

}
