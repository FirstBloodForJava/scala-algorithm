package com.oycm.dp.b.basic;

import java.util.Arrays;
import java.util.List;

public class Solution_4 {

    /**
     * 120. <a href="https://leetcode.cn/problems/triangle/description/">三角形最小路径和</a>
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        /*
        给定一个三角形 triangle ，找出自顶向下的最小路径和。
        每一步只能移动到下一行中相邻的结点上。
        相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
        如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
         */
        /*
        1 <= triangle.length <= 200
        triangle[0].length == 1
        triangle[i].length == triangle[i - 1].length + 1
        -1e4 <= triangle[i][j] <= 1e4
         */
        /*
        dfs(i, i) 表示到第 i 行，下标 i 的最小路径和
            当前位置只能是从第 i-1 行，下标 i-1 或 下标 i 过来
        子问题，dfs(i-1, i), dfs(i-1, i-1) 到第 i-1 行的最小路径和
        递归边界
         */
        int n = triangle.size();
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            ans = Math.min(ans, dfs(n - 1, i, triangle, memo));
        }
        return ans;
    }

    public int dfs(int i, int j, List<List<Integer>> triangle, int[][] memo) {
        if (i < 0 || j < 0 || j >= triangle.get(i).size()) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) return triangle.get(i).get(j);
        if (memo[i][j] != Integer.MIN_VALUE) return memo[i][j];

        return memo[i][j] = Math.min(dfs(i - 1, j, triangle, memo), dfs(i - 1, j - 1, triangle, memo)) + triangle.get(i).get(j);
    }

    public int minimumTotal_dp(List<List<Integer>> triangle) {
        /*
        dfs(i, j) = min(
            dfs(i-1, j),
            dfs(i-1, j-1)
            ) + triangle[i][j]
        f[i][j] = min(f[i-1][j], f[i-1][j-1]) + triangle[i][j]
        f[i-1][j] 表示当前行上方；
        f[i-1][j-1] 表示当前行左上方；
        使用一行，要倒着更新 f[j]
         */
        int n = triangle.size();
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[1] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                f[j + 1] = Math.min(f[j + 1], f[j]) + triangle.get(i).get(j);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n ; i++) {
            ans = Math.min(ans, f[i]);
        }

        return ans;
    }

}
