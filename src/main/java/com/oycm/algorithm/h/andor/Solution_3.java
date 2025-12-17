package com.oycm.algorithm.h.andor;


public class Solution_3 {

    /**
     * 2419. <a href="https://leetcode.cn/problems/longest-subarray-with-maximum-bitwise-and/description/">按位与最大的最长子数组</a> 1496
     *
     * nums 子数组按 & 运算得到最大值的最大长度
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        /*
        nums[i] & nums[i] 与越多不同的数，只会越来越小，本质是找 nums 最大值元素的最大连续长度
         */
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int ans = 0;
        int n = nums.length;
        int l = 0;
        while (l < n) {
            int cnt = 0;
            while (l < n && (nums[l] & max) == max) {
                l++;
                cnt++;
            }
            ans = Math.max(ans, cnt);
            l++;
        }

        return ans;
    }

    public int answer(int[] nums) {
        int ans = 0, cnt = 0, mx = 0;
        for (int num : nums) {
            if (num > mx) {
                ans = 1;
                cnt = 1;
                mx = num;
            } else if (num == mx) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
}
