package com.oycm.algorithm.h.gcd_log_trick;


public class Solution_1 {

    /**
     * 2447. <a href="https://leetcode.cn/problems/number-of-subarrays-with-gcd-equal-to-k/description/">最大公因数等于 K 的子数组数目</a> 1603
     *
     * @param nums
     * @param k
     * @return 求 nums 中 连续子数组 最大公约数 是 k 的 个数
     */
    public int subarrayGCD(int[] nums, int k) {
        /*
        子数组越来越长 最大公约数会越来越小, 具有单调性, 可以使用滑动窗口解决
        nums[r] == k, ans++;
        nums[l, r] > k r 继续扩大
        nums[l, r] == k, ans++, r 继续右移
        nums[l, r] < k 时, 移除 nums[l] 时, nums[l+1, r] 的最大公约数怎么求?
         */
        /*
        暴力做法 n^2
        外层循环 i = 0, 从左到右; 内存循环 j = i-1, 从右到左, nums[j] = gcd(nums[j], nums[i]);
         */
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x == k) {
                ans++;
            }
            for (int j = i - 1; j >= 0; j--) {
                nums[j] = gcd(nums[j], x);
                if (nums[j] == k) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public static int gcd(int a, int b) {
        // a > b > 0;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(gcd(1, 3));
    }
}
