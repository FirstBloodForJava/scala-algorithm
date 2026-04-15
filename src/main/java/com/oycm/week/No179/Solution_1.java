package com.oycm.week.No179;

public class Solution_1 {

    /**
     * 3880. <a href="https://leetcode.cn/problems/minimum-absolute-difference-between-two-values/description/">两个值之间的最小绝对差值</a> 1257
     *
     * @param nums nums.length [1, 100]
     * @return
     */
    public int minAbsoluteDifference(int[] nums) {
        /*
        nums[i] == 1 且 nums[j] == 2，则称下标对 (i, j) 为 有效 的，求下标对有效的最小绝对差
         */
        int n = nums.length;
        int ans = n;
        int one = -n;
        int two = -n;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                one = i;
                ans = Math.min(ans, i - two);
            }
            if (nums[i] == 2) {
                two = i;
                ans = Math.min(ans, i - one);
            }
        }

        return ans == n ? -1 : ans;
    }
}
