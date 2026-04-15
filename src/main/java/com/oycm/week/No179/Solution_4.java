package com.oycm.week.No179;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_4 {

    /**
     * 3883. <a href="https://leetcode.cn/problems/count-non-decreasing-arrays-with-given-digit-sums/">统计满足数位和数组的非递减数组数目</a> 2172
     *
     * @param digitSum digitSum.length [1, 1000], digitSum[i] [0, 50]
     * @return
     */
    public int countArrays(int[] digitSum) {
        /*
        如果一个长度为 n 的数组 arr 满足以下条件，则认为它是 有效 的：
            0 <= arr[i] <= 5000
            它是 非递减 的
            arr[i] 的 数位和 等于 digitSum[i]
        返回一个整数，表示 不同的有效数组 的数量。由于答案可能很大，请将其对 1e9 + 7 取模后返回。
         */
        /*
        数位 dp
         */
        int MOD = 1000000007, MAX = 5000;
        int n = digitSum.length;

        // 记录相同数位的数字集合
        List<Integer>[] bucket = new List[51];
        Arrays.setAll(bucket, l -> new ArrayList<>());

        for (int i = 0; i <= MAX; i++) {
            bucket[getDigitSum(i)].add(i);
        }

        long[] dp = new long[MAX + 1];

        // 初始化 digitSum[0] 数位和对应的所有数字
        for (int v : bucket[digitSum[0]]) {
            dp[v] = 1;
        }

        for (int i = 1; i < n; i++) {

            // prefix[j] 表示 arr[i-1] 中，小于等于 j 的，且数位和等于 digitSum[i-1] 的数的数量
            long[] prefix = new long[MAX + 1];

            prefix[0] = dp[0];

            for (int v = 1; v <= MAX; v++) {
                prefix[v] = (prefix[v - 1] + dp[v]) % MOD;
            }

            long[] next = new long[MAX + 1];

            for (int v : bucket[digitSum[i]]) {
                next[v] = prefix[v];
            }

            dp = next;
        }

        long ans = 0;

        for (long v : dp) {
            ans = (ans + v) % MOD;
        }

        return (int) ans;
    }

    // x 的数位和
    private int getDigitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_4().countArrays(new int[]{2, 49, 23}));
    }
}
