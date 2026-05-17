package com.oycm.hot100.permutation;

import java.util.ArrayList;
import java.util.List;

public class Solution_56 {

    /**
     * 78. <a href="https://leetcode.cn/problems/subsets/description/">子集</a>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        /*
        给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
        解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
         */
        /*
        二进制枚举
         */
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < (1 << n) - 1; i++) {
            List<Integer> path = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) > 0) {
                    path.add(nums[j]);
                }
            }
            ans.add(path);
        }

        return ans;
    }

}
