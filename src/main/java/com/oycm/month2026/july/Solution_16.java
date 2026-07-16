package com.oycm.month2026.july;

import java.util.Arrays;

public class Solution_16 {

    /**
     * 3867. 数对的最大公约数之和
     * <br>
     * 3867. <a href="https://leetcode.cn/problems/sum-of-gcd-of-formed-pairs/description/">数对的最大公约数之和</a> 1407
     *
     * @param nums
     * @return
     */
    public long gcdSum(int[] nums) {
        /*
        给你一个长度为 n 的整数数组 nums。
        构造一个数组 prefixGcd，其中对于每个下标 i：
            令 mxi = max(nums[0], nums[1], ..., nums[i])。
            prefixGcd[i] = gcd(nums[i], mxi)。
        在构造 prefixGcd 之后：
            将 prefixGcd 按 非递减 顺序排序。
            通过取 最小的未配对 元素和 最大的未配对 元素来形成数对。
            重复此过程，直到无法再形成更多数对。
            对于每个形成的数对，计算 两个元素的最大公约数 gcd。
            如果 n 是奇数，prefixGcd 数组中的 中间 元素保持 未配对 状态，并应被忽略。
        返回一个整数，表示所有形成数对的 最大公约数之和。
        术语 gcd(a, b) 表示 a 和 b 的 最大公约数。
         */
        int mx = 0;
        int n = nums.length;
        int[] prefixGcd = new int[n];
        for (int i = 0; i < nums.length; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(mx, nums[i]);
        }
        Arrays.sort(prefixGcd);
        long sum = 0;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            sum += gcd(prefixGcd[l], prefixGcd[r]);
            l++;
            r--;
        }

        return sum;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
