package com.oycm.month2026.april;

import java.util.Arrays;

public class Solution_28 {

    /**
     * 2033. <a href="https://leetcode.cn/problems/minimum-operations-to-make-a-uni-value-grid/">获取单值网格的最小操作数</a> 1672
     *
     * @param grid m x n 的二维整数网格
     * @param x    每一次操作，你可以对 grid 中的任一元素 加 x 或 减 x
     * @return
     */
    public int minOperations(int[][] grid, int x) {
        /*
        单值网格 是全部元素都相等的网格。
        返回使网格化为单值网格所需的 最小 操作数。如果不能，返回 -1 。
         */
        /*
        什么情况下，不能变成单值网格？
            grid 转换成一维数组，任意两元素之间的差 (nums[i + 1] - nums[i]) % x == 0 才能变成相同的值
            题解证明：根据 (grid[i][j] + kx) % x == grid[i][j] % x，所有 grid 中所有 % x 的结果要相等
        最小操作次数对于的单值能确定吗？
            最小距离和定理
            排序后的中位数
         */
        int m = grid.length, n = grid[0].length;
        int[] nums = new int[m * n];
        int idx = 0;
        int target = grid[0][0] % x;
        for (int[] row : grid) {
            for (int v : row) {
                if (v % x != target) return -1;
                nums[idx++] = v;
            }
        }
        Arrays.sort(nums);
        int ans = 0;
        int mid = idx / 2;
        for (int v : nums) {
            ans += Math.abs(v - nums[mid]);
        }
        return ans / x;
    }

}
