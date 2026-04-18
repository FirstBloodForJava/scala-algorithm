package com.oycm.algorithm.i.backtracking_with_duplicate_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_1 {

    /**
     * 90. <a href="https://leetcode.cn/problems/subsets-ii/description/">子集 II</a>
     *
     * @param nums 整数数组 nums ，其中可能包含重复元素
     * @return 请你返回该数组所有可能的 子集
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        /*
        题解思路：为了方便跳过相同元素，先对 nums 排序
        选/不选：
            不选 nums[i] 时，后续要跳过所有和 nums[i] 相同元素
        [1, 2, 3, 3, 4] [1, 2, ... , 4] 都选
        如果 选第一个，不选第二 3 [1, 2, 3, 4]
        如果 不选第一个 3, 选第二个 3 [1, 2, 3, 4]

         */
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        int n = nums.length;
        if (i == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 选
        int x = nums[i];
        path.add(x);
        dfs(i + 1, nums, path, ans);
        path.remove(path.size() - 1);

        // 不选
        i++;
        while (i < n && x == nums[i]) {
            i++;
        }
        dfs(i, nums, path, ans);
    }

    public void dfsAnswerPerspective(int i, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        /*
        答案视角，枚举选哪个
         */
        ans.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; j++) {
            // j > i 表示上一个数未选, 如果相等则跳过
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            path.add(nums[j]);
            dfs(j + 1, nums, path, ans);
            path.remove(path.size() - 1);
        }
    }

}
