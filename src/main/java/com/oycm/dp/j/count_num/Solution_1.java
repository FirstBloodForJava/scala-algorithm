package com.oycm.dp.j.count_num;

import java.util.Arrays;

public class Solution_1 {

    /**
     * 3747. <a href="https://leetcode.cn/problems/count-distinct-integers-after-removing-zeros/description/">统计移除零后不同整数的数目</a> 1848
     *
     * @param n
     * @return
     */
    public long countDistinct(long n) {
        /*
        给你一个 正 整数 n。
        对于从 1 到 n 的每个整数 x，我们记下通过移除 x 的十进制表示中的所有零而得到的整数。
        返回一个整数，表示记下的 不同 整数的数量。
         */
        /*
        1 <= n <= 1e15
         */
        /*
        1230 去掉 0 得到 123 这个数看到在前面出现过，且包含 0，本质是统计 [1, n] 不含 0 的个数；
        长为 1 不含 0 的数字有 9 个，长为 2 不含 0 的数字有 9*9 个
        长为 [1, m-1] 的数字共有 9 + 9^2 + ... + 9^(m-1)
        等比数列求和：(9^m - 9)/8
        从数字高位遍历到低位
            最高位
        */
        char[] cs = String.valueOf(n).toCharArray();
        int m = cs.length;
        long pow = (long) Math.pow(9, m);
        long ans = (pow - 9) / 8;

        for (int i = 0; i < m && cs[i] > '0'; i++) {
            int d = cs[i] - '1';
            pow /= 9;
            if (i == m - 1) {
                d++;
            }
            ans += d * pow;
        }

        return ans;
    }

    /**
     * @param low
     * @param high
     * @param target
     * @return 统计 [low, high] 区间包含 target 个 0 的数字个数
     */
    public long digitDP(long low, long high, int target) {
        char[] lowS = String.valueOf(low).toCharArray();
        char[] highS = String.valueOf(high).toCharArray();

        int n = highS.length;
        long[][] memo = new long[n][target + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(0, 0, true, true, lowS, highS, target, memo);
    }

    private long dfs(int i, int cnt0, boolean limitLow, boolean limitHigh, char[] lowS, char[] highS, int target, long[][] memo) {
        if (cnt0 > target) {
            return 0; // 不合法
        }
        if (i == highS.length) {
            return cnt0 == target ? 1 : 0;
        }

        if (!limitLow && !limitHigh && memo[i][cnt0] >= 0) {
            return memo[i][cnt0];
        }

        int diff = highS.length - lowS.length;
        int lo = limitLow && i >= diff ? lowS[i - diff] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;

        long res = 0;
        int d = lo;

        // 通过 limitLow 和 i 可以判断能否不填数字，无需 isNum 参数
        // 如果前导零不影响答案，去掉这个 if block
        if (limitLow && i < diff) {
            // 不填数字，上界不受约束
            res = dfs(i + 1, 0, true, false, lowS, highS, target, memo);
            d = 1; // 下面填数字，从 1 开始填
        }

        for (; d <= hi; d++) {
            res += dfs(i + 1,
                    cnt0 + (d == 0 ? 1 : 0),
                    limitLow && d == lo,
                    limitHigh && d == hi,
                    lowS, highS, target, memo);
        }

        if (!limitLow && !limitHigh) {
            memo[i][cnt0] = res;
        }
        return res;
    }

}
