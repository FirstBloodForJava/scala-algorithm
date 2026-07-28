package com.leetcode.interview_question.d;

import java.util.*;

public class Solution_1 {

    /**
     * 面试题 04.01. <a href="https://leetcode.cn/problems/route-between-nodes-lcci/description/">节点间通路</a>
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        /*
        节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
        图中可能存在自环和平行边。
            自环：
            平行边：
         */
        Set<Integer>[] g = new Set[n];
        Arrays.setAll(g, l -> new HashSet<>());
        for (int[] row : graph) {
            g[row[0]].add(row[1]);
        }
        boolean[] visited = new boolean[n];

        return dfs(start, target, g, visited);
    }

    public boolean dfs(int cur, int target, Set<Integer>[] g, boolean[] visited) {
        if (cur == target) {
            return true;
        }
        if (visited[cur] || g[cur].isEmpty()) {
            return false;
        }
        visited[cur] = true;

        for (int next : g[cur]) {
            if (dfs(next, target, g, visited)) {
                return true;
            }
        }
        return false;
    }


}
