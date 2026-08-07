package com.leetcode.interview_question.h;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    /**
     * 面试题 08.04. <a href="https://leetcode.cn/problems/power-set-lcci/description/">幂集</a>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        /*
        幂集。编写一种方法，返回某集合的所有子集。集合中 不包含重复的元素。
        说明：解集不能包含重复的子集。
         */
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private final List<List<Integer>> ans = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    private int[] nums;


    private void dfs(int i) {
        // 枚举选哪个
        ans.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; j++) {
            path.add(nums[j]);
            dfs(j + 1);
            path.remove(path.size() - 1);
        }
    }

}
