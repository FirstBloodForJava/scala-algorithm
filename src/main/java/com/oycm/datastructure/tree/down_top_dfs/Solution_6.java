package com.oycm.datastructure.tree.down_top_dfs;

import com.oycm.utils.DataCreateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_6 {

    /**
     * 2872. <a href="https://leetcode.cn/problems/maximum-number-of-k-divisible-components/description/">可以被 K 整除连通块的最大数目</a> 1968
     * <p>
     * 一棵 n 个节点的无向树，节点编号为 0 到 n - 1
     *
     * @param n      [1,3e4]
     * @param edges  edges[i] = [u, v] 表示节点 u 和节点 v 之间存在一条边
     * @param values values[i] 是第 i 个节点的 值
     * @param k      [1, 1e9]
     * @return 返回所有合法分割中，连通块数目的最大值
     */
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        /*
        你可以从树中删除一些边，也可以一条边也不删，得到若干连通块。
        一个 连通块的值 定义为连通块中所有节点值之和。
        如果所有连通块的值都可以被 k 整除，那么我们说这是一个 合法分割 。
         */
        /*
        values 之和可以被 k 整除, 如果一个子树 childS1 % k == 0, 则 (values - childS1) % k == 0 也成立，则这里可以分割一个块
        自底向上不断返回，遇到和 % k == 0，则分割一个块
         */
        if (k == 1) return n;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        g[0].add(-1);
        dfs(0, -1, g, values, k);
        return ans;
    }

    int ans = 1;

    public long dfs(int cur, int fa, List<Integer>[] g, int[] values, int k) {
        if (g[cur].size() == 1) return values[cur];
        long sum = 0;
        for (int next : g[cur]) {
            if (next != fa) {
                long childSum = dfs(next, cur, g, values, k);
                if (childSum % k == 0) {
                    // 切割
                    ans++;
                } else {
                    sum += childSum;
                }
            }
        }
        return sum + values[cur];
    }

    public static void main(String[] args) {
        System.out.println(new Solution_6().maxKDivisibleComponents(5,
                DataCreateUtils.twoDimensionInts("[[0,2],[1,2],[1,3],[2,4]]"),
                new int[]{1, 8, 1, 4, 4},
                6));
    }

}
