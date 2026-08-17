package com.oycm.dp.c.multiple_knapsack;

public class Solution_1 {

    /**
     * 2585. <a href="https://leetcode.cn/problems/number-of-ways-to-earn-points/description/">获得分数的方法数</a>
     *
     * @param target [1, 1000]
     * @param types  n [1, 50]
     * @return
     */
    public int waysToReachTarget(int target, int[][] types) {
        /*
        考试中有 n 种类型的题目。
        给你一个整数 target 和一个下标从 0 开始的二维整数数组 types ，其中 types[i] = [count, marks] 表示第 i 种类型的题目有 count 道，每道题目对应 marks 分。
        返回你在考试中恰好得到 target 分的方法数。由于答案可能很大，结果需要对 109 +7 取余。
        注意，同类型题目无法区分。
        注意，同类型题目无法区分。
            比如说，如果有 3 道同类型题目，那么解答第 1 和第 2 道题目与解答第 1 和第 3 道题目或者第 2 和第 3 道题目是相同的。
         */
        /*
        dfs 怎么标记一种类型的题目选了几次？
        限制在循环，和 dfs 参数无关。
         */
        /*
        f[i][j] +=
            for(int k = 0; k <= count; k++) {
                f[i-1][j - k*count];
            }
            count = types[i][0]
            marks = types[i][1]
         */
        int mod = 1000000007;
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int[] p : types) {
            int count = p[0];
            int marks = p[1];
            for (int j = target; j >= marks; j--) {
                // j - k * marks >= 0 = k <= j / marks;
                for (int k = 1; k <= Math.min(j / marks, count); k++) {
                    f[j] = (f[j] + f[j - k * marks]) % mod;
                }
            }
        }

        return f[target];
    }

}
