package com.oycm.datastructure.tree.top_down_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_4 {

    /**
     * 3820. <a href="https://leetcode.cn/problems/pythagorean-distance-nodes-in-a-tree/description/">树上的勾股距离节点</a> 1725
     * 一棵包含 n 个节点的无向树，节点编号从 0 到 n - 1
     *
     * @param n
     * @param edges 表示节点之间的无向边
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        /*
        三个 互不相同 的目标节点 x、y 和 z, 树中的任意节点 u 到 x, y, z 节点的距离为记为三个数, 三个数 按从小到大排序 a, b, c
        求满足 a^2 + b^2 = c^2 的节点数

        题解思路: 找出树中 点 x, y, z 到其他点的最短距离
         */
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            int v = e[0];
            int w = e[1];
            g[v].add(w);
            g[w].add(v);
        }
        // 计算 点 x, y, z 的距离
        int[] dx = calcDis(x, g);
        int[] dy = calcDis(y, g);
        int[] dz = calcDis(z, g);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] a = new int[]{dx[i], dy[i], dz[i]};
            Arrays.sort(a);
            if ((long) a[0] * a[0] + (long) a[1] * a[1] == (long) a[2] * a[2]) {
                ans++;
            }
        }
        return ans;
    }

    private int[] calcDis(int start, List<Integer>[] g) {
        int[] dis = new int[g.length];
        dfs(start, -1, g, dis);
        return dis;
    }

    private void dfs(int cur, int fa, List<Integer>[] g, int[] dis) {
        for (int next : g[cur]) {
            if (next != fa) {
                dis[next] = dis[cur] + 1;
                dfs(next, cur, g, dis);
            }
        }
    }

}
