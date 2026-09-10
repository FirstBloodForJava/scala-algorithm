package com.oycm.dp.e.enable_divide;

import java.util.Arrays;

public class Solution_3 {

    public int splitArray(int[] nums, int k) {
        /*
        dfs(i, k) = max(dfs(j, k-1), sum) i-1 > j >= k-1 所有最大取最小
        递归边界 dfs(i, 0) i = 0, 0
         */
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        int[][] f = new int[n + 1][k + 1];
        for (int[] row : f) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < Math.min(i, k); j++) {
                for (int m = 0; m < i; m++) {
                    /*
                    [0, i) 分成 j 个子数组, 枚举 [0, i) 所有的分割位置
                     */
                    f[i][j] = Math.min(f[i][j], Math.max(sum[i] - sum[m], f[m][j - 1]));
                }
            }
        }

        return f[n][k];
    }


}
