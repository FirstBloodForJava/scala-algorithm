package com.oycm.algorithm.i.combination;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 77. <a href="https://leetcode.cn/problems/combinations/description/">组合</a>
     *
     * @param n 正整数 [1, 20]
     * @param k [1, n]
     * @return 范围 [1, n] 中所有可能的 k 个数的组合
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(n, k, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * 枚举选 哪个
     *
     * @param i    初始 n
     * @param k
     * @param path
     * @param ans
     */
    public void dfs(int i, int k, List<Integer> path, List<List<Integer>> ans) {
        int d = k - path.size();
        if (d == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j >= d; j--) {
            path.add(j);
            dfs(j - 1, k, path, ans);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 枚举选 哪个 从小到大选
     *
     * @param i    初始 1
     * @param k
     * @param n
     * @param path
     * @param ans
     */
    public void dfs(int i, int k, int n, List<Integer> path, List<List<Integer>> ans) {
        int d = k - path.size();
        if (d == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        /*
        d 还需要选的数 n - j + 1 >= d => j <=
         */
        for (int j = i; j <= n - d + 1; j++) {
            path.add(j);
            dfs(j + 1, k, n, path, ans);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 选/不选 从小到大
     * @param i
     * @param k
     * @param path
     * @param ans
     */
    public void dfsInputPerspective(int i, int k, List<Integer> path, List<List<Integer>> ans) {
        int d = k - path.size();
        if (d == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (i > d) {
            dfs(i - 1, k, path, ans);
        }
        path.add(i);
        dfsInputPerspective(i - 1, k, path, ans);
        path.remove(path.size() - 1);
    }
}
