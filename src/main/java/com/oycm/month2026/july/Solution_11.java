package com.oycm.month2026.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_11 {

    /**
     * 2685. 统计完全连通分量的数量
     * <br>
     * 2685. 统计完全连通分量的数量
     * <a href="https://leetcode.cn/problems/count-the-number-of-complete-components/description/"></a>
     * @param n
     * @param edges
     * @return
     */
    public int countCompleteComponents(int n, int[][] edges) {
        /*
        给你一个整数 n 。现有一个包含 n 个顶点的 无向 图，顶点按从 0 到 n - 1 编号。
        给你一个二维整数数组 edges 其中 edges[i] = [ai, bi] 表示顶点 ai 和 bi 之间存在一条 无向 边。
        返回图中 完全连通分量 的数量。
        如果在子图中任意两个顶点之间都存在路径，并且子图中没有任何一个顶点与子图外部的顶点共享边，则称其为 连通分量 。
        如果连通分量中每对节点之间都存在一条边，则称其为 完全连通分量 。
         */
        /*
        题解思路:
        DFS 每个连通块，统计当前连通块的点数 v 和边数 e。
        每访问一个点 x，就把 点数 v 加一，把 边数 e 加上点 x 的邻居个数（注意同一条边会被计入两次）。
        在完全图中，任意两点之间都有边。v 个点的完全图的边数，等于从 v 个点中选 2 个点的方案数。所以完全图有 v(v−1)/2  条边。
        由于统计的时候，一条边统计了两次，所以实际代码中的判断条件是 e 是否等于 v(v−1)。
         */
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                v = 0;
                e = 0;
                dfs(i, g, vis);
                if (e == v * (v - 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private int v;
    private int e;

    private void dfs(int x, List<Integer>[] g, boolean[] vis) {
        v++;
        e += g[x].size();
        vis[x] = true;
        for (int y : g[x]) {
            if (!vis[y]) {
                dfs(y, g, vis);
            }
        }
    }

}
