package com.oycm.dualweek.lc2026.No185;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_3 {

    /**
     * 3965. <a href="https://leetcode.cn/problems/finish-time-of-tasks-i/description/">任务完成时间 I</a> 1699
     *
     * @param n
     * @param edges
     * @param baseTime
     * @return
     */
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        /*
        给你一个整数 n，表示项目中的任务数量，编号从 0 到 n - 1。
        这些任务以任务 0 为根的 树 的形式连接。这由一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示任务 ui 是任务 vi 的父节点。
        同时给你一个长度为 n 的数组 baseTime，其中 baseTime[i] 表示完成任务 i 所需的时间。
        每个任务的 完成时间 计算如下：
            叶子任务：完成时间为 baseTime[i]。
            非叶子任务：
                令 earliest 为其子节点中的 最小 完成时间，latest 为其子节点中的 最大 完成时间。
                令 ownDuration 为 (latest - earliest) + baseTime[i]。
            任务 i 的完成时间为 latest + ownDuration。
        返回根任务 0 的完成时间。
         */
        /*
        先建图，自底向上返回
         */
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
        }
        return dfs(0, g, baseTime);

    }

    public long dfs(int x, List<Integer>[] g, int[] baseTime) {
        if (g[x].isEmpty()) {
            return baseTime[x];
        }
        long earliest = Long.MAX_VALUE;
        long latest = 0;
        for (int next : g[x]) {
            long son = dfs(next, g, baseTime);
            earliest = Math.min(earliest, son);
            latest = Math.max(latest, son);
        }
        return latest * 2 - earliest + baseTime[x];
    }

}
