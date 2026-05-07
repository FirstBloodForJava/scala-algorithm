package com.oycm.dp.a.rob;

public class Solution_2 {

    /**
     * 213. <a href="https://leetcode.cn/problems/house-robber-ii/description/">打家劫舍 II</a>
     *
     * @param nums 环形数组
     * @return
     */
    public int rob(int[] nums) {
        /*
        如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
        给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
         */
        /*
        偷 0, 1, n-1 不能偷, [2, n-2] 范围，非环形数组可偷答案
        不偷 0, [1, n-1] 范围，非环形数组可偷答案
         */
        int n = nums.length;
        return Math.max(rob(2, n - 1, nums) + nums[0], rob(1, n, nums));
    }

    public int rob(int start, int end, int[] nums) {
        int f0 = 0, f1 = 0;
        for (int i = start; i < end; i++) {
            int f = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = f;
        }
        return f1;
    }

    /*
        int n = nums.length;
        int[] m1 = new int[n];
        Arrays.fill(m1, -1);
        int[] m2 = new int[n];
        Arrays.fill(m2, -1);
        return Math.max(dfsZero(n - 2, nums, m1), dfsOne(n - 1, nums, m2));
     */
    public int dfsZero(int i, int[] nums, int[] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) return memo[i];
        return memo[i] = Math.max(dfsZero(i - 1, nums, memo), dfsZero(i - 2, nums, memo) + nums[i]);
    }

    public int dfsOne(int i, int[] nums, int[] memo) {
        if (i < 1) {
            return 0;
        }
        if (memo[i] != -1) return memo[i];
        return memo[i] = Math.max(dfsOne(i - 1, nums, memo), dfsOne(i - 2, nums, memo) + nums[i]);
    }

}
