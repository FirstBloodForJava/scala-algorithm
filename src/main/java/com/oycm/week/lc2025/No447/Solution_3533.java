package com.oycm.week.lc2025.No447;

import java.util.Arrays;

public class Solution_3533 {

    /**
     * 3533. 判断连接可整除性
     * <br>
     * 3533. <a href="https://leetcode.cn/problems/concatenated-divisibility/description/">判断连接可整除性</a> 2257
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] concatenatedDivisibility(int[] nums, int k) {
        /*
        给你一个正整数数组 nums 和一个正整数 k。
        当 nums 的一个 排列 中的所有数字，按照排列顺序 连接其十进制表示 后形成的数可以 被 k  整除时，我们称该排列形成了一个 可整除连接。
        返回能够形成 可整除连接 且 字典序 最小 的排列（按整数列表的形式表示）。如果不存在这样的排列，返回一个空列表。
         */
        /*
        1 <= nums.length <= 13
        1 <= nums[i] <= 1e5
        1 <= k <= 100
         */
        /*
        全排列暴力搜索
         */
        Arrays.sort(nums);
        int n = nums.length;
        int[] pow10 = new int[n];
        for (int i = 0; i < n; i++) {
            pow10[i] = (int) Math.pow(10, String.valueOf(nums[i]).length());
        }
        int[] ans = new int[n];
        boolean[][] vis = new boolean[1 << n][k];
        if (!dfs((1 << n) - 1, 0, nums, pow10, k, vis, ans)) {
            return new int[]{};
        }

        return ans;
    }

    public boolean dfs(int s, int x, int[] nums, int[] pow10, int k, boolean[][] vis, int[] ans) {
        if (s == 0) {
            return x == 0;
        }
        if (vis[s][x]) return false;

        vis[s][x] = true;
        // 枚举在 s 中选下标 i
        for (int i = 0; i < nums.length; i++) {
            if ((s & (1 << i)) > 0 && dfs(s ^ (1 << i), (x * pow10[i] + nums[i]) % k, nums, pow10, k, vis, ans)) {
                ans[nums.length - Integer.bitCount(s)] = nums[i];
                return true;
            }
        }
        return false;
    }

}
