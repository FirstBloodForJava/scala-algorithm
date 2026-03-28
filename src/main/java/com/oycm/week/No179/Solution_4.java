package com.oycm.week.No179;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    public int countArrays(int[] digitSum) {
        /*
        数位 dp
         */
        int MOD = 1000000007, MAX = 5000;
        int n = digitSum.length;

        List<Integer>[] bucket = new List[51];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<>();
        }

        for (int i = 0; i <= MAX; i++) {
            bucket[getDigitSum(i)].add(i);
        }

        long[] dp = new long[MAX + 1];

        // 初始化
        for (int v : bucket[digitSum[0]]) {
            dp[v] = 1;
        }

        for (int i = 1; i < n; i++) {

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
