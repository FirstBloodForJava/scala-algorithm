package com.oycm.algorithm.h.mind;


import java.util.List;

public class Solution_4 {

    /**
     * 3315. <a href="https://leetcode.cn/problems/construct-the-minimum-bitwise-array-ii/description/">构造最小位运算数组 II</a> 1715
     * <p>
     * ans[i] | (ans[i] + 1) == nums[i], 求 符合条件的最小 ans[i], 没有符合条件的 ans[i], 则为 -1
     *
     * @param nums 长为 n 的质数数组
     * @return
     */
    public int[] minBitwiseArray(List<Integer> nums) {
        /*
        10 = 10 | 11, 01 | 10, 无论 ans[i] 是奇数还是偶数 其结果必然是 奇数, 肯定没有符合条件的 ans
        题解思路:
        nums[i] = 101111, x ans[i] 只能是下面的数
            101110
            101101
            101011
            100111
        最小值为 100111, 答案就是 把 nums[i] 最右边 0 右边的第一个1置为 0

        左边最低位连续 1 的第一个1取反就是答案

        取反 求 lowbit，再右移一位就是 右边的第一个1，和 nums[i] 异或就是答案
         */
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < nums.size(); i++) {
            int x = nums.get(i);
            if (x % 2 != 0) {
                int rev = ~x;
                int lowbit = (rev & -rev) >> 1;
                ans[i] = x ^ lowbit;
            } else {
                ans[i] = -1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(11));
    }

}
