package com.oycm.hot100.union_find;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_107 {

    /**
     * 399. <a href="https://leetcode.cn/problems/evaluate-division/description/">除法求值</a>
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /*
        给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
        其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
        另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
        返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
        如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

        注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
        注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。
         */
        /*
        带权并查集
        定义 mul[x] 表示 x * mul[x] = x 的代表元的值
        如果 c 和 d 在同一个集合中，那么
            c * mul[c] = d * mul[d] = 代表元的值。怎么理解这里？

         */
        // 把不同字符串映射为不同的数字，方便使用并查集
        Map<String, Integer> variableToId = new HashMap<>();
        for (List<String> equation : equations) {
            for (String s : equation) {
                variableToId.putIfAbsent(s, variableToId.size());
            }
        }

        // 初始化并查集
        UnionFind uf = new UnionFind(variableToId.size());
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            uf.merge(variableToId.get(equation.get(1)), variableToId.get(equation.get(0)), values[i]);
        }

        // 回答询问
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            Integer c = variableToId.get(query.get(0));
            Integer d = variableToId.get(query.get(1));
            if (c != null && d != null && uf.same(c, d)) {
                //    c * mul[c] = d * mul[d] = 代表元的值
                // => c / d = mul[d] / mul[c]
                ans[i] = uf.mul[d] / uf.mul[c];
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }

    class UnionFind {
        private final int[] fa; // 代表元
        public final double[] mul; // x 的值 * mul[x] = x 的代表元的值

        public UnionFind(int n) {
            fa = new int[n];
            // 一开始有 n 个集合 {0}, {1}, ..., {n-1}
            // 集合 i 的代表元是自己，自己 * 1 = 自己
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }

            mul = new double[n];
            Arrays.fill(mul, 1);
        }

        // 返回 x 所在集合的代表元
        // 同时做路径压缩，也就是把 x 所在集合中的所有元素的 fa 都改成代表元
        private int find(int x) {
            if (fa[x] != x) {
                int root = find(fa[x]);
                mul[x] *= mul[fa[x]]; // 更新 x 到其代表元的 mul 值
                fa[x] = root;
            }
            return fa[x];
        }

        // 判断 x 和 y 是否在同一个集合
        public boolean same(int x, int y) {
            // 如果 x 的代表元和 y 的代表元相同，那么 x 和 y 就在同一个集合
            // 这就是代表元的作用：用来快速判断两个元素是否在同一个集合
            return find(x) == find(y);
        }

        // 合并 from 和 to，新增信息 to / from = value
        // 其中 to 和 from 表示未知量，下文的 x 和 y 也表示未知量
        public void merge(int from, int to, double value) {
            int x = find(from);
            int y = find(to);
            if (x == y) { // from 和 to 在同一个集合，不做合并
                return;
            }
            //    x --------- y
            //   /           /
            // from ------- to
            // 已知 x/from = mul[from] 和 y/to = mul[to]，现在合并 from 和 to，新增信息 to/from = value
            // 由于 y/from = (y/x) * (x/from) = (y/to) * (to/from)
            // 所以 y/x = (y/to) * (to/from) / (x/from) = mul[to] * value / mul[from]
            mul[x] = mul[to] * value / mul[from];
            fa[x] = y;
        }
    }

}
