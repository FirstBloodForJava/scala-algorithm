package com.oycm.week.No179;

public class Solution_1 {

    public int minAbsoluteDifference(int[] nums) {
        /*

         */
        int n = nums.length;
        int ans = n;
        int one = -n;
        int two = -n;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                one = i;
                ans = Math.min(ans,i - two);
            }
            if (nums[i] == 2) {
                two = i;
                ans = Math.min(ans, i - one);
            }
        }

        return ans == n ? -1 : ans;
    }
}
