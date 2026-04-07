package com.oycm.datastructure.tree.topological_sort;

import java.util.*;

public class Solution_1 {

    /**
     * 310. <a href="https://leetcode.cn/problems/minimum-height-trees/description/">最小高度树</a>
     * <p>
     * 一棵包含 n 个节点的树，标记为 0 到 n - 1
     *
     * @param n
     * @param edges edges1[i] = [a, b] 表示在一棵树中节点 a 和 b 之间有一条边
     * @return 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树的根节点标签
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        /*
        树的高度由 根节点到叶子节点 之间的最大距离构成，假设树中距离最长的两个节点为 (x,y) 距离为 maxLen(树的直径) = xy
        假设 x 到 y 的路径为 x → p1 → p2 → ... → p(k−1) →pk → y, 根据树中任意节点到其它节点的最小值节点为 树直径所在路径的中点
        可以删除最外层度为 1 的节点 x,y 后，x,y 相邻节点 p1,pk 变成了度为 1 的节点，直到只剩下根节点 数量 <= 2
        证明：反证法，假设 p1 节点的度不为 1，还有一个节点 q 和它相连，且 q 不在最长路径中，由于删除度为 1 的节点后，节点 p 还存在，
        所以，节点 q 还有一个节点 q' 和它连接，q'y 的距离 = q'p1 + p1y = 2 + p1y > xy(1 + p1y) 与 xy 为树的最长路径相矛盾
         */
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        // 节点的度个数
        int[] degree = new int[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        int remainNodes = n;
        while (remainNodes > 2) {
            int sz = queue.size();
            remainNodes -= sz;
            for (int i = 0; i < sz; i++) {
                int u = queue.poll();
                for (int v : g[u]) {
                    degree[v]--;
                    if (degree[v] == 1) {
                        queue.offer(v);
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            ans.add(queue.poll());
        }
        return ans;
    }

}

class Solution_1_BFS {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        /*
        树的直径中点为根节点时，树的高度最小，怎么找中间节点？
        bfs 查找中间节点
         */
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        // 建树
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        // parent 数组含义: 节点 i 的父节点时 parent[i]
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        //  找到距离 0 节点最远的节点 x
        int x = findLongestNode(0, parent, g);
        //  找到距离 x 节点最远的节点 y
        int y = findLongestNode(x, parent, g);
        List<Integer> path = new ArrayList<>();
        // 循环终点
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            ans.add(path.get(m / 2 - 1));
        }
        ans.add(path.get(m / 2));
        return ans;
    }

    public int findLongestNode(int cur, int[] parent, List<Integer>[] g) {
        int n = g.length;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[n];
        queue.offer(cur);
        visit[cur] = true;
        int node = -1;

        while (!queue.isEmpty()) {
            cur = queue.poll();
            node = cur;
            for (int next : g[cur]) {
                if (!visit[next]) {
                    visit[next] = true;
                    parent[next] = cur;
                    queue.offer(next);
                }
            }
        }
        return node;
    }
}

class Solution_1_DFS {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        // 建树
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        // parent 数组含义: 节点 i 的父节点时 parent[i]
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        //  找到距离 0 节点最远的节点 x
        int x = findLongestNode(0, parent, g);
        //  找到距离 x 节点最远的节点 y
        int y = findLongestNode(x, parent, g);

        List<Integer> path = new ArrayList<>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            ans.add(path.get(m / 2 - 1));
        }
        ans.add(path.get(m / 2));
        return ans;
    }

    public int findLongestNode(int root, int[] parent, List<Integer>[] g) {
        int n = g.length;
        // 每个节点距离 root 节点的距离
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[root] = 0;
        dfs(root, dist, parent, g);
        int maxDist = 0;
        int node = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                node = i;
            }
        }
        return node;
    }

    public void dfs(int cur, int[] dist, int[] parent, List<Integer>[] g) {
        for (int next : g[cur]) {
            // 没有访问过
            if (dist[next] < 0) {
                dist[next] = dist[cur] + 1;
                parent[next] = cur;
                dfs(next, dist, parent, g);
            }
        }
    }
}
