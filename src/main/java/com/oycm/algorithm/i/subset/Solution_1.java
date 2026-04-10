package com.oycm.algorithm.i.subset;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 78. <a href="https://leetcode.cn/problems/subsets/description/">子集</a>
     *
     * @param nums nums.length [1, 10]
     * @return 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        /*
        Answer Perspective 答案视角
        枚举第 1 个数选谁
        枚举第 2 个数选谁
        ...
        每个节点都是答案
        回溯三问：
            当前操作？枚举一个下标 j >= i 的数组加入 path
            子问题？从下标 >= i 的数字中构造子集
            下一个子问题？从下标 >= j+1 的数字中构造子集
         */
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        // 选好之后，就是答案
        ans.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; j++) {
            path.add(nums[j]);
            // 下一个子问题
            dfs(j + 1, nums, path, ans);
            // 选完之后还原, 变成不选它
            path.remove(path.size() - 1);
        }
    }

}

class Solution_1_InputPerspective {
    public List<List<Integer>> subsets(int[] nums) {
        /*
        选/不选 思路
        回溯三问：
            当前操作？枚举第 i 个 选/不选
            子问题？从下标 >= i 构造子集
            下一个子问题？从下标 >= i+1 构造子集
         */
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 不选
        dfs(i + 1, nums, path, ans);
        // 选
        path.add(nums[i]);
        dfs(i + 1, nums, path, ans);
        // 选完之后还原
        path.remove(path.size() - 1);
    }
}