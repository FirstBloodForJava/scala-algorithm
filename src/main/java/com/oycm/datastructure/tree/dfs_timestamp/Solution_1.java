package com.oycm.datastructure.tree.dfs_timestamp;

import java.util.*;

public class Solution_1 {

    /**
     * 3515. <a href="https://leetcode.cn/problems/shortest-path-in-a-weighted-tree/description/">带权树中的最短路径</a> 2312
     * <p>
     * 树包含 n 个编号从 1 到 n 的节点
     *
     * @param n
     * @param edges   长度为 n - 1, edges[i] = [u, v, w] 表示一条从节点 u 到 v 的无向边，权重为 w
     * @param queries 二维数组，两种格式 [1, u, v, w'] 更新节点 uv 之间的边权为 w'; [2, x] 计算 从根节点 1 到节点 x 的 最短 路径距离
     * @return 返回一个 answer 数组， answer[i] 是对于第 i 个 [2, x] 查询，从节点 1 到 x 的最短路径距离
     */
    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        dfs(1, 0, g, in, out);

        int[] weight = new int[n + 1];
        FenwickTree diff = new FenwickTree(n);

        for (int[] e : edges) {
            update(e[0], e[1], e[2], in, out, weight, diff);
        }

        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                update(q[1], q[2], q[3], in, out, weight, diff);
            } else {
                ans.add(diff.pre(in[q[1]]));
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private int clock = 0;

    private void dfs(int cur, int fa, List<Integer>[] g, int[] in, int[] out) {
        in[cur] = ++clock; // 进来的时间
        for (int next : g[cur]) {
            if (next != fa) {
                dfs(next, cur, g, in, out);
            }
        }
        out[cur] = clock; // 离开的时间
    }

    private void update(int cur, int next, int w, int[] in, int[] out, int[] weight, FenwickTree diff) {
        // 保证 next 是 cur 的儿子
        if (in[cur] > in[next]) {
            next = cur;
        }
        int d = w - weight[next]; // 边权的增量
        weight[next] = w;
        // 把子树 y 中的最短路长度都增加 d（用差分树状数组维护）
        diff.update(in[next], d);
        diff.update(out[next] + 1, -d);
    }

}

class FenwickTree {

    private final int[] tree;

    public FenwickTree(int n) {
        tree = new int[n + 1]; // 使用下标 1 到 n
    }

    // a[i] 增加 val
    // 1 <= i <= n
    public void update(int i, int val) {
        for (; i < tree.length; i += i & -i) {
            tree[i] += val;
        }
    }

    // 求前缀和 a[1] + ... + a[i]
    // 1 <= i <= n
    public int pre(int i) {
        int res = 0;
        for (; i > 0; i &= i - 1) {
            res += tree[i];
        }
        return res;
    }
}


class Solution_1_Bruteforce {

    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        /*
        u, v 边 压缩成一个值，用 map 记录 uv 的边权, 更新操作更新边权
         */
//        List<int[]>
        List<long[]>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, l -> new ArrayList<>());
        Map<Long, Integer> map = new HashMap<>(n, 1);
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            long side = ((long) x << 20) | y;
            g[x].add(new long[]{y, side});
            g[y].add(new long[]{x, side});
            map.put(side, edge[2]);
        }
        int[] dist = new int[n + 1];
        List<Integer> ans = new ArrayList<>();
        boolean needDfs = true;

        for (int[] query : queries) {
            if (query.length == 4) {
                needDfs = true;
                map.put(((long) query[1] << 20) | query[2], query[3]);
                map.put(((long) query[2] << 20) | query[1], query[3]);
            } else if (needDfs) {
                needDfs = false;
                dfs(1, 0, g, map, dist);
                ans.add(dist[query[1]]);
            } else {
                ans.add(dist[query[1]]);
            }
        }
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }

    public void dfs(int cur, int fa, List<long[]>[] g, Map<Long, Integer> map, int[] dist) {
        for (long[] nextRow : g[cur]) {
            if (nextRow[0] != fa) {
                dist[(int) nextRow[0]] = dist[cur] + map.get(nextRow[1]);
                dfs((int) nextRow[0], cur, g, map, dist);
            }
        }
    }
}
