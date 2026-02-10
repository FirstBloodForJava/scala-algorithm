package com.oycm.datastructure.binary_tree.backtracking;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 113. <a href="https://leetcode.cn/problems/path-sum-ii/description/">路径总和 II</a>
     *
     * @param root
     * @param targetSum
     * @return 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, targetSum, path, ans);
        return ans;
    }

    private void dfs(TreeNode node, int targetSum, List<Integer> path, List<List<Integer>> ans) {
        if (node == null) return;
        targetSum -= node.val;
        path.add(node.val);
        if (node.left == null && node.right == null && targetSum == 0) {
            ans.add(new ArrayList<>(path));
        } else {
            dfs(node.left, targetSum, path, ans);
            dfs(node.right, targetSum, path, ans);
        }
        // 回溯
        path.remove(path.size() - 1);
    }

}
