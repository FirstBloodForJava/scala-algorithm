package com.oycm.datastructure.tree.traverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * 1466. <a href="https://leetcode.cn/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/">重新规划路线</a> 1634
     *
     * @param n           n 座城市，从 0 到 n-1 编号
     * @param connections connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线
     * @return 使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数
     */
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] row : connections) {
            int x = row[0], y = row[1];
            g[x].add(new int[]{y, 1});
            g[y].add(new int[]{x, 0});
        }
        return dfs(0, -1, g);
    }

    public int dfs(int x, int parent, List<int[]>[] g) {
        int res = 0;
        for (int[] e : g[x]) {
            if (e[0] != parent) {
                res += e[1] + dfs(e[0], x, g);
            }
        }
        return res;
    }

}
