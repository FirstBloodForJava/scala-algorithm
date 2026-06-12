package com.oycm.datastructure.tree.lca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeAncestor {

    // pa[x][i] 表示 x 节点的第 2^i 个祖先
    int[][] pa;
    // depth[x] 表示 x 节点的高度
    int[] depth;

    public TreeAncestor(int[][] edges) {
        /*
        先根据 edges 建图，节点是 [0, n-1]
         */
        int n = edges.length + 1;
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

        dfs(0, -1, g);

        // pa[x][i] 计算
        for (int i = 0; i < m; i++) {
            for (int x = 0; x < n; x++) {
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

}
