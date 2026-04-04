package com.oycm.datastructure.tree.down_top_dfs;

import com.oycm.utils.DataCreateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * 3249. <a href="https://leetcode.cn/problems/count-the-number-of-good-nodes/description/">统计好节点的数目</a> 1566
     * 一棵 无向 树，树中包含 n 个节点，按从 0 到 n - 1 标记
     *
     * @param edges 长度为 n - 1 的二维整数数组, edges[i] = [a, b] 表示树中节点 a 与节点 b 之间存在一条边
     * @return
     */
    public int countGoodNodes(int[][] edges) {
        /*
        好节点: 一个节点的所有子节点为根的 子树 包含的节点数相同
         */
        /*
        叶子节点是好节点，只有一条边的节点是好节点
         */
        int n = edges.length + 1;
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
        dfs(0, -1, g);
        return ans;
    }

    int ans = 0;

    public int dfs(int cur, int fa, List<Integer>[] g) {
        // 叶子节点
        if (g[cur].size() == 1) {
            ans++;
            return 1;
        }
        // 这里应该定义为 1, 再加上子树的和
        int childSum = 1;
        boolean isGood = true;
        int pre = -1;
        for (Integer next : g[cur]) {
            if (next != fa) {
                // 数量计算有问题
                int child = dfs(next, cur, g);
                if (pre == -1) {
                    pre = child;
                } else if (pre != child) {
                    isGood = false;
                }
                childSum += child;
            }
        }

        if (isGood) ans++;
        return childSum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_2().countGoodNodes(DataCreateUtils.twoDimensionInts("[[1,0],[3,0],[2,3]]")));
    }

}
