package com.oycm.datastructure.tree.down_top_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_4 {

    /**
     * 3558. <a href="https://leetcode.cn/problems/number-of-ways-to-assign-edge-weights-i/description/">给边赋权值的方案数 I</a> 1845
     * 一棵 n 个节点的无向树, 节点从 1 到 n 编号
     *
     * @param edges 长度为 n - 1 的二维整数数组, edges[i] = [u, v] 表示在节点 u 和 v 之间有一条边
     * @return 选择任意一个 深度最大 的节点 x。返回从节点 1 到 x 的路径中，边权重之和为 奇数 的赋值方式数量。
     */
    public int assignEdgeWeights(int[][] edges) {
        /*
        一开始，所有边的权重为 0。你可以将每条边的权重设为 1 或 2
        两个节点 u 和 v 之间路径的 代价 是连接它们路径上所有边的权重之和
         */
        /*
        如果知道树的最大深度为 m, 问题就变成了 m 个 0, 把 0 改为 1 或 2, 和为奇数的方案数
        m 个元素可选奇数或偶数，求选奇数和数的方案数
        [m - 1] 选奇数或偶数的 方案数 2^(m-1)
            当选了 偶数个奇数，则另外一个选奇数（选）
            当选了 奇数个奇数，则另外一个选偶数（不选）
         */
        int n = edges.length + 1;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        g[1].add(-1);

        int height = maxDepth(1, 0, g);
        return (int) pow(2, height - 1);
    }

    static int MOD = 1000000007;

    public int maxDepth(int cur, int fa, List<Integer>[] g) {
        int depth = 0;
        // 叶子节点
        if (g[cur].size() == 1) {
            return depth;
        }
        for (Integer next : g[cur]) {
            if (next != fa) {
                depth = Math.max(depth, maxDepth(next, cur, g));
            }
        }
        return depth + 1;
    }

    public long pow(long x, long n) {
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
