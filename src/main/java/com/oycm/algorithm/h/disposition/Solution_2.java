package com.oycm.algorithm.h.disposition;

public class Solution_2 {

    /**
     * 1863. <a href="https://leetcode.cn/problems/sum-of-all-subset-xor-totals/description/">找出所有子集的异或总和再求和</a> 1372
     *
     * @param nums
     * @return 求所有子集的异或值, 再求和
     */
    public int subsetXORSum(int[] nums) {
        /*
        沉浸在 nums[i] 有 c 个 1 时, 会有多少个不同的子集组合结果是 1。
        题解思路：n 个数中，其中至少有一个数是 1，在构建子集的过程中，先拿出一个 1，剩余的 n-1 个数随便 选与不选，有 2^(n-1) 种选法
            如果 n-1 个数中选中了偶数个 1，把拿出来的 1 放入，则异或和 为 1
            如果 n-1 个数中选中了奇数个 1，异或和为 1
        所以有恰好 2^(n-1) 个子集
        只要 nums[i] 中第 i 个比特位 包含 1，则 sum += (1 << i) * 2^(n-1)
        怎么知道 数组中 第 i 个比特位 是否包含 1，可以用 or 连接所以，再 乘以 2^(n-1) 就是答案
         */
        int or = 0;
        for (int num : nums) {
            or |= num;
        }
        return or << (nums.length - 1);
    }

}
