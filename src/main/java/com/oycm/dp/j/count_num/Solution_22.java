package com.oycm.dp.j.count_num;

import java.util.HashMap;
import java.util.Map;

public class Solution_22 {

    /**
     * 3490. <a href="https://leetcode.cn/problems/count-beautiful-numbers/description/">统计美丽整数的数目</a> 2502
     *
     * @param l
     * @param r
     * @return
     */
    public int beautifulNumbers(int l, int r) {
        /*
        给你两个正整数 l 和 r 。如果正整数每一位上的数字的乘积可以被这些数字之和整除，则认为该整数是一个 美丽整数 。
        统计并返回 l 和 r 之间（包括 l 和 r ）的 美丽整数 的数目。
         */
        /*
        1 <= l <= r < 1e9
         */
        Map<Long, Integer> memo = new HashMap<>();
        return dfs(0, 1, 0, true, true, String.valueOf(l).toCharArray(), String.valueOf(r).toCharArray(), memo);
    }

    private int dfs(int i, int m, int s, boolean limitLow, boolean limitHigh, char[] low, char[] high, Map<Long, Integer> memo) {
        if (i == high.length) {
            return s > 0 && m % s == 0 ? 1 : 0;
        }
        /*
        m 乘积最大为 9^9 = 387420489 <= Integer.MAX_VALUE
        s 和最大为 9 * 9 = 81
        i 最大为 8
        三个 int 压缩成 long
         */
        long mask = (long) m << 32 | (long) i << 16 | s;
        if (!limitLow && !limitHigh && memo.containsKey(mask)) {
            return memo.get(mask);
        }

        int diffLh = high.length - low.length;
        int lo = limitLow && i >= diffLh ? low[i - diffLh] - '0' : 0;
        int hi = limitHigh ? high[i] - '0' : 9;

        int res = 0;
        int d = lo;
        /*
        初始时 limitLow 和 limitHigh 都为 true，如果前面都不填数字，也把 limitLow 标记为 true，
        这样就能通过 limitLow，以及 i 和 diffLh 判断前面是否填了数字
         */
        if (limitLow && i < diffLh) {
            // 前面没有填数字
            res = dfs(i + 1, 1, 0, true, false, low, high, memo);
            /*
            9234
             123
            填数字只能从 1 开始
             */
            d = 1;
        }
        for (; d <= hi; d++) {
            res += dfs(i + 1, m * d, s + d, limitLow && d == lo, limitHigh && d == hi, low, high, memo);
        }

        if (!limitLow && !limitHigh) {
            memo.put(mask, res);
        }
        return res;
    }
}
