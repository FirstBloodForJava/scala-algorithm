package com.oycm.algorithm.h.andor;


public class Solution_4 {

    /**
     * 2871. <a href="https://leetcode.cn/problems/split-array-into-maximum-number-of-subarrays/description/">将数组分割成最多数目的子数组</a> 1750
     * <p>
     * 子数组 [l, r] 分数： nums[l] & nums[l+1] ... & nums[r]
     *
     * @param nums 非负整数 数组
     * @return 求子数组分数之和尽可能小，可以返回最多的子数组
     */
    public int maxSubarrays(int[] nums) {
        /*
        题解：利用为 与运算性质，与的元素越多，会越来越小
        要注意子数组的 & 和不会小于整个数组的 & 和
        当 nums 与和记为 a 时，如果 a > 0, 则 分割后 的和 至少为 2a, 不满足题意
        所以当 a > 0 时，答案只能是 1
        当 a == 0, 只要与和为 0, 就拆分出一个子数组, 拆分的数量就是答案
         */
        int cnt = 0;
        // -1 表示 32位 二进制位都是 1
        int a = -1;
        for (int num : nums) {
            a &= num;
            if (a == 0) {
                cnt++;
                a = -1;
            }
        }

        return Math.max(cnt, 1);
    }

}
