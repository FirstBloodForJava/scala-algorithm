package com.oycm.dp.a.rob;

public class Solution_1 {

    /**
     * 198. <a href="https://leetcode.cn/problems/house-robber/description/">打家劫舍</a>
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        /*
        如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
        给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
         */
        /*
        dfs(i) = max(dfs(i - 2) + nums[i], dfs(i-1))
        dfs(i) 表示从前 i 个房子中得到的最高金额
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

    public int dfs(int i, int[] nums, int[] memo) {
        if (i < 0) return 0;
        if (memo[i] != -1) return memo[i];
        return memo[i] = Math.max(dfs(i - 1, nums, memo), dfs(i - 2, nums, memo) + nums[i]);
    }

}
