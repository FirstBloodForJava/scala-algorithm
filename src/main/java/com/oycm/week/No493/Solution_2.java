package com.oycm.week.No493;

public class Solution_2 {

    public long countCommas(long n) {
        /*
        10 ^ 15 最大只会到5个逗号
        区间统计
         */
        long ans = 0;
        long start = 1;

        for (int k = 0; k <= 5; k++) {
            long end = start * 1000 - 1;
            if (n < start) break;

            long right = Math.min(n, end);
            long count = right - start + 1;

            ans += count * k;

            start *= 1000;
        }

        return ans;
    }
}
