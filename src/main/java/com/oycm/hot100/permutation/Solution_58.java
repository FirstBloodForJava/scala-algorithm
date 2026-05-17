package com.oycm.hot100.permutation;

import java.util.ArrayList;
import java.util.Arrays;
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
        /*
        枚举答案：枚举 [i, n-1] 填入 path 中
         */
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, candidates, target, new ArrayList<>(), ans);
        return ans;
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


}
