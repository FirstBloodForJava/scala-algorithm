package com.oycm.dp.j.count_num;

import java.util.Arrays;

public class Solution_17 {

    /**
     * 2999. <a href="https://leetcode.cn/problems/count-the-number-of-powerful-integers/description/">统计强大整数的数目</a> 2351
     *
     * @param start
     * @param finish
     * @param limit
     * @param s
     * @return
     */
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        /*
        给你三个整数 start ，finish 和 limit 。同时给你一个下标从 0 开始的字符串 s ，表示一个 正 整数。
        如果一个 正 整数 x 末尾部分是 s （换句话说，s 是 x 的 后缀），且 x 中的每个数位至多是 limit ，那么我们称 x 是 强大的 。
        请你返回区间 [start..finish] 内强大整数的 总数目。
         */
        /*
        1 <= start <= finish <= 1e15
        1 <= limit <= 9
        s 数位中每个数字都小于等于 limit。
        s 不包含任何前导 0。
         */
        char[] high = String.valueOf(finish).toCharArray();
        String lowStr = String.valueOf(start);
        lowStr = "0".repeat(high.length - lowStr.length()) + lowStr;
        long[] memo = new long[high.length];
        Arrays.fill(memo, -1);
        return dfs(0, true, true, lowStr.toCharArray(), high, limit, s.toCharArray(), memo);
    }

    public long dfs(int i, boolean loLimit, boolean hiLimit, char[] low, char[] high, int limit, char[] cs, long[] memo) {
        if (i == high.length) return 1;
        if (!hiLimit && !loLimit && memo[i] != -1) {
            return memo[i];
        }
        int lo = loLimit ? low[i] - '0' : 0;
        int hi = hiLimit ? high[i] - '0' : 9;
        long res = 0;
        if (i < high.length - cs.length) {
            for (int d = lo; d <= Math.min(hi, limit) ; d++) {
                res += dfs(i + 1, loLimit && d == lo, hiLimit && d == hi, low, high, limit, cs, memo);
            }
        } else {
            int x = cs[i - (high.length - cs.length)] - '0';
            if (lo <= x && x <= hi) {
                res = dfs(i+1, loLimit && x == lo, hiLimit && x == hi, low, high, limit, cs, memo);
            }
        }
        if (!loLimit && !hiLimit) memo[i] = res;
        return res;
    }


}
