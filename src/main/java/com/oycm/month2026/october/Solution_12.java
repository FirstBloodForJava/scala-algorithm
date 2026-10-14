package com.oycm.month2026.october;

import java.util.List;

public class Solution_12 {

    /**
     * 2897. <a href="https://leetcode.cn/problems/apply-operations-on-array-to-maximize-sum-of-squares/description/">对数组执行操作使平方和最大</a> 2301
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSum(List<Integer> nums, int k) {
        /*
        给你一个下标从 0 开始的整数数组 nums 和一个 正 整数 k 。
        你可以对数组执行以下操作 任意次 ：
            选择两个互不相同的下标 i 和 j ，同时 将 nums[i] 更新为 (nums[i] AND nums[j]) 且将 nums[j] 更新为 (nums[i] OR nums[j]) ，OR 表示按位 或 运算，AND 表示按位 与 运算。
            你需要从最终的数组里选择 k 个元素，并计算它们的 平方 之和。
        请你返回你可以得到的 最大 平方和。
        由于答案可能会很大，将答案对 109 + 7 取余 后返回。
         */
        /*
        任意次操作：这个数最大就是 2^31 - 1，nums[i] < 10^9 < 2^30，最大这个数 2^30 - 1
        题解思路：
        提示一：
            对于同一个比特位，and 和 or 不会改变两个数比特网位都为 0 和 都为 1 的情况：所有等价于：
            把一个数的的 0 比特位和另一个数相同位置的比特位 1 进行交换。
        提示二：
            假设交换前两个数是 x 和 y，且 x > y。把小的数的 1 给大的数，假设交换后 x 增加了 d，那么 y 也减少了 d；
            交换前：x^2 + y^2
            交换后：(x + d)^2 + (y - d)^2 = x^2 + y^2 + 2xd - 2yd + 2d^2 = x^2 + y^2 + 2d(x - y) + 2d^2 > x^2 + y^2
            说明通过交换，让一个数越大越好，相当于把所有的 1 都聚在一个数中。
         */
        int mod = 1000000007;
        // 记录所有数的比特位情况
        int[] cnt = new int[30];
        for (int x : nums) {
            for (int i = 0; i < 30; i++) {
                cnt[i] += (x >> i) & 1;
            }
        }
        long ans = 0;
        while (k-- > 0) {
            int x = 0;
            for (int i = 0; i < 30; i++) {
                if (cnt[i] > 0) {
                    cnt[i]--;
                    x |= 1 << i;
                }
            }
            ans = (ans + (long) x * x) % mod;
        }

        return (int) ans;
    }

}
