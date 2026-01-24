package com.oycm.datastructure.binary_tree.top_down_traverse;


import com.oycm.TreeNode;

public class Solution_3 {

    public boolean ans = false;

    /**
     * 112. <a href="https://leetcode.cn/problems/path-sum/description/">路径总和</a>
     *
     * @param root
     * @param targetSum
     * @return 是否存在 根节点到叶子节点 路径的和等于 targetSum
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }


}