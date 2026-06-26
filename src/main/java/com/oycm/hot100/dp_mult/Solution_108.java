package com.oycm.hot100.dp_mult;

import java.util.Arrays;

public class Solution_108 {

    /**
     * 312. <a href="https://leetcode.cn/problems/burst-balloons/description/">戳气球</a>
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        /*
        有 n 个气球，编号为 0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
        现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
        这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
        求所能获得硬币的最大数量。
         */
        /*
        dfs(i, j) 在区间枚举 k 作为最后一个气球被戳破获得的最大值
         */
        int n = nums.length;
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        System.arraycopy(nums, 0, val, 1, n);
        int[][] memo = new int[n + 2][n + 2];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, n + 1, val, memo);
    }

    public int dfs(int i, int j, int[] val, int[][] memo) {
        if (i >= j - 1) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int res = 0;
        for (int k = i + 1; k < j; k++) {
            int temp = val[i] * val[k] * val[j];
            temp += dfs(i, k, val, memo) + dfs(k, j, val, memo);
            res = Math.max(res, temp);
        }
        return memo[i][j] = res;
    }

}
