package com.oycm.week.No495;

public class Solution_4 {

    public int numberOfEdgesAdded(int n, int[][] edges) {
        parent = new int[n];
        parity = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int ans = 0;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            int ru = find(u);
            int rv = find(v);

            if (ru != rv) {
                // 合并 ru -> rv
                parent[ru] = rv;

                // parity[ru] ^ parity[u] ^ parity[v] = w
                parity[ru] = parity[u] ^ parity[v] ^ w;
                ans++;
            } else {
                // 已连通，检查是否形成奇数环
                if ((parity[u] ^ parity[v] ^ w) == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }

    int[] parent;
    int[] parity;

    public int find(int x) {
        if (parent[x] != x) {
            int root = find(parent[x]);
            parity[x] ^= parity[parent[x]];
            parent[x] = root;
        }
        return parent[x];
    }
}
