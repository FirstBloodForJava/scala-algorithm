package com.oycm.month2026.may;

public class Solution_29 {

    /**
     * 3300. <a href="https://leetcode.cn/problems/minimum-element-after-replacement-with-digit-sum/description/">替换为数位和以后的最小元素</a> 1181
     *
     * @param nums
     * @return
     */
    public int minElement(int[] nums) {
        /*
        给你一个整数数组 nums 。请你将 nums 中每一个元素都替换为它的各个数位之 和。
        请你返回替换所有元素以后 nums 中的 最小 元素。
         */
        int ans = Integer.MAX_VALUE;

        for (int x : nums) {
            int temp = 0;
            while (x > 0) {
                temp += x % 10;
                x /= 10;
            }
            ans = Math.min(temp, ans);
        }

        return ans;
    }

}
