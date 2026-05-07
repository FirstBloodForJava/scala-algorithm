package com.oycm.dp.a.climb_stairs;

public class Solution_5 {

    /**
     * 2466. <a href="https://leetcode.cn/problems/count-ways-to-build-good-strings/description/">统计构造好字符串的方案数</a> 1694
     *
     * @param low
     * @param high
     * @param zero
     * @param one
     * @return
     */
    public int countGoodStrings(int low, int high, int zero, int one) {
        /*
        我们从空字符串开始构造一个字符串，每一步执行下面操作中的一种：
            将 '0' 在字符串末尾添加 zero  次。
            将 '1' 在字符串末尾添加 one 次。
        以上操作可以执行任意次。
        如果通过以上过程得到一个 长度 在 low 和 high 之间（包含上下边界）的字符串，那么这个字符串我们称为 好 字符串。
        请你返回满足以上要求的 不同 好字符串数目。由于答案可能很大，请将结果对 1e9 + 7 取余 后返回。
         */
        /*
        和 377 类似，可以转换成爬楼梯，两种步长爬楼梯，爬到 [low, high] 台阶的方案数
        题解思路：f(i) 表示爬楼梯 zero, one 步的方案数
        // todo 还可 gcd 优化
         */
        int[] dp = new int[high + 1];

        for (int i = high; i >= 0; i--) {
            if (i >= low && i <= high) dp[i] = 1;
            if (i + zero <= high) dp[i] += dp[i + zero];
            if (i + one <= high) dp[i] = (dp[i] + dp[i + one]) % mod;
        }
        return dp[0];
    }

    public int dfs(int i, int zero, int one, int[] memo) {
        /*
        int ans = 0;
        int[] memo = new int[high + 1];
        Arrays.fill(memo, -1);
        for (int i = low; i <= high; i++) {
            ans = (ans + dfs(i, zero, one, memo)) % mod;
        }

        return ans;
         */
        if (i < 0) return 0;
        if (i == 0) return 1;
        if (memo[i] != -1) return memo[i];
        return memo[i] = (dfs(i - zero, zero, one, memo) + dfs(i - one, zero, one, memo)) % mod;
    }

    public static final int mod = 1000000007;

    /**
     * dfs(0) 入口
     *
     * @param i
     * @param low
     * @param high
     * @param nums
     * @param memo
     * @return
     */
    public int dfs(int i, int low, int high, int[] nums, int[] memo) {
        /*
        爬到 [low, high] 后续可能还可继续爬，后续的方案该如何记录
         */
        if (i > high) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        long res = 0;
        for (int num : nums) {
            res = dfs(i + num, low, high, nums, memo) + res;
            if (i + num >= low && i + num <= high) {
                res++;
            }
        }

        return memo[i] = (int) (res % mod);
    }

}
