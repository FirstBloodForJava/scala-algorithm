package com.oycm.dualweek.lc2026.No185;

public class Solution_2 {

    /**
     * 3964. <a href="https://leetcode.cn/problems/minimum-lights-to-illuminate-a-road/description/">照亮道路的最少灯泡数</a> 1572
     *
     * @param lights
     * @return
     */
    public int minLights(int[] lights) {
        /*
        给你一个长度为 n 的整数数组 lights，表示一条路上从 0 到 n - 1 有 n 个位置。
        对于每个位置 i：
            如果 lights[i] = v，其中 v > 0，则在位置 i 有一个正常工作的灯泡，它 照亮 从 max(0, i - v) 到 min(n - 1, i + v)（包含边界）的每个位置。
            如果 lights[i] = 0，则在位置 i 没有正常工作的灯泡。
        如果一个位置被 至少 一个正常工作的灯泡照亮，则该位置是 可见的 。
        你可以在 任意 位置安装 额外的 灯泡。每个安装在位置 j 的额外灯泡将照亮从 max(0, j - 1) 到 min(n - 1, j + 1)（包含边界）的位置。
        返回使路上 每个 位置都可见所需安装的最少额外灯泡数量。
         */
        /*
        可以用差分数组，确定哪些区间没有被照亮，一个灯泡能照亮 3 个位置，向上取整。
        一个安装一个灯泡，可以照亮 3 个位置。找到连续不照亮的地方，计算需要最少安装的灯泡数量，/ 3 下取整。
         */
        int n = lights.length;
        int[] d = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int v = lights[i];
            if (v > 0) {
                d[Math.max(0, i - v)] += 1;
                d[Math.min(n, i + v + 1)] -= 1;
            }

        }
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += d[i];
            if (sum == 0) {
                // 在 i+1 安装灯泡，照亮 [i, i+2]
                ans += 1;
                sum += 1;
                d[Math.min(n, i + 3)] -= 1;
            }
        }


        return ans;
    }

}
