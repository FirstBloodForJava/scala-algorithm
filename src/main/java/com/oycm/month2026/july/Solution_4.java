package com.oycm.month2026.july;

public class Solution_4 {

    /**
     * 2492. 两个城市间路径的最小分数
     * <br>
     * 2492. <a href="https://leetcode.cn/problems/minimum-score-of-a-path-between-two-cities/description/">两个城市间路径的最小分数</a>
     *
     * @param n
     * @param roads
     * @return
     */
    public int minScore(int n, int[][] roads) {
        /*
        给你一个正整数 n ，表示总共有 n 个城市，城市从 1 到 n 编号。
        给你一个二维数组 roads ，其中 roads[i] = [ai, bi, distancei] 表示城市 ai 和 bi 之间有一条 双向 道路，道路距离为 distancei。
        城市构成的图不一定是连通的。
        两个城市之间一条路径的 分数 定义为这条路径中道路的 最小 距离。
        返回城市 1 和城市 n 之间的所有路径的 最小 分数。
         */
        /*
        可以从 1 开始，走也给 dfs 搜索，求路径上的最小边
         */
        /*
        并查集，合并连通块，求和 1 在一个联通块的最小值
         */
        fa = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }
        for (int[] p : roads) {
            merge(p[0], p[1]);
        }
        int ans = Integer.MAX_VALUE;
        int fx1 = find(1);
        for (int[] p : roads) {
            if (find(p[0]) == fx1) {
                ans = Math.min(ans, p[2]);
            }
        }

        return ans;
    }

    int[] fa;

    public int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }

    public void merge(int from, int to) {
        int x = find(from);
        int y = find(to);
        if (x == y) return;
        fa[x] = y;
    }

}
