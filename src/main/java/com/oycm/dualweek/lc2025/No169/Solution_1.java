package com.oycm.dualweek.lc2025.No169;

public class Solution_1 {

    /**
     * 3736. <a href="https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-iii/description/">最小操作次数使数组元素相等 III</a> 1252
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        /*
        给你一个整数数组 nums。
        在一步操作中，你可以将任意单个元素 nums[i] 的值 增加 1。
        返回使数组中的所有元素都 相等 所需的 最小总操作次数 。
         */
        /*
        1 <= nums.length <= 100
        1 <= nums[i] <= 100
         */
        /*
        只能进行增加操作，找出最大数 mx，所有小于 mx 增长值就是操作次数
         */
        int mx = 0;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        int ans = 0;
        for (int num : nums) {
            ans += mx - num;
        }

        return ans;
    }

}
