package com.oycm.month2026.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_12 {

    /**
     * 3559. <a href="https://leetcode.cn/problems/number-of-ways-to-assign-edge-weights-ii/description/">给边赋权值的方案数 II</a> 2146
     *
     * @param edges
     * @param queries
     * @return
     */
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        /*
        给你一棵有 n 个节点的无向树，节点从 1 到 n 编号，树以节点 1 为根。
        树由一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间有一条边。
        一开始，所有边的权重为 0。你可以将每条边的权重设为 1 或 2。
        两个节点 u 和 v 之间路径的 代价 是连接它们路径上所有边的权重之和。
        给定一个二维整数数组 queries。对于每个 queries[i] = [ui, vi]，计算从节点 ui 到 vi 的路径中，使得路径代价为 奇数 的权重分配方式数量。
        返回一个数组 answer，其中 answer[i] 表示第 i 个查询的合法赋值方式数量。
        由于答案可能很大，请对每个 answer[i] 取模 1e9 + 7。
        注意： 对于每个查询，仅考虑 ui 到 vi 路径上的边，忽略其他边。
         */
        /*
        2 <= n <= 1e5
        edges.length == n - 1
        edges[i] == [ui, vi]
        1 <= queries.length <= 1e5
        queries[i] == [ui, vi]
        1 <= ui, vi <= n
        edges 表示一棵合法的树。
         */
        /*
        关键是怎么快速找出 [ui, vi] 边长
         */
        int mod = 1000000007;
        int n = edges.length;
        int[] pow2 = new int[n + 1];
        pow2[0] = 1;
        for (int i = 1; i < pow2.length; i++) {
            pow2[i] = pow2[i - 1] * 2 % mod;
        }
        int m = queries.length;
        int[] ans = new int[m];
        TreeAncestor t = new TreeAncestor(edges);
        for (int i = 0; i < m; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            if (x != y) {
                ans[i] = pow2[t.getDis(x, y) - 1];
            }
        }

        return ans;
    }

}

class TreeAncestor {

    // pa[x][i] 表示 x 节点的第 2^i 个祖先
    int[][] pa;
    // depth[x] 表示 x 节点的高度
    int[] depth;

    public TreeAncestor(int[][] edges) {
        /*
        先根据 edges 建图，节点是 [0, n-1]
         */
        int n = edges.length + 2;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        int m = 32 - Integer.numberOfLeadingZeros(n);
        pa = new int[n][m];
        depth = new int[n];

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }

        dfs(1, -1, g);

        // pa[x][i] 计算
        for (int i = 0; i < m - 1; i++) {
            for (int x = 1; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = p == -1 ? -1 : pa[p][i];
            }
        }

    }

    public void dfs(int cur, int fa, List<Integer>[] g) {
        pa[cur][0] = fa;
        for (int next : g[cur]) {
            if (next != fa) {
                depth[next] = depth[cur] + 1;
                dfs(next, cur, g);
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        /*
        lowbit
        */
        for (; k > 0 && node >= 0; k &= k - 1) {
            // 注意，这里是 lowbit 长度 -1，不是 lowbit 的值
            node = pa[node][Integer.numberOfTrailingZeros(k)];
        }

        return node;
    }

    public int getLca(int x, int y) {
        /*
        假设 depth[x] <= depth[y] （否则交换 x 和 y），保证 x 的在 y 的上面。
        先把更靠下的 y 更新成 y 的第 getKthAncestor(y, depth[y] - depth[x]) 个祖先节点，这样 x 和 y 在同一深度，
        如果 x = y，那么 x 就是 lca；否则说明 lca 在更上面，那么就把 x 和 y 一起向上调。
        由于不知道 lca 的具体位置，只能不断尝试，先尝试大步跳，再尝试小步跳。初始 i = log n，循环知道 i < 0。每次循环：
            如果 x 的第 2^i 个祖先节点不存在，即 pa[x][i] = -1，说明跳多了，将 i 减 1，继续循环；
            如果 x 的第 2^i 个祖先节点存在，
                如果 pa[x][i] != pa[y][i]，说明 lca 在 pa[x][i] 的上面，那么更新 x = p[x][i], y = p[y][i]，将 i 继续减 1，继续循环；
                否则，pa[x][i] = pa[y][i]，说明 lca 在 pa[x][i] 的下面，那个将 i 继续减 1，继续循环；
         */
        if (depth[x] > depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        y = getKthAncestor(y, depth[y] - depth[x]);
        if (x == y) {
            return x;
        }
        for (int i = pa[x].length - 1; i >= 0; i--) {
            if (pa[x][i] != pa[y][i]) {
                x = pa[x][i];
                y = pa[y][i];
            }
        }
        return pa[x][0];
    }

    public int getDis(int x, int y) {
        return depth[x] + depth[y] - depth[getLca(x, y)] * 2;
    }

}
