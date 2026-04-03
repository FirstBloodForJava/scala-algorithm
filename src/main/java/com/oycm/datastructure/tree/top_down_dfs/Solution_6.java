package com.oycm.datastructure.tree.top_down_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_6 {

    /**
     * 3067. <a href="https://leetcode.cn/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/description/">在带权树网络中统计可连接服务器对数目</a> 1909
     * 无根带权树，树中总共有 n 个节点，分别表示 n 个服务器，服务器从 0 到 n - 1 编号
     *
     * @param edges       edges.length = n-1, edges[i] = [a, b, weight], 表示这条边的权重
     * @param signalSpeed
     * @return 返回一个长度为 n 的整数数组 count ，其中 count[i] 表示通过服务器 i 可连接 的服务器对的 数目
     */
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        /*
        如果两台服务器 a 和 b 是通过服务器 c 可连接的，则：
            a < b ，a != c 且 b != c
            从 c 到 a 的距离是可以被 signalSpeed 整除的
            从 c 到 b 的距离是可以被 signalSpeed 整除的
            从 c 到 b 的路径与从 c 到 a 的路径没有任何公共边
         */
        /*
        选的节点 c 至少要有两条边，每条边中点权重和 % signalSpeed == 0 数量，两两组合相等的总和
         */
        int n = edges.length + 1;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int wt = e[2];
            g[x].add(new int[]{y, wt});
            g[y].add(new int[]{x, wt});
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 没有公共边, 至少要有两条边
            if (g[i].size() == 1) {
                continue;
            }
            int sum = 0;
            /*
            m1, m2, m2 = 3, 4, 5
            3 * 4 + (3 + 4) * 5
             */
            for (int[] next : g[i]) {
                int cnt = dfs(next[0], i, next[1], g, signalSpeed);
                ans[i] += cnt * sum;
                sum += cnt;
            }
        }

        return ans;
    }

    private int dfs(int cur, int fa, int sum, List<int[]>[] g, int signalSpeed) {
        int cnt = sum % signalSpeed == 0 ? 1 : 0;
        for (int[] next : g[cur]) {
            if (next[0] != fa) {
                cnt += dfs(next[0], cur, sum + next[1], g, signalSpeed);
            }
        }
        return cnt;
    }

}
