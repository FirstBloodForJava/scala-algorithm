package com.oycm.hot100.dp;

public class Solution_83 {

    /**
     * 198. <a href="https://leetcode.cn/problems/house-robber/description/">打家劫舍</a>
     *
     * @param nums 0 <= nums[i] <= 400
     * @return
     */
    public int rob(int[] nums) {
        /*
        f[i] = Math.max(f[i-2] + nums[i], f[i-1])
         */
        int n = nums.length;
        int f0 = 0, f1 = 0;
        for (int i = 0; i < n; i++) {
            int f = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = f;
        }
        return f1;
    }

}
