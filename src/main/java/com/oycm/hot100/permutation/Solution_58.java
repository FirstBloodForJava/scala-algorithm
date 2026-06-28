package com.oycm.hot100.permutation;

import java.util.ArrayList;
import java.util.List;

public class Solution_58 {

    /**
     * 39. <a href="https://leetcode.cn/problems/combination-sum/description/">组合总和</a>
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /*
        给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。
        candidates 中的 同一个 数字可以 无限制重复被选取。
         */
        int n = candidates.length;
        // 完全背包
        boolean[][] f = new boolean[n + 1][target + 1];
        f[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                f[i + 1][j] = f[i][j] || j >= candidates[i] && f[i + 1][j - candidates[i]];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        // 倒序遍历
        dfs(n - 1, target, candidates, new ArrayList<>(), ans, f);
        return ans;
    }

    public void dfs(int i, int reduce, int[] candidates, List<Integer> path, List<List<Integer>> ans, boolean[][] f) {
        if (reduce == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 前面一些数，无法选择和未 reduce，不用判断 i < 0，此时 f[i+1][reduce] 为 false
        if (reduce < 0 || !f[i + 1][reduce]) {
            return;
        }
        dfs(i - 1, reduce, candidates, path, ans, f);
        path.add(candidates[i]);
        dfs(i, reduce - candidates[i], candidates, path, ans, f);
        path.remove(path.size() - 1);
    }

    public void dfs(int i, int[] candidates, int target, List<Integer> path, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < candidates.length && candidates[j] <= target; j++) {
            path.add(candidates[j]);
            dfs(j, candidates, target - candidates[j], path, ans);
            // 回溯 不选 nums[i]，
            path.remove(path.size() - 1);
        }
    }

    public void dfs(int i, int reduce, int[] candidates, List<Integer> path, List<List<Integer>> ans) {
        if (reduce == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (i == candidates.length || reduce < 0) {
            return;
        }
        // 不选
        dfs(i + 1, reduce, candidates, path, ans);
        // 选
        path.add(candidates[i]);
        dfs(i, reduce - candidates[i], candidates, path, ans);
        path.remove(path.size() - 1);
    }


}
