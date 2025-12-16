package com.oycm.algorithm.h.xor;

public class Solution_7 {

    /**
     * 2997. <a href="https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k/description/">使数组异或和等于 K 的最少操作次数</a> 1525
     * <p>
     * 对数字中任意一个元素表示的二进制 翻转 一个数位 记为一次操作
     *
     * @param nums
     * @param k
     * @return
     */
    public int minOperations(int[] nums, int k) {
        /*
        本质就是 求 nums 的异或和 需要翻转几次才能变成 k
        不相等时，怎么计算 k 和 xor 不同位的数量
        100 -> 001
        把 k 取反, xor & ~k 的 1 的个数就是 需要翻转成 0 的个数
        把 xor 取反, ~xor & k 的 1 的个数就是 需要翻转成 1 的个数
         */
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        if (xor == k) {
            return 0;
        }

        return Integer.bitCount(xor & ~k) + Integer.bitCount(~xor & k);

    }

    public int answer(int[] nums, int k) {
        /*
        与 k 相同的不需要翻转，异或之后为 1 的就是需要翻转的数量
         */
        for (int num : nums) {
            k ^= num;
        }
        return Integer.bitCount(k);
    }

}
