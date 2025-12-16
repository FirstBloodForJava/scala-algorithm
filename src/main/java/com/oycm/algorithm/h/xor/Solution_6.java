package com.oycm.algorithm.h.xor;

public class Solution_6 {

    /**
     * 1829. <a href="https://leetcode.cn/problems/maximum-xor-for-each-query/description/">每个查询的最大异或值</a> 1523
     *
     * 执行以下查询 n 次
     *  非负整数 k < (1 << maximumBit), 使得 nums[0] ^ nums[1] ... ^ nums[n-1] ^ k 的结果最大化
     *  从当前数组中删除最后一个元素
     * @param nums 升序 非负整数 数组
     * @param maximumBit
     * @return
     */
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        /*
        由于 nums[i] < (1 << maximumBit), 所以 xor ^ ? 最大为 k
        xor 为 0 的地方 ? 为 1, xor 为 1 的地方 为 0
        ans = ~xor & k
         */
        int n = nums.length;
        int[] ans = new int[n];
        int xor = 0;
        int k = (1 << maximumBit) - 1;
        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
            ans[n - 1 - i] = ~xor & k;
        }

        return ans;
    }
}
