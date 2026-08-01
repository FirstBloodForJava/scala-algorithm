package com.oycm.month2026.august;

import java.util.Arrays;

public class Solution_1 {

    /**
     * 486. <a href="https://leetcode.cn/problems/predict-the-winner/description/">预测赢家</a>
     *
     * @param nums
     * @return
     */
    public boolean predictTheWinner(int[] nums) {
        /*
        给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
        玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。
        开始时，两个玩家的初始分值都是 0 。
        每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），取到的数字将会从数组中移除（数组长度减 1 ）。
        玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
        如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。你可以假设每个玩家的玩法都会使他的分数最大化。
         */
        /*
        动态规划
        题解思路：
        设玩家 1 的最终得分为 a，玩家 2 的最终得分为 b。如果 a >= b，那么玩家 1 获胜，否则玩家 2 获胜。
        a >= b 可以变形为 a - b >= 0。问题变成只需要关注得分之差 a-b 的值。
        用得分之差来看，为了获胜，玩家 1 需要最大化 a-b 的值，玩家 2 需要最小化 a-b 的值（最大化 b-a 的值）。
        也就是说，每个选择都需要最大化自己的得分减去对手的得分。

        解决问题：对于 nums 中的一个连续子数组，计算先手得分减去后手得分的最大值，
        所以定义 dfs(i, j) 表示剩余 nums 子数组 [i, j] 时，先手得分减去后手得分的最大值。
        对于 nums = [1, 5, 2]。
            玩家 1 先选择 nums[2]，那么玩家 1 的得分增加 nums[2]；
            子问题为：对于 nums' = [1, 5] 数组，玩家 2 选哪个整数，可以最大化（b-a 的值）；
            对于 nums'，设玩家 2 的得分为 b'，玩家 1 的得分为 a'，那么子问题表示为 dfs(0, 1) = b' - a'；
            要计算的原问题表示为 dfs(0, 2) = a - b；
        两者有什么关系？
        由于 a = nums[2] + a', b = b'，所以有：
            dfs(0, 2) = a - b = nums[2] + a' - b' = nums[2] - (b' - a') = nums[2] - dfs(0, 1)；
        那么就找到原问题和子问题的关系。
        一般地，如果 nums 剩余数组为 [i, j]，枚举先手移除的整数：
            枚举 i dfs(i, j) = nums[i] - dfs(i+1, j)；
            枚举 j dfs(i, j) = nums[j] - dfs(i, j-1)；
        两种情况取最大值。

        递归边界条件：dfs(i, i) = nums[i]。此时只有一个整数可选，先手得分为 nums[i]，后手得分为 0。
         */
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return dfs(0, n - 1, nums, memo) >= 0;
    }

    private int dfs(int i, int j, int[] nums, int[][] memo) {
        if (memo[i][j] != Integer.MIN_VALUE) {
            return memo[i][j];
        }
        if (i == j) {
            return memo[i][j] = nums[i];
        }
        return memo[i][j] = Math.max(nums[i] - dfs(i + 1, j, nums, memo),
                nums[j] - dfs(i, j - 1, nums, memo));
    }

}
