package com.oycm.datastructure.tree.top_down_dfs;

import com.oycm.utils.DataCreateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_5 {

    /**
     * 1377. <a href="https://leetcode.cn/problems/frog-position-after-t-seconds/">T 秒后青蛙的位置</a> 1824
     *
     * @param n      n 个顶点组成的无向树，顶点编号从 1 到 n。
     * @param edges  表示顶点相连的边
     * @param t
     * @param target
     * @return 青蛙在 t 秒后位于目标顶点 target 上的概率
     */
    public double frogPosition(int n, int[][] edges, int t, int target) {
        /*
        青蛙从 顶点 1 开始起跳，起跳规则：
            在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
            青蛙无法跳回已经访问过的顶点。
            如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
            如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
         */
        /*
        自底向上
         */
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        g[1].add(0);
        long prod = dfs(g, target, 1, 0, t);
        return prod != 0 ? 1.0 / prod : 0;
    }

    private long dfs(List<Integer>[] g, int target, int cur, int fa, int t) {
        /*
        t 秒是否能到达 target，t >= 1 -> target 的最短路径, 能到达的情况
            target 是叶子节点
            t == 1 -> target 的最短路径
         */
        if (t == 0) return cur == target ? 1 : 0;
        if (cur == target) return g[cur].size() == 1 ? 1 : 0;
        for (int next : g[cur]) {
            if (next != fa) { // y 不能是父节点
                long prod = dfs(g, target, next, cur, t - 1); // 寻找 target
                if (prod != 0)
                    return prod * (g[cur].size() - 1); // 乘上儿子个数，并直接返回
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_5().frogPosition(84,
                DataCreateUtils.twoDimensionInts("[[1,2],[2,3],[3,4],[4,5],[5,6],[6,7],[7,8],[8,9],[9,10],[10,11],[11,12],[12,13],[13,14],[14,15],[15,16],[1,17],[1,18],[1,19],[1,20],[1,21],[1,22],[1,23],[1,24],[1,25],[1,26],[2,27],[2,28],[2,29],[2,30],[2,31],[2,32],[2,33],[2,34],[2,35],[2,36],[3,37],[3,38],[3,39],[3,40],[3,41],[3,42],[3,43],[3,44],[3,45],[3,46],[4,47],[4,48],[4,49],[4,50],[4,51],[4,52],[5,53],[5,54],[5,55],[5,56],[5,57],[5,58],[6,59],[6,60],[6,61],[6,62],[6,63],[6,64],[7,65],[7,66],[7,67],[7,68],[7,69],[7,70],[8,71],[8,72],[8,73],[8,74],[8,75],[8,76],[9,77],[9,78],[10,79],[11,80],[12,81],[13,82],[14,83],[15,84]]"), 15, 84));
    }
}

class Solution_5_1 {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        /*
        t 秒是否能到达 target，t >= 1 -> target 的最短路径, 能到达的情况
            target 是叶子节点
            t == 1 -> target 的最短路径
         */
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        dfs(1, -1, t, target, g, 1);
        return ans;
    }

    double ans = 0;
    boolean find = false;

    public void dfs(int cur, int fa, int t, int target, List<Integer>[] g, double prob) {
        int size = 0;
        for (Integer next : g[cur]) {
            if (next != fa) {
                size++;
            }
        }
        if (cur == target || find) {
            if (!find && t >= 0) {
                if (size == 0 || t == 0) ans = prob;
            }
            find = true;
            return;
        }
        if (t < 0) {
            return;
        }

        for (Integer next : g[cur]) {
            if (next != fa) {
                dfs(next, cur, t - 1, target, g, prob / size);
            }
        }
    }
}

/*
自顶向下 优化
 */
class Solution_5_2 {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        /*
        统计节点数使用 g[cur].size() - 1), 这里计算根节点时, 需要特判，根节点不需要数量减少
         */
        g[1].add(0);
        dfs(1, 0, t, target, g, 1);
        return ans;
    }

    private double ans;

    public boolean dfs(int cur, int fa, int t, int target, List<Integer>[] g, double prod) {
        // 找到了, 时间恰好为 0, 或恰为叶子节点
        if (cur == target && (t == 0 || g[cur].size() == 1)) {
            ans = 1 / prod;
            return true;
        }
        if (cur == target || t == 0) return false;

        for (int next : g[cur]) {
            if (next != fa && dfs(next, cur, t - 1, target, g, prod * (g[cur].size() - 1))) {
                return true;
            }
        }
        return false;
    }
}
