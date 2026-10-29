package com.oycm.month2026.october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_29 {

    /**
     * 3841. <a href="https://leetcode.cn/problems/palindromic-path-queries-in-a-tree/description/">查询树上回文路径</a> 2384
     *
     * @param n
     * @param edges
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        /*
        给你一棵包含 n 个节点的无向树，节点编号从 0 到 n - 1。树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 之间存在一条无向边。
        另给你一个长度为 n 且由小写英文字母组成的字符串 s，其中 s[i] 表示分配给节点 i 的字符。
        还给你一个字符串数组 queries，其中每个 queries[i] 为以下形式之一：
            "update ui c"：将节点 ui 处的字符更改为 c。正式地，更新 s[ui] = c。
            "query ui vi"：判断从 ui 到 vi 的 唯一 路径（含两端点）上的字符所组成的字符串，是否可以 重新排列 成一个 回文串。
        返回一个布尔数组 answer，如果第 i 个类型为 "query ui vi" 的查询可以重新排列成 回文串 ，则 answer[i] 为 true，否则为 false。
        回文串 是指正读和反读都相同的字符串。
         */
        char[] t = s.toCharArray();
        LcaBinaryLifting g = new LcaBinaryLifting(edges, t);
        FenwickTree f = new FenwickTree(n); // 注意树状数组是异或运算
        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int x = Integer.parseInt(parts[1]);
            if (parts[0].charAt(0) == 'u') {
                char c = parts[2].charAt(0);
                int val = (1 << (t[x] - 'a')) ^ (1 << (c - 'a')); // 擦除旧的，换上新的
                t[x] = c;
                // 子树 x 全部异或 val，转换成对区间 [tin[x], tout[x]] 的差分更新
                f.update(g.tin[x], val);
                f.update(g.tout[x] + 1, val);
            } else {
                int y = Integer.parseInt(parts[2]);
                int lca = g.getLCA(x, y);
                int res = g.pathXorFromRoot[x] ^ g.pathXorFromRoot[y] ^ f.pre(g.tin[x]) ^ f.pre(g.tin[y]) ^ (1 << (t[lca] - 'a'));
                ans.add((res & (res - 1)) == 0); // 至多一个字母的出现次数是奇数
            }
        }

        return ans;
    }

}

class FenwickTree {
    private final int[] tree;

    public FenwickTree(int n) {
        tree = new int[n + 1]; // 使用下标 1 到 n
    }

    // a[i] ^= val
    // 1 <= i <= n
    // 时间复杂度 O(log n)
    public void update(int i, int val) {
        for (; i < tree.length; i += i & -i) {
            tree[i] ^= val;
        }
    }

    // 求前缀异或和 a[1] ^ ... ^ a[i]
    // 1 <= i <= n
    // 时间复杂度 O(log n)
    public int pre(int i) {
        int res = 0;
        for (; i > 0; i &= i - 1) {
            res ^= tree[i];
        }
        return res;
    }
}

class LcaBinaryLifting {
    private final int[] depth;
    private final int[][] pa;
    private int clock = 0;

    public final int[] tin;
    public final int[] tout;
    public final int[] pathXorFromRoot;

    LcaBinaryLifting(int[][] edges, char[] s) {
        int n = edges.length + 1;
        int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        depth = new int[n];
        pa = new int[m][n];
        tin = new int[n]; // DFS 时间戳
        tout = new int[n];
        pathXorFromRoot = new int[n]; // 从根开始的路径的字母出现次数的奇偶性
        pathXorFromRoot[0] = 1 << (s[0] - 'a');

        dfs(0, -1, g, s);

        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[i][x];
                pa[i + 1][x] = p < 0 ? -1 : pa[i][p];
            }
        }
    }

    private void dfs(int x, int fa, List<Integer>[] g, char[] s) {
        pa[0][x] = fa;
        tin[x] = ++clock;
        for (int y : g[x]) {
            if (y != fa) {
                depth[y] = depth[x] + 1;
                pathXorFromRoot[y] = pathXorFromRoot[x] ^ (1 << (s[y] - 'a'));
                dfs(y, x, g, s);
            }
        }
        tout[x] = clock;
    }

    // 返回 node 的第 k 个祖先节点
    // 如果不存在，返回 -1
    private int getKthAncestor(int node, int k) {
        for (; k > 0 && node >= 0; k &= k - 1) {
            node = pa[Integer.numberOfTrailingZeros(k)][node];
        }
        return node;
    }

    // 返回 x 和 y 的最近公共祖先（节点编号从 0 开始）
    public int getLCA(int x, int y) {
        if (depth[x] > depth[y]) {
            int tmp = y;
            y = x;
            x = tmp;
        }
        // 使 y 和 x 在同一深度
        y = getKthAncestor(y, depth[y] - depth[x]);
        if (y == x) {
            return x;
        }
        for (int i = pa.length - 1; i >= 0; i--) {
            int px = pa[i][x], py = pa[i][y];
            if (px != py) {
                x = px;
                y = py; // 同时往上跳 2^i 步
            }
        }
        return pa[0][x];
    }
}
