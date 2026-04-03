package com.oycm.month2026.april;

import java.util.Arrays;
import java.util.Comparator;

public class Solution_3 {

    /**
     * 3661. <a href="https://leetcode.cn/problems/maximum-walls-destroyed-by-robots/description/">可以被机器人摧毁的最大墙壁数目</a> 2525
     * 一条无限长的直线上分布着一些机器人和墙壁
     *
     * @param robots   robots[i] 是第 i 个机器人的位置
     * @param distance distance[i] 是第 i 个机器人的子弹可以行进的 最大 距离
     * @param walls    walls[j] 是第 j 堵墙的位置
     * @return 机器人可以摧毁墙壁的 最大 数量
     */
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        /*
        每个机器人有 一颗 子弹，可以向左或向右发射，最远距离为 distance[i] 米。
        子弹会摧毁其射程内路径上的每一堵墙。机器人是固定的障碍物：如果子弹在到达墙壁前击中另一个机器人，它会 立即 在该机器人处停止，无法继续前进。

        注意：
        墙壁和机器人可能在同一位置；该位置的墙壁可以被该位置的机器人摧毁。
        机器人不会被子弹摧毁。
         */
        /*
        题解思路:
            考虑最右边的机器人，分类讨论：
                如果它向左射击，需要解决的子问题是：对于前 n-1 个机器人，在第 n 个机器人向左射击的前提下，能摧毁的最大城墙数
                如果它向右射击，需要解决的子问题是：对于前 n-1 个机器人，在第 n 个机器人向右射击的前提下，能摧毁的最大城墙数
            注意，当第 n 个机器人往左射击时，第 n-1 个机器人向右射击，需要限制其访问
         */
        int n = robots.length;
        int[][] a = new int[n + 2][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = robots[i];
            a[i][1] = distance[i];
        }
        // 增加一个 位置为 0 和 max 节点, 简化判断
        a[n][0] = Integer.MAX_VALUE;
        Arrays.sort(a, Comparator.comparingInt(p -> p[0]));
        Arrays.sort(walls);

        int[][] memo = new int[n + 1][2];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(n, 1, a, walls, memo);

    }

    private int dfs(int i, int j, int[][] a, int[] walls, int[][] memo) {
        if (i == 0) {
            return 0;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }

        int x = a[i][0], d = a[i][1];
        // 往左射，墙的坐标范围为 [leftX, x]
        int leftX = Math.max(x - d, a[i - 1][0] + 1); // +1 表示不能射到左边那个机器人

        int left = lowerBound(walls, leftX);
        int cur = lowerBound(walls, x + 1);
        int resLeft = dfs(i - 1, 0, a, walls, memo) + cur - left; // 下标在 [left, cur-1] 中的墙都能摧毁

        // 往右射，墙的坐标范围为 [x, rightX]
        int rightX = x + d;
        int x2 = a[i + 1][0];
        if (j == 0) { // 右边那个机器人往左射
            x2 -= a[i + 1][1];
        }
        rightX = Math.min(rightX, x2 - 1); // -1 表示不能射到右边那个机器人（或者它往左射到的墙）

        int right = lowerBound(walls, rightX + 1);
        cur = lowerBound(walls, x);
        int resRight = dfs(i - 1, 1, a, walls, memo) + right - cur; // 下标在 [cur, right-1] 中的墙都能摧毁

        return memo[i][j] = Math.max(resLeft, resRight); // 记忆化
    }

    private int lowerBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

}
