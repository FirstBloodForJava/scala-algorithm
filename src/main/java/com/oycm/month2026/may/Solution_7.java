package com.oycm.month2026.may;

public class Solution_7 {

    /**
     * 3660. <a href="https://leetcode.cn/problems/jump-game-ix/description/">跳跃游戏 IX</a> 2187
     *
     * @param nums
     * @return
     */
    public int[] maxValue(int[] nums) {
        /*
        从任意下标 i 出发，你可以根据以下规则跳跃到另一个下标 j：
            仅当 nums[j] < nums[i] 时，才允许跳跃到下标 j，其中 j > i。
            仅当 nums[j] > nums[i] 时，才允许跳跃到下标 j，其中 j < i。
        对于每个下标 i，找出从 i 出发且可以跳跃 任意 次，能够到达 nums 中的 最大值 是多少。
         */
        /*
        题解思路：ans[0] 和 ans[n-1]，哪个更好计算？
        对于 ans[n-1]，它一定能取到最大值：
            max(nums) == nums[n-1], 自己就是最大值。
            max(nums) > nums[n-1], j < n-1，往下标更小处跳，能获得最大值。
        [2, 3, 1] 对于 ans[0], 2 -> 1 -> 3
        倒着计算会更好，看 ans[i] 是否能跳到最大值？不能跳到数组的最大值该怎么计算？
        定义 i -> i+1 为往前跳(较小值)，i -> i-1 为往后跳(较大值)
        [1, 2, 8, 6, 5, 4, 3] 2 和 8 之间存在一个分界线，左边的最大值 <= 右边的最小值，这种情况下，无法玩前跳
        一般地，设 preMax[i] 表示 [0, i] 的最大值，sufMin[i+1] 表示 [i+1, n-1] 的最小值：
        如果 preMax[i] <= sufMin[i+1]：
            设 p 表示 [0, i] 任意下标，q 表示 [i+1, n-1] 任意下标，则 nums[i] <= nums[p] <= preMax[i] <= sufMin[i+1] <= nums[p]，
            p 不能通过任意方式跳到 q。nums[i] 只能在 [0, i] 区间跳跃，取到 preMax[i] 最大值。
        如果 preMax[i] > sufMin[i+1]：
            i 可以先跳到 preMax[i] 所在位置 p，p 可以跳跃到 sufMin[i+1] 所在位置 q，
            如果 [i+1, n-1] 中所有元素都不同，如果 q != i + 1，则 q 可以跳到 i + 1，等价 i 可以 跳到 i+1，取和 i+1 相等的值
            如果 [i+1, n-1] 中存在相同的最小值，看它是否能跳到 i+1，无论哪种情况都能到 i+1
         */
        int n = nums.length;
        // ans 初始为 [0, i] 的最大值
        int[] ans = new int[n];
        ans[0] = nums[0];
        for (int i = 1; i < n; i++) {
            ans[i] = Math.max(ans[i - 1], nums[i]);
        }
        int sufMin = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (ans[i] > sufMin) {
                ans[i] = ans[i + 1];
            }
            sufMin = Math.min(sufMin, nums[i]);

        }

        return ans;
    }

}
