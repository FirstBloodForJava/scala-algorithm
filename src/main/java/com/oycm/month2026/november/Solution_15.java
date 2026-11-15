package com.oycm.month2026.november;

public class Solution_15 {

    /**
     * 1524. <a href="https://leetcode.cn/problems/number-of-sub-arrays-with-odd-sum/description/">和为奇数的子数组数目</a> 1611
     *
     * @param arr
     * @return
     */
    public int numOfSubarrays(int[] arr) {
        /*
        给你一个整数数组 arr 。请你返回和为 奇数 的子数组数目。
        由于答案可能会很大，请你将结果对 10^9 + 7 取余后返回。
         */
        int mod = 1000000007;
        int odd = 0;
        int s = 0;
        for (int x : arr) {
            s += x;
            odd += s & 1;
        }
        // n-odd 个偶数，不选偶数算一种
        return (int) ((long)odd * (arr.length + 1 - odd) % mod);
    }
}
