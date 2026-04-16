package com.oycm.algorithm.i.permutation;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 46. <a href="https://leetcode.cn/problems/permutations/description/">全排列</a>
     *
     * @param nums nums.length [1, 6], nums[i] [-10, 10] 不含重复数字的数组
     * @return 所有可能的全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> enable = new ArrayList<>();
        for (int num : nums) {
            enable.add(num);
        }
        dfs(0, enable, new ArrayList<>(enable), ans, nums.length);
        return ans;
    }

    public void dfs(int i, List<Integer> enable, List<Integer> path, List<List<Integer>> ans, int n) {
        /*
        枚举剩下有哪些数可以选
         */
        if (i == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (Integer x : enable) {
            path.set(i, x);
            List<Integer> temp = new ArrayList<>(enable);
            temp.remove(x);
            dfs(i + 1, temp, path, ans, n);
        }

    }

    public void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans, boolean[] enable) {
        /*
        枚举 path[i] 填 nums 的哪个数
         */
        if (i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (!enable[j]) {
                path.set(i, nums[j]);
                enable[j] = true;
                dfs(i + 1, nums, path, ans, enable);
                // 回溯
                enable[j] = false;
            }
        }
    }

}
