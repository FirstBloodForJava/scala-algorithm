package com.oycm.month2026.march;

public class Solution_12 {

    /**
     * 3600. <a href="https://leetcode.cn/problems/maximize-spanning-tree-stability-with-upgrades/description/">升级后最大生成树稳定性</a> 2301
     * <p>
     * 未解决
     *
     * @param n
     * @param edges
     * @param k
     * @return
     */
    public int maxStability(int n, int[][] edges, int k) {
        UnionFind mustUf = new UnionFind(n); // 必选边并查集
        UnionFind allUf = new UnionFind(n); // 全图并查集
        int minS = Integer.MAX_VALUE;
        int maxS = 0;
        for (int[] e : edges) {
            int x = e[0], y = e[1], s = e[2], must = e[3];
            if (must > 0 && !mustUf.merge(x, y)) { // 必选边成环
                return -1;
            }
            allUf.merge(x, y);
            minS = Math.min(minS, s);
            maxS = Math.max(maxS, s);
        }

        if (allUf.cc > 1) { // 图不连通
            return -1;
        }

        int left = minS;
        int right = maxS * 2 + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(mid, n, edges, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(int low, int n, int[][] edges, int k) {
        UnionFind u = new UnionFind(n);
        for (int[] e : edges) {
            int x = e[0], y = e[1], s = e[2], must = e[3];
            if (must > 0 && s < low) { // 必选边的边权太小
                return false;
            }
            if (must > 0 || s >= low) {
                u.merge(x, y);
            }
        }

        for (int[] e : edges) {
            if (k == 0 || u.cc == 1) {
                break;
            }
            int x = e[0], y = e[1], s = e[2], must = e[3];
            if (must == 0 && s < low && s * 2 >= low && u.merge(x, y)) {
                k--;
            }
        }
        return u.cc == 1;
    }

    static class UnionFind {
        private final int[] fa; // 代表元
        public int cc; // 连通块个数

        UnionFind(int n) {
            // 一开始有 n 个集合 {0}, {1}, ..., {n-1}
            // 集合 i 的代表元是自己
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
            cc = n;
        }

        // 返回 x 所在集合的代表元
        // 同时做路径压缩，也就是把 x 所在集合中的所有元素的 fa 都改成代表元
        public int find(int x) {
            // 如果 fa[x] == x，则表示 x 是代表元
            if (fa[x] != x) {
                fa[x] = find(fa[x]); // fa 改成代表元
            }
            return fa[x];
        }

        // 把 from 所在集合合并到 to 所在集合中
        // 返回是否合并成功
        public boolean merge(int from, int to) {
            int x = find(from);
            int y = find(to);
            if (x == y) { // from 和 to 在同一个集合，不做合并
                return false;
            }
            fa[x] = y; // 合并集合。修改后就可以认为 from 和 to 在同一个集合了
            cc--; // 成功合并，连通块个数减一
            return true;
        }
    }

}
