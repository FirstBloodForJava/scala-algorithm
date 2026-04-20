package com.oycm.algorithm.i.backtracking_with_duplicate_elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_3 {

    /**
     * 491. <a href="https://leetcode.cn/problems/non-decreasing-subsequences/description/">非递减子序列</a>
     *
     * @param nums nums[i] [-100, 100]; nums.length [1, 15]
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        /*
        nums 不是有序，该怎么判断 选的结果是否存在重复？
         */
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() > 1) {
            ans.add(new ArrayList<>(path));
        }
        // 记录前面选过的数字
        Set<Integer> set = new HashSet<>();
        for (int j = i; j < nums.length; j++) {

            if (!path.isEmpty() && nums[j] < path.get(path.size() - 1) || set.contains(nums[j])) {
                continue;
            }
            set.add(nums[j]);
            path.add(nums[j]);
            dfs(j + 1, nums, path, ans);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution_3 solution = new Solution_3();
        System.out.println(solution.findSubsequences(new int[]{4, 6, 7, 1, 7}));

    }

}
