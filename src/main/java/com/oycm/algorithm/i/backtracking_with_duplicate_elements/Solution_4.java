package com.oycm.algorithm.i.backtracking_with_duplicate_elements;

import java.util.*;

public class Solution_4 {

    /**
     * 47. <a href="https://leetcode.cn/problems/permutations-ii/description/">全排列 II</a>
     *
     * @param nums 可包含重复数字的数组
     * @return 任意顺序 返回所有不重复的全排列
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        /*
        跳过相同元素的排列结果
         */
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int num : nums) {
            path.add(num);
        }
        permute(0, path, ans);
        return ans;
    }

    private void permute( int start, List<Integer> nums, List<List<Integer>> ans) {
        if (start == nums.size()) {
            ans.add(new ArrayList<>(nums));
        }
        Set<Integer> set = new HashSet<>();
        // 相同元素交换后的排列重复了
        for (int i = start; i < nums.size(); i++) {
            if (!set.add(nums.get(i))) {
                continue;
            }
            swap(nums, i, start);
            permute( start + 1, nums, ans);
            swap(nums, i, start);
        }
    }

    private void swap(List<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    public static void main(String[] args) {
        Solution_4 solution = new Solution_4();
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3}));
    }

}
