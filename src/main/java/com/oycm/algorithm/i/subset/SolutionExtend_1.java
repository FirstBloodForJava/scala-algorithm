package com.oycm.algorithm.i.subset;

import java.util.ArrayList;
import java.util.List;

public class SolutionExtend_1 {

    /**
     * 39. <a href="https://leetcode.cn/problems/combination-sum/description/">组合总和</a>
     *
     * @param candidates 无重复元素 的整数数组
     * @param target     目标整数
     * @return 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /*
        candidates 中的 同一个 数字可以 无限制重复被选取。
            candidates = [2,3,6,7]; target = 7
            ans [2, 2, 3], [7]

         */
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, target, candidates, ans, path);
        return ans;
    }

    public void dfs(int i, int left, int[] candidates, List<List<Integer>> ans, List<Integer> path) {
        if (left == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (i == candidates.length || left < 0) {
            return;
        }
        // 不选
        dfs(i + 1, left, candidates, ans, path);

        // 选
        path.add(candidates[i]);
        dfs(i, left - candidates[i], candidates, ans, path);
        // 回溯
        path.remove(path.size() - 1);
    }
}
