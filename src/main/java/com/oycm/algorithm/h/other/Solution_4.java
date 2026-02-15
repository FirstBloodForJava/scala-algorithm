package com.oycm.algorithm.h.other;

public class Solution_4 {

    /**
     * 137. <a href="https://leetcode.cn/problems/single-number-ii/description/">只出现一次的数字 II</a>
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        /*
        题解思路：统计每个比特位上 1 出现的次数，模 3 的结果就是出现一次的比特位
         */
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int bitCnt = 0;
            for (int x : nums) {
                bitCnt += x >> i & 1;
            }
            ans |= bitCnt % 3 << i;
        }
        return ans;
    }

}
