package com.oycm.dp.a.rob;

public class SolutionExtend_1 {

    /**
     * 2140. <a href="https://leetcode.cn/problems/solving-questions-with-brainpower/description/">解决智力问题</a> 1709
     *
     * @param questions
     * @return
     */
    public long mostPoints(int[][] questions) {
        /*
        下标从 0 开始的二维整数数组 questions ，其中 questions[i] = [pointsi, brainpoweri]
        这个数组表示一场考试里的一系列题目，你需要 按顺序（也就是从问题 0 开始依次解决），针对每个问题选择 解决 或者 跳过 操作。
        解决问题 i 将让你 获得  pointsi 的分数，但是你将 无法 解决接下来的 brainpoweri 个问题（即只能跳过接下来的 brainpoweri 个问题）。
        如果你跳过问题 i ，你可以对下一个问题决定使用哪种操作。
         */
        //long ans = 0;
        int n = questions.length;
        long[] dp = new long[n + 1];
        dp[n - 1] = questions[n - 1][0];
        for (int i = n - 1; i >= 0; i--) {
            int j = Math.min(n, i + questions[i][1]);
            dp[i] = Math.max(questions[i][0] + dp[j], dp[i + 1]);
        }

        return dp[0];
    }

}
