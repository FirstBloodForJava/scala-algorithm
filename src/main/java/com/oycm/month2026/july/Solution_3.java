package com.oycm.month2026.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_3 {

    /**
     * 3620. 恢复网络路径
     * <br>
     * 3620. <a href="https://leetcode.cn/problems/network-recovery-pathways/description/">恢复网络路径</a> 1998
     *
     * @param edges
     * @param online
     * @param k
     * @return
     */
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        /*
        给你一个包含 n 个节点（编号从 0 到 n-1）的有向无环图。图由长度为 m 的二维数组 edges 表示，
        其中 edges[i] = [ui, vi, costi] 表示从节点 ui 到节点 vi 的单向通信，恢复成本为 costi。
        一些节点可能处于离线状态。给定一个布尔数组 online，其中 online[i] = true 表示节点 i 在线。节点 0 和 n - 1 始终在线。
        从 0 到 n - 1 的路径如果满足以下条件，那么它是 有效 的：
            路径上的所有中间节点都在线。
            路径上所有边的总恢复成本不超过 k。
        对于每条有效路径，其 分数 定义为该路径上的最小边成本。
        返回所有有效路径中的 最大 路径分数（即最大 最小 边成本）。如果没有有效路径，则返回 -1。
         */
        int n = online.length;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        int maxWt = -1;
        for (int[] e : edges) {
            int x = e[0], y = e[1], wt = e[2];
            if (online[x] && online[y]) {
                g[x].add(new int[]{y, wt});
                if (x == 0) {
                    maxWt = Math.max(maxWt, wt);
                }
            }
        }

        long[] memo = new long[n];
        int left = -1, right = maxWt + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            Arrays.fill(memo, -1L); // -1 表示没有计算过
            if (dfs(0, mid, g, memo) <= k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private long dfs(int x, int lower, List<int[]>[] g, long[] memo) {
        if (x == g.length - 1) { // 到达终点
            return 0;
        }
        if (memo[x] != -1) { // 之前计算过
            return memo[x];
        }
        long res = Long.MAX_VALUE / 2; // 防止加法溢出
        for (int[] e : g[x]) {
            int y = e[0], wt = e[1];
            if (wt >= lower) {
                res = Math.min(res, dfs(y, lower, g, memo) + wt);
            }
        }
        return memo[x] = res; // 记忆化
    }

}
