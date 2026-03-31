package com.oycm.datastructure.tree.traverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_1 {

    /**
     * 2368. <a href="https://leetcode.cn/problems/reachable-nodes-with-restrictions/description/">受限条件下可到达节点的数目</a> 1477
     *
     * @param n          n 个节点组成的无向树, 节点编号从 0 到 n - 1 ，共有 n - 1 条边
     * @param edges      长度为 n - 1, edges[i] = {x,y} 表示 节点之间的一条边
     * @param restricted 表示 受限 节点
     * @return 不访问受限节点的前提下，返回你可以从节点 0 到达的 最多 节点数目
     */
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] isRestricted = new boolean[n];
        for (int i : restricted) {
            isRestricted[i] = true;
        }
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (!isRestricted[x] && !isRestricted[y]) {
                g[x].add(y);
                g[y].add(x);
            }
        }

        return dfs(0, -1, g);
    }

    public int dfs(int x, int fa, List<Integer>[] g) {
        int cnt = 1;
        for (Integer y : g[x]) {
            if (y != fa) {
                cnt += dfs(y, x, g);
            }
        }
        return cnt;
    }

}
