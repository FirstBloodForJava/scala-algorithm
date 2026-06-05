package com.oycm.month2026.june;

public class Solution_5 {

    /**
     * 3753. <a href="https://leetcode.cn/problems/total-waviness-of-numbers-in-range-ii/description/">范围内总波动值 II</a> 2297
     *
     * @param num1
     * @param num2
     * @return
     */
    public long totalWaviness(long num1, long num2) {
        /*
        给你两个整数 num1 和 num2，表示一个 闭 区间 [num1, num2]。
        一个数字的 波动值 定义为该数字中 峰 和 谷 的总数：
            如果一个数位 严格大于 其两个相邻数位，则该数位为 峰。
            如果一个数位 严格小于 其两个相邻数位，则该数位为 谷。
            数字的第一个和最后一个数位 不能 是峰或谷。
            任何少于 3 位的数字，其波动值均为 0。
        返回范围 [num1, num2] 内所有数字的波动值之和。
         */
        /*
        1 <= num1 <= num2 <= 1e15
         */
        char[] lowS = Long.toString(num1).toCharArray();
        char[] highS = Long.toString(num2).toCharArray();
        int n = highS.length;
        long[][][][] memo = new long[n][n - 1][3][10]; // 一个数至多包含 n-2 个峰或谷
        return dfs(0, 0, 0, 0, true, true, lowS, highS, memo);
    }

    private long dfs(int i, int waviness, int lastCmp, int lastDigit, boolean limitLow, boolean limitHigh, char[] lowS, char[] highS, long[][][][] memo) {
        if (i == highS.length) {
            return waviness;
        }
        if (!limitLow && !limitHigh && memo[i][waviness][lastCmp + 1][lastDigit] > 0) {
            return memo[i][waviness][lastCmp + 1][lastDigit] - 1;
        }

        int diffLh = highS.length - lowS.length;
        int lo = limitLow && i >= diffLh ? lowS[i - diffLh] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;

        long res = 0;
        boolean isNum = !limitLow || i > diffLh; // 前面是否填过数字
        for (int d = lo; d <= hi; d++) {
            // 当前填的数不是最高位，cmp 才有意义
            int cmp = isNum ? Integer.compare(d, lastDigit) : 0;
            int w = waviness + (cmp * lastCmp < 0 ? 1 : 0);
            res += dfs(i + 1, w, cmp, d, limitLow && d == lo, limitHigh && d == hi, lowS, highS, memo);
        }

        if (!limitLow && !limitHigh) {
            memo[i][waviness][lastCmp + 1][lastDigit] = res + 1;
        }
        return res;
    }

}
