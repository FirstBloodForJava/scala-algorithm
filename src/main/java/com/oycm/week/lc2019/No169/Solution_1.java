package com.oycm.week.lc2019.No169;

public class Solution_1 {

    /**
     * 1304. <a href="https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero/description/">和为零的 N 个不同整数</a> 1167
     *
     * @param n
     * @return
     */
    public int[] sumZero(int n) {
        /*
        给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
         */
        /*
        -1, 0, 1
        -1, 1
        是两种最简单的构造方式
        实现步骤：
            1. 初始化 ans[n/2] = 0；
            2. 初始化 l = 0, r = n-1, val = 1，当 l < r 时，ans[l++] = val, ans[r--] = -val，val++
         */
        /*
        等差数列构造 ans[0] = -s
        s = 1 + 2 + ... + n-1 = n*(n-1)/2
         */
        int[] ans = new int[n];
        int l = 0, r = n - 1;
        ans[n / 2] = 0;
        int val = 1;
        while (l < r) {
            ans[l++] = val;
            ans[r--] = -val;
            val++;
        }

        return ans;
    }

}
