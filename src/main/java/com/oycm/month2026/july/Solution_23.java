package com.oycm.month2026.july;

public class Solution_23 {

    /**
     * 3513. <a href="https://leetcode.cn/problems/number-of-unique-xor-triplets-i/description/">不同 XOR 三元组的数目 I</a> 1663
     *
     * @param nums
     * @return
     */
    public int uniqueXorTriplets(int[] nums) {
        /*
        给你一个长度为 n 的整数数组 nums，其中 nums 是范围 [1, n] 内所有数的 排列 。
        XOR 三元组 定义为三个元素的异或值 nums[i] XOR nums[j] XOR nums[k]，其中 i <= j <= k。
        返回所有可能三元组 (i, j, k) 中 不同 的 XOR 值的数量。
        排列 是一个集合中所有元素的重新排列。
         */
        /*
        由于 i <= j <= k，所以三元组范围至少满是 [1, n]
        就看三元组的异或值是否能得到 0。
        只有 n 大于等于 3（两个 bit 位），就存在三元组异或为 0 的情况。
        题解思路：
        如果 n = 1，只能选 3 个 1，异或值为 1，答案为 1。
        如果 n = 2，三个数中必然有两个数相等，异或值为 1 或 2，答案为 2。
        如果 n >= 3，
            对于结果 x = 0 可以通过选 1，2，3 得到；
            对于结果 x [1, n] 可以通过选 1 ^ 1 ^ x 得到；
            对于结果 x [n+1, 2^l - 1]，l 为 n 的二进制长度，可以按 2^(l-1), x ^ 2^(l-1) ^ 1, 1 结果是 x。
            特殊情况当 x = 2^(l-1) + 1 时，第二个数结果为 0，不能选这个数，前后两个数可以选择 2，3，异或结果得到 a。
            数量为 2^l
         */
        int n = nums.length;
        return n < 3 ? n : 1 << (32 - Integer.numberOfLeadingZeros(n));
    }

}
