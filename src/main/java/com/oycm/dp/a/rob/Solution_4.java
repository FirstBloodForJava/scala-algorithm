package com.oycm.dp.a.rob;

public class Solution_4 {

    /**
     * 3840. <a href="https://leetcode.cn/problems/house-robber-v/description/">打家劫舍 V</a> 1619
     *
     * @param nums
     * @param colors
     * @return
     */
    public long rob(int[] nums, int[] colors) {
        /*
        两个长度为 n 的整数数组 nums 和 colors，
        nums[i] 是第 i 间房屋中的金额，而 colors[i] 是该房屋的颜色代码。
        如果两间 相邻 的房屋具有 相同 的颜色代码，则你 不能同时偷窃 它们。
         */
        /*
        计算相同颜色的区间，分段打家劫舍
         */
        long ans = 0;
        int start = 0;
        int n = colors.length;
        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[start]) {
                ans += rob(start, i, nums);
                start = i;
            }
        }
        ans += rob(start, n, nums);

        return ans;
    }

    public long rob(int start, int end, int[] nums) {
        long f0 = 0, f1 = 0;
        for (int i = start; i < end; i++) {
            long f = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = f;
        }
        return f1;
    }

}
