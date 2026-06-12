package com.oycm.datastructure.tree.lca;

public class Solution_1 {

    /**
     * 1483. <a href="https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/description/">树节点的第 K 个祖先</a> 2115
     */
    class TreeAncestor {

        /*
        给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
        树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
        实现 TreeAncestor 类：
            TreeAncestor(int n, int[] parent) 对树和父数组中的节点数初始化对象。
            getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
         */
        int[][] pa;

        public TreeAncestor(int n, int[] parent) {
            /*
            暴力的做法，从节点 i 出发，一步一步往上跳，i -> parent[i] -> parent[parent[i]] -> ...
            跳 k 次到达节点 i 的第 k 个祖先节点，时间复杂度 O(k)。
            能不能预处理 每个节点 的 第几个父节点，例如 k = 13 = 1011，
                如果我能知道当前节点的第 1 个祖先节点；当前祖先节点的 第 4 个祖先节点；当前祖先的第 8 个祖先节点，就能知道 当前节点的第 k 个节点。
                这样只需要 logk 就能知道 节点的第 k 个父节点
            [1, 2^k] 之间的任意数字，可以通过 {2^0, 2^1, ... 2^k } 组合成，可以预处理每个节点 2^i 的祖先节点，这样就能快速到达任意第 k 个节点。
            怎么枚举所有节点的 2^i 祖先节点？
            先枚举 pa[i][0] = parent[i]，i 的 2^0 个节点
            pa[i][1] = pa[ pa[i][0] ][0]；i 的爷爷节点是 父节点的父节点
            pa[i][2] = pa[ pa[i][1] ][1]；i 的 第 4 个祖先节点 是 爷爷节点的爷爷节点
             */
            int m = 32 - Integer.numberOfLeadingZeros(n);
            pa = new int[n][m];
            for (int i = 0; i < n; i++) {
                pa[i][0] = parent[i];
            }
            for (int i = 0; i < m - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int p = pa[x][i];
                    pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
                }
            }

        }

        public int getKthAncestor(int node, int k) {
            /*
            lowbit
             */

            for (; k > 0 && node >= 0; k &= k - 1) {
                // 注意，这里是 lowbit 长度 -1，不是 lowbit 的值
                node = pa[node][Integer.numberOfTrailingZeros(k)];
            }

            return node;
        }
    }

}
