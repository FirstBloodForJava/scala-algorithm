package com.oycm.algorithm.h.other;


public class Solution_1 {

    /**
     * 136. <a href="https://leetcode.cn/problems/single-number/description/">只出现一次的数字</a>
     *
     * @param nums nums 中其他元素都出现了 2 次
     * @return 找出只出现一次数字的值
     */
    public int singleNumber(int[] nums) {
        /*
        利用异或 a ^ a = 0, a ^ 0 = a 性质
         */
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

}
