package com.oycm.datastructure.union_find.basic;

import com.oycm.datastructure.union_find.BasicUnionFind;

import java.util.Arrays;
import java.util.BitSet;

public class Solution_2 {

    /**
     * 3493. <a href="https://leetcode.cn/problems/properties-graph/description/">属性图</a> 1565
     *
     * @param properties
     * @param k
     * @return
     */
    public int numberOfComponents(int[][] properties, int k) {
        /*
        给你一个二维整数数组 properties，其维度为 n x m，以及一个整数 k。
        定义一个函数 intersect(a, b)，它返回 数组 a 和 b 中 共有的不同整数的数量。
        构造一个 无向图，其中每个索引 i 对应 properties[i]。
        如果且仅当 intersect(properties[i], properties[j]) >= k（其中 i 和 j 的范围为 [0, n - 1] 且 i != j），节点 i 和节点 j 之间有一条边。
        返回结果图中 连通分量 的数量。
         */
        /*
        1 <= n == properties.length <= 100
        1 <= m == properties[i].length <= 100
        1 <= properties[i][j] <= 100
        1 <= k <= m
         */
        /*
        首先解决 intersect(a, b) 方法，找出两个数组相同整数数量（去重），如果数量 >= k
        则使用 并查集合并 (i, j) 集合，遍历所有组合，返回最终的连通块数量
         */
        int n = properties.length;
        int m = properties[0].length;
        BitSet[] bs = new BitSet[n];
        Arrays.setAll(bs, l -> new BitSet(m));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bs[i].set(properties[i][j] - 1);
            }
        }

        BasicUnionFind buf = new BasicUnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (intersectionSize((BitSet) bs[i].clone(), bs[j]) >= k) {
                    buf.merge(i, j);
                }
            }
        }

        return buf.cc;
    }

    public int intersectionSize(BitSet a, BitSet b) {
        a.and(b);
        return a.cardinality();
    }

}
