package com.oycm.algorithm.h.basic;


public class Solution_10 {

    /**
     * 2917. <a href="https://leetcode.cn/problems/find-the-k-or-of-an-array/description/">找出数组中的 K-or 值</a> 1389
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKOr(int[] nums, int k) {
        /*
        K-or 操作: 所有 nums[i] 某个比特位的存在至少 k 个 1，那么该 比特位的值为 1
        31 长的数组记录 nums[i] 对应比特位的数量

        cnt[i] >= k, 进行 或拼接
         */
        int[] cnt = new int[31];
        for (int num : nums) {
            int i = 0;
            while (num > 0) {
                if ((num & 1) == 1) {
                    cnt[i]++;
                }
                i++;
                num = num >> 1;
            }
        }
        int ans = 0;

        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] >= k) {
                ans |= 1 << i;
            }
        }

        return ans;
    }

    public int optimize(int[] nums, int k) {
        /*
        合并循环写法
         */
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += num >> i & 1;
            }
            if (cnt >= k) {
                ans |= 1 << i;
            }
        }

        return ans;
    }
}
