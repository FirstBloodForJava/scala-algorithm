package com.oycm.hot100.greedy;

public class Solution_78 {

    /**
     * 55. <a href="https://leetcode.cn/problems/jump-game/description/">跳跃游戏</a>
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        /*
        给你一个非负整数数组 nums ，你最初位于数组的 第一个下标。
        数组中的每个元素代表你在该位置可以跳跃的最大长度。
        判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false。
         */
        /*
        只要中间不跳到值为 0 的下标，就能跳到 n-1
         */
        /*
        题解思路，维护当前 i 能跳到的最远下标 mx，当 i > mx，说明 前一个是最远下标，下标值为 0
         */
        int mx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > mx) return false;
            mx = Math.max(mx, i + nums[i]);
            if (mx >= nums.length - 1) return true;
        }
        return true;
    }

}
