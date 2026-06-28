package com.oycm.hot100.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_55 {

    /**
     * 46. <a href="https://leetcode.cn/problems/permutations/description/">全排列</a>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        /*
        枚举 path[i] 填 nums 的哪个数
         */
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        dfs(0, nums, Arrays.asList(new Integer[n]), ans, new boolean[n]);
        return ans;
    }

    public void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans, boolean[] enable) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (!enable[j]) {
                // path 需先预填值
                path.set(i, nums[j]);
                enable[j] = true;
                dfs(i + 1, nums, path, ans, enable);
                // 回溯
                enable[j] = false;
            }
        }
    }


}
