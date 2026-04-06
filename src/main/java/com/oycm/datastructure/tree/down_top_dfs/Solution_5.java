package com.oycm.datastructure.tree.down_top_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_5 {

    /**
     * 3593. <a href="https://leetcode.cn/problems/minimum-increments-to-equalize-leaf-paths/description/">使叶子路径成本相等的最小增量</a> 1959
     * 一个无向树，该树以节点 0 为根节点，包含 n 个节点
     *
     * @param n
     * @param edges edges[i] = [u, v] 表示节点 u 和节点 v 之间存在一条边
     * @param cost  每个节点 i 都有一个关联的成本 cost[i]，表示经过该节点的成本，cost[i] [1, 1e9]
     * @return 需要增加成本的节点数的 最小值
     */
    public int minIncrease(int n, int[][] edges, int[] cost) {
        /*
        路径得分 定义为路径上所有节点成本的总和
        你的目标是通过给任意数量的节点 增加 成本（可以增加任意非负值），使得所有从根节点到叶子节点的路径得分 相等
         */
        /*
        先算出叶子节点的最大值, 要想增加成本的节点数的 最小
            如果在父节点增加值能让其下面的叶子节点 成本相同，这肯定是最优解
            如果父节点增加不能相等，则最优解是
        自底部向上返回节点的和，如果子节点的和 不相等，
            则对其叶子节点 n - 1 个进行增加操作，返回这里的最大值，表示这棵树是增加后相等的和
            还需要记录 最大值相同次数
        这里不需要计算路径的最大和
        也可自顶向上，维护根节点到叶子节点的和，记录不等于最大值的数，返回当前的最大值（返回后，相当于该节点是叶子节点）
         */
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        g[0].add(-1);
        dfs(0, -1, g, cost);
        return ans;
    }

    int ans = 0;

    public long dfs(int cur, int fa, List<Integer>[] g, int[] cost) {
        // 叶子节点
        if (g[cur].size() == 1) {
            return cost[cur];
        }
        /*
        可以不使用 list 记录，记录最大值的次数
         */
        long max = 0;
        int cnt = 0;
        List<Long> list = new ArrayList<>();
        for (int next : g[cur]) {
            if (next != fa) {
                long childSum = dfs(next, cur, g, cost);
                if (childSum > max) {
                    cnt = 1;
                    max = childSum;
                } else if (childSum == max) {
                    cnt++;
                }
            }
        }
        // g[cur].size() - 1 是子节点数
        ans += g[cur].size() - 1 - cnt;
        return cost[cur] + max;
    }


}
