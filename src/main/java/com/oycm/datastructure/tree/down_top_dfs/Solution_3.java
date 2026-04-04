package com.oycm.datastructure.tree.down_top_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_3 {

    /**
     * 1519. <a href="https://leetcode.cn/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/description/">子树中标签相同的节点数</a> 1809
     *
     * @param n      [1, 10^5]
     * @param edges  长度为 n - 1 的二维整数数组
     * @param labels 编号为 i 的 节点的标签就是 labels[i]
     * @return 返回一个大小为 n 的数组，其中 ans[i] 表示第 i 个节点的子树中与节点 i 标签相同的节点数
     */
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        /*
        每次枚举 i 去遍历的时间复杂度是 O(n^2)
        可以 dfs 过程中, 返回子树的 标签数量情况 cnt 26 数组，ans[i] = cnt[labels[i] + 1;
         */
        int[] ans = new int[n];
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        // 给 0 添加一个父节点, 避免 n = 2 的特殊判断 [0, 1] [1, 0]
        g[0].add(-1);
        dfs(0, -1, g, ans, labels.toCharArray());
        return ans;
    }

    public int[] dfs(int cur, int fa, List<Integer>[] g, int[] ans, char[] cs) {
        // 叶子节点
        int[] cnt = new int[26];
        cnt[cs[cur] - 'a']++;
        if (g[cur].size() == 1) {
            ans[cur] = 1;
            return cnt;
        }
        // 非叶子节点计算
        for (Integer next : g[cur]) {
            if (next != fa) {
                int[] dfs = dfs(next, cur, g, ans, cs);
                for (int i = 0; i < dfs.length; i++) {
                    cnt[i] += dfs[i];
                }
            }
        }

        ans[cur] = cnt[cs[cur] - 'a'];
        return cnt;
    }

}
