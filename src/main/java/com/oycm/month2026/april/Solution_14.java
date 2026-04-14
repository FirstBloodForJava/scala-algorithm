package com.oycm.month2026.april;

import java.util.Arrays;
import java.util.List;

public class Solution_14 {

    /**
     * 2463. <a href="https://leetcode.cn/problems/minimum-total-distance-traveled/description/">最小移动总距离</a> 2454
     * <p>
     * 每个机器人所在的位置 互不相同 。每个工厂所在的位置也 互不相同 。注意一个机器人可能一开始跟一个工厂在 相同的位置 。
     *
     * @param robot   robot[i] 是第 i 个机器人的位置
     * @param factory factory[j] = [position, limit], 第 j 个工厂的位置在 position, 且第 j 个工厂最多可以修理 limit 个机器人
     * @return 所有机器人开始都是坏的, 机器人沿着一个方向移动，工厂维修后会停止移动， 最小化所有机器人总的移动距离
     */
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        /*
        题解思路
        todo 待学习
         */
        int[] robots = robot.stream().mapToInt(i -> i).toArray();
        Arrays.sort(robots);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        int n = factory.length;
        int m = robots.length;
        long[][] memo = new long[n][m];
        for (long[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }

        return dfs(n - 1, m - 1, robots, factory, memo);
    }

    private long dfs(int i, int j, int[] robot, int[][] factory, long[][] memo) {
        if (j < 0) { // 所有机器人都修完了
            return 0;
        }
        if (i < 0) { // 还有机器人没修，但没有工厂了
            return Long.MAX_VALUE / 2; // 避免加法溢出
        }

        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }

        // 工厂 i 不修机器人
        long res = dfs(i - 1, j, robot, factory, memo);

        int position = factory[i][0];
        int limit = factory[i][1];
        long disSum = 0;
        // 枚举修 k 个机器人
        for (int k = 1; k <= Math.min(j + 1, limit); k++) {
            disSum += Math.abs(robot[j - k + 1] - position);
            res = Math.min(res, dfs(i - 1, j - k, robot, factory, memo) + disSum);
        }

        memo[i][j] = res; // 记忆化
        return res;
    }

}
