package com.oycm.algorithm.h.log_trick;

public class Solution_3 {

    /**
     * 3097. 或值至少为 K 的最短子数组 II 1891
     *
     * @param nums
     * @param k
     * @return
     */
    public int minimumSubarrayLength(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x >= k) {
                min = 1;
                break;
            }
            for (int j = i - 1; j >= 0 && ((x | nums[j]) != nums[j]); j--) {
                nums[j] = nums[j] | x;
                if (nums[j] >= k) {
                    min = Math.min(min, i - j + 1);
                }
            }
        }


        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
