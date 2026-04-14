package com.oycm.algorithm.i.combination;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 216. <a href="https://leetcode.cn/problems/combination-sum-iii/description/">组合总和 III</a>
     *
     * @param k [2, 9]
     * @param n [1, 60]
     * @return 所有可能的有效组合的列表
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        /*
        找出所有相加之和为 n 的 k 个数的组合，且满足下列条件:
            只使用数字1到9
            每个数字 最多使用一次
         */
        List<List<Integer>> ans = new ArrayList<>();
        dfs(9, k, n, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * 枚举选哪个，从大到小
     *
     * @param i
     * @param k
     * @param t    目标值
     * @param path
     * @param ans
     */
    public void dfs(int i, int k, int t, List<Integer> path, List<List<Integer>> ans) {
        int d = k - path.size();
        /*
        t < 0 选的数太大了
        t > i + (i - 1) + ... + (i - d + 1) = (i + i - d + 1 ) / 2 * d
         */
        // 注意, 先乘法再除法
        if (t < 0 || t > (2 * i - d + 1) / 2 * d) {
            return;
        }
        if (d == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j >= d; j--) {
            path.add(j);
            dfs(j - 1, k, t - j, path, ans);
            path.remove(path.size() - 1);
        }
    }

}
