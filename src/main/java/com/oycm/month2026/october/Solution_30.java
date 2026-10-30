package com.oycm.month2026.october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_30 {

    /**
     * 3970. <a href="https://leetcode.cn/problems/shortest-path-with-at-most-k-consecutive-identical-characters/description/">最多 K 个连续相同字符的最短路径</a> 1840
     *
     * @param n
     * @param edges
     * @param labels
     * @param k
     * @return
     */
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        /*
        给你一个整数 n，表示一个 有向加权 图中的节点数量，节点编号从 0 到 n - 1。
        该图由二维数组 edges 表示，其中 edges[i] = [ui, vi, wi] 表示一条从节点 ui 指向节点 vi、权重为 wi 的有向边。
        另给定一个长度为 n 的字符串 labels，其中 labels[i] 是分配给节点 i 的字符，以及一个整数 k。
        返回一条从节点 0 到节点 n - 1 的路径的 最小总边权 ，并要求该路径上所有节点标签按顺序 拼接 后，最多包含 k 个 连续相同 字符。如果不存在有效路径，返回 -1。
         */
        /*
        最短路 + 条件限制
         */
        char[] cs = labels.toCharArray();
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            // a -> b(c), c 表示权重
            g[e[0]].add(new int[]{e[1], e[2]});
        }
        int[][] dis = new int[n][k + 1];
        for (int[] row : dis) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // 小顶堆，[0] 表示路径权重， [1] 表示当前位置，[2] 最后最大连续相同字符数量
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0];
            int x = top[1];
            int cnt = top[2];
            // 已到达 n-1
            if (x == n - 1) {
                return d;
            }
            if (d > dis[x][cnt]) {
                continue;
            }
            for (int[] e : g[x]) {
                int y = e[0];
                int newCnt = cs[x] == cs[y] ? cnt + 1 : 1;
                int newDis = d + e[1];
                // 连续字符要求
                if (newCnt <= k && newDis < dis[y][newCnt]) {
                    dis[y][newCnt] = newDis;
                    pq.add(new int[]{newDis, y, newCnt});
                }
            }
        }

        return -1;
    }

}
