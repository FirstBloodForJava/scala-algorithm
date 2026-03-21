package com.oycm.algorithm.h.log_trick;

public class Solution_4 {

    /**
     * 2411. <a href="https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or/description/">按位或最大的最小子数组长度</a> 1938
     *
     * @param nums 非负整数
     * @return
     */
    public int[] smallestSubarrays(int[] nums) {
        /*
        ans[i] 表示 nums[i, k] k [i, n-1] 异或运算值最大，且子数组数量最小
         */
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            ans[i] = 1;
            // nums[j] 表示固定 nums[i] 为右端点的最大或值, 能继续增大, 说明 j 亦可更长
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j] ; j--) {
                nums[j] |= x;
                ans[j] = i - j + 1;
            }
        }

        return ans;
    }

}
