package com.oycm.algorithm.i.backtracking_with_duplicate_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * 40. <a href="https://leetcode.cn/problems/combination-sum-ii/description/">组合总和 II</a>
     *
     * @param candidates 正整数数组，候选人编号的集合
     * @param target
     * @return 找出 candidates 中所有可以使数字和为 target 的组合。
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        /*
        candidates 中的每个数字在每个组合中只能使用 一次 。
         */
        /*
        先对 candidates 排序，使用枚举选哪个的
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
        // 剪枝
        if (target < 0) {
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            // 不选的相同元素跳过
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            path.add(candidates[j]);
            dfs(j + 1, candidates, target - candidates[j], path, ans);
            path.remove(path.size() - 1);
        }
    }

}
