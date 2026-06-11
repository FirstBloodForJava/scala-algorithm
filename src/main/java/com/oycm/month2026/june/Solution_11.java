package com.oycm.month2026.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_11 {

    /**
     * 3558. <a href="https://leetcode.cn/problems/number-of-ways-to-assign-edge-weights-i/description">给边赋权值的方案数 I</a> 1845
     *
     * @param edges
     * @return
     */
    public int assignEdgeWeights(int[][] edges) {
        /*
        给你一棵 n 个节点的无向树，节点从 1 到 n 编号，树以节点 1 为根。
        树由一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间有一条边。
        一开始，所有边的权重为 0。你可以将每条边的权重设为 1 或 2。
        两个节点 u 和 v 之间路径的 代价 是连接它们路径上所有边的权重之和。
        选择任意一个 深度最大 的节点 x。返回从节点 1 到 x 的路径中，边权重之和为 奇数 的赋值方式数量。
        由于答案可能很大，返回它对 109 + 7 取模的结果。
        注意： 忽略从节点 1 到节点 x 的路径外的所有边。
         */
        /*
        先求出 1 -> 节点 x 的最大深度边个数 k
        k-1 个边选 1或2 的方案数是 1 << (k-1)，
            如果方案选的和为 奇数，则另外一条边选 偶数；
            如果方案选的和为 偶数，则另外一条边选 奇数；
        所以方案数是 1 << (k-1)
         */
        int n = edges.length + 1;
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        int k = maxHeight(1, -1, g);


        return (int) pow(2, k - 1);
    }

    public int maxHeight(int cur, int fa, List<Integer>[] g) {

        int height = -1;
        for (int next : g[cur]) {
            if (next != fa) {
                height = Math.max(height, maxHeight(next, cur, g));
            }
        }

        return height + 1;
    }

    private static final int MOD = 1000000007;

    // 快速幂
    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

}
