package com.oycm.algorithm.h.disposition;

public class Solution_1 {

    /**
     * 477. 汉明距离总和
     * <a href="https://leetcode.cn/problems/total-hamming-distance/description/"></a>
     * <p>
     * 汉明距离: 两个数字对应二进制位不同的位置的数目
     *
     * @param nums
     * @return 求 nums 中任意两个数之间的 汉明距离的总合
     */
    public int totalHammingDistance(int[] nums) {
        /*
        题解思路: 统计 nums[i] 中第 i 位, 1 的数量, 对于 0 的数量就是 n - c, 这个位置的汉明距离和 = c * (n - c)
        nums[i] <= 10^9, 最长 30 位
         */
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < 30; i++) {
            int c = 0;
            for (int j = 0; j < n; j++) {
                // nums[j] 右移判断第 i 位 是否为 1
                c += nums[j] >> i & 1;
            }
            ans += c * (n - c);
        }

        return ans;
    }

}
