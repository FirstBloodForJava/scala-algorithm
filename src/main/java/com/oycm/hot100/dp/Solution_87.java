package com.oycm.hot100.dp;

public class Solution_87 {

    /**
     * 300. <a href="https://leetcode.cn/problems/longest-increasing-subsequence/description/">最长递增子序列</a>
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        /*
        给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
        子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
         */
        /*
        子序列是子集型问题，可以有两者方式枚举子集：
            选/不选：由于要严格递增，这里还需要额外变量记录上一个选的元素
            枚举选哪个：下一个选的元素小于当前选的
        dfs(i) 表示为 nums[i] 结尾的最长递增子序列。
        枚举子序列的倒数第二个数的下标 j，如果 nums[j] < nums[i]，dfs(i) = dfs(j) + 1
         */
        int ans = 0;
        int n = nums.length;
        int[] memo = new int[n];
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, nums, memo));
        }

        return ans;
    }

    public int dfs(int i, int[] nums, int[] memo) {
        if (memo[i] != 0) return memo[i];
        int res = 0;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) res = Math.max(res, dfs(j, nums, memo));
        }
        return memo[i] = res + 1;
    }

}
