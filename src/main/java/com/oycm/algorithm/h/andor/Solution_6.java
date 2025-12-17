package com.oycm.algorithm.h.andor;

public class Solution_6 {

    /**
     * 2680. <a href="https://leetcode.cn/problems/maximum-or/description/">最大或值</a> 1912
     * 对 nums[i] 任意元素 乘以 2, 记为一次操作
     * 求 k 次操作后 nums[i] 或和的最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public long maximumOr(int[] nums, int k) {
        /*
        对 max(nums[i]) << k 次才能时数组或和最大
        k [1, 15], nums[i] [1, 10^9] 30位
        找到最大值 << k 后的 或和就是答案
        但是不是找到 最大值，而是找到一个元素 << k 的最大值
        题解思路：可以预处理 i 左侧 或 右侧 的或值和
        可以用 suf[i] 表示 [i+1, n-1] 的或值和, suf[n-1] = 0;
        pre = nums[0, i-1] 的或值和
         */
        long ans = 0L;
        int n = nums.length;
        int[] suf = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] | nums[i + 1];
        }
        int pre = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, pre | suf[i] | ((long) nums[i] << k));
            pre |= nums[i];
        }

        return ans;

    }

    public long answer(int[] nums, int k) {
        /*
        可以先计算所有的 nums[i] 或值和 xor, (xor ^ x) | x << k 这样计算答案，但是其它值中如果和 x 的 1 有交集，这样会丢失一部分值
        (xor ^ x) | fixed 怎么样补回来 需要的 1 呢？只要 nums[i] 某个比特位出现了两次，则 这个比特位恒为 1
        fixed |= xor & x 这样来求出现两次的比特位
         */
        long ans = 0;
        int fixed = 0;
        int xor = 0;
        for (int num : nums) {
            fixed |= xor & num;
            xor |= num;
        }

        for (int num : nums) {
            ans = Math.max(ans, (xor ^ num) | fixed | ((long) num << k));
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(12));
        System.out.println(Integer.toBinaryString(9));
    }

}
