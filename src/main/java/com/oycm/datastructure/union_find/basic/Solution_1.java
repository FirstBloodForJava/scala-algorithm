package com.oycm.datastructure.union_find.basic;

import com.oycm.datastructure.union_find.BasicUnionFind;

public class Solution_1 {

    /**
     * 684. <a href="https://leetcode.cn/problems/redundant-connection/description/">冗余连接</a>
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        /*
        树可以看成是一个连通且 无环 的 无向 图。
        给定一个图，该图从一棵 n 个节点 (节点值 1～n) 的树中添加一条边后获得。
        添加的边的两个不同顶点编号在 1 到 n 中间，且这条附加的边不属于树中已存在的边。
        图的信息记录于长度为 n 的二维数组 edges，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
        请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的那个。
         */
        /*
        [[1,2], [2,3], [3,4], [1,4], [1,5]]
        初始有 n 个 连通分量，按 [ai, bi] 合并连通分量，如果 [ai, bi] 不在一个连通块，则合并，否则合并失败，当前 边就是构成环的边
        fa = [0, 1, 2, 3, 4, 5]
         */
        int n = edges.length;
        BasicUnionFind buf = new BasicUnionFind(n + 1);

        for (int[] edge : edges) {
            // 在一个块，合并失败
            if (!buf.merge(edge[0], edge[1])) {
                return edge;
            }
        }

        return new int[0];
    }

}
