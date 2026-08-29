package com.oycm.dp.e.constraint_divide;

public class Solution_1 {

    /**
     * 813. <a href="https://leetcode.cn/problems/largest-sum-of-averages/description/">最大平均值和的分组</a> 1937
     *
     * @param nums
     * @param k
     * @return
     */
    public double largestSumOfAverages(int[] nums, int k) {
        /*
        给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个非空子数组，且数组内部是连续的。
        分数 由每个子数组内的平均值的总和构成。
        注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
        返回我们所能得到的最大 分数 是多少。答案误差在 10^-6 内被视为是正确的。
         */
        /*
        定义 dfs(i, j) 表示把长为 i 的前缀分成 j 个连续子数组的最大平均分数。
        枚举 [0, i) 最后一个子数组 的左端点，dfs(i, j) = max(dfs(l, j-1) + (sum(i) - sum(l)) / (i-l)),
        l 范围， j-1 <= l < i
        递归边界：
            dfs(0, 0) = 0;
            dfs(i, 0) = min;
         */
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        double[][] memo = new double[n + 1][k + 1];

        return dfs(n, k, memo, sums);
    }

    public double dfs(int i, int j, double[][] memo, int[] sums) {
        if (i == 0 || j == 0) {
            if (i == 0 && j == 0) {
                return 0;
            }
            return Integer.MIN_VALUE;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        double ans = 0;
        for (int l = i - 1; l >= j - 1; l--) {
            ans = Math.max(ans, dfs(l, j - 1, memo, sums) + ((double) sums[i] - sums[l]) / (i - l));
        }

        return memo[i][j] = ans;
    }

    public double largestSumOfAverages_dp(int[] nums, int k) {
        /*
        f[j][i] 表示把长为 i 的前缀分成 j 个连续子数组的最大平均分数。
        枚举 最后一个子数组的左端点
        f[j][i] = max(f[j-1][l] + avg(sum[i] - sums[l]))
        j-1 <= l < i
        j = 1, f[1][i] = sums[i] / i;
        j > 1; f[j][i] = max(f[j-1][l] + avg(sum[i] - sums[l]))
         */
        int n = nums.length;
        double[] sums = new double[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        double[][] f = new double[k + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            f[1][i] = sums[i] / i;
        }
        for (int j = 2; j <= k; j++) {
            for (int i = j; i <= n; i++) {
                for (int l = j - 1; l < i; l++) {
                    f[j][i] = Math.max(f[j][i], f[j - 1][l] + (sums[i] - sums[l]) / (i - l));
                }
            }
        }

        return f[k][n];
    }

}
