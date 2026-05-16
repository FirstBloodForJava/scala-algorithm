package com.oycm.hot100.graph;

import java.util.*;

public class Solution_53 {

    /**
     * 207. <a href="https://leetcode.cn/problems/course-schedule/description/">课程表</a>
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*
        你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1。
        在选修某些课程之前需要一些先修课程。先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai，则必须先学习课程 bi。
        判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
         */
        /*
        [0, 1], [1, 2], [2, 0] 这种情况下，先修后修课程关系形成环，无法完成所有课程学习。
         */
        /*
        ‌拓扑排序（Topological Sorting）是一种对有向无环图（DAG）的所有顶点进行线性排序的算法，使得对于图中任意一条有向边(u, v)，顶点 u 都出现在顶点 v 之前‌。
        它常用于解决依赖关系问题，如任务调度、课程安排或编译顺序确定。
        排序结果‌：得到的线性序列称为‌拓扑序列‌。一个DAG可能有多个合法的拓扑序列。
         */
        /*
        bfs 搜索，初始化两个数组：
            一个数组维护节点的度；
            一个数组维护节点的下一个节点集合；
        初始先找出度为 0 的队列，
        如果队列不为空，先访问 u 节点（范围计数器++），对 u 下面的节点 v 的度减少 1，如果度减少为 0，则加入到待遍历的队列
         */
        List<Integer>[] edges = new List[numCourses];
        Arrays.setAll(edges, l -> new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (int[] row : prerequisites) {
            edges[row[1]].add(row[0]);
            indeg[row[0]]++;
        }
        Queue<Integer> q = new LinkedList();
        // 先找出度为 0 的节点集合；
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        int visited = 0;
        while (!q.isEmpty()) {
            visited++;
            int u = q.poll();
            for (Integer v : edges[u]) {
                // 度
                if (--indeg[v] == 0) {
                    q.offer(v);
                }
            }
        }

        return visited == numCourses;
    }

    public boolean canFinish_dfs(int numCourses, int[][] prerequisites) {
        /*
        dfs 三色标记法：
            0-未搜索：节点 x 未被访问；
            1-搜索中：节点 x 正在访问中，尚未结束（搜索中的下一个节点访问到搜索中的节点，则构成了环）；
            2-已完成：节点已全部访问完毕，说明 节点 x 出发未找到环。
        执行过程：
            1. 建有向图：prerequisites[i][1] -> prerequisites[i][0] 存在一条边；
            2. 创建长为 numCourses 的颜色数组 colors，各个节点初始化为 0-未搜索；
            3. 遍历 colors 数组，如果 colors 为 0，调用递归函数 dfs(i)；
            4. dfs(u) 过程：
                首先标记 colors[u] = 1，表示正在搜索中；
                然后遍历 u 的下一个节点 v，
                    如果 colors[v] = 1，则表示找到环，返回 true。
                    如果 colors = 0 （表示未搜索，搜索 v），如果 dfs(v) 返回 true，则返回 true；
                如果没有找到环，那么先标记 colors[u] = 2，然后返回 false。
            5. dfs(i) 返回 true，说明找到了环，返回 false；
            6. 遍历所有后，未找到环，则返回 true
         */
        List<Integer>[] g = new List[numCourses];
        Arrays.setAll(g, l -> new ArrayList<>());
        int[] colors = new int[numCourses];
        for (int[] row : prerequisites) {
            g[row[1]].add(row[0]);
        }
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 0 && dfs(i, g, colors)) {
                return false;
            }
        }

        return true;
    }

    public boolean dfs(int u, List<Integer>[] g, int[] colors) {
        colors[u] = 1;
        for (int v : g[u]) {
            /*
            colors[v] 有三种情况：
                colors[v] = 1，表示出现了环；
                colors[v] = 0，表示没有访问，继续递归 v 获取信息
                colors[v] = 2，表示前面递归没有找到环，后续可以不用递归
             */
            if (colors[v] == 1 || colors[v] == 0 && dfs(v, g, colors)) {
                return true;
            }
        }
        colors[u] = 2;
        return false;
    }

}

class Solution_210 {
    /**
     * 210. <a href="https://leetcode.cn/problems/course-schedule-ii/description/">课程表 II</a>
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        /*
        现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
        给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi。
        返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
         */
        List<Integer>[] g = new List[numCourses];
        Arrays.setAll(g, l -> new ArrayList<>());
        int[] colors = new int[numCourses];
        for (int[] row : prerequisites) {
            g[row[1]].add(row[0]);
        }
        int[] ans = new int[numCourses];
        stack = numCourses - 1;
        boolean isValid = true;
        for (int i = 0; i < colors.length && isValid; i++) {
            if (colors[i] == 0 && dfs(i, g, colors, ans)) {
                isValid = false;
            }
        }
        if (!isValid) {
            return new int[0];
        }

        return ans;
    }

    int stack;

    public boolean dfs(int u, List<Integer>[] g, int[] colors, int[] ans) {
        colors[u] = 1;
        for (int v : g[u]) {
            /*
            colors[v] 有三种情况：
                colors[v] = 1，表示出现了环；
                colors[v] = 0，表示没有访问，继续递归 v 获取信息
                colors[v] = 2，表示前面递归没有找到环，后续可以不用递归
             */
            if (colors[v] == 1 || colors[v] == 0 && dfs(v, g, colors, ans)) {
                return true;
            }
        }
        colors[u] = 2;
        ans[stack--] = u;
        return false;
    }
}