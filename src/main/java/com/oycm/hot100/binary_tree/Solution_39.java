package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_39 {

    /**
     * 101. <a href="https://leetcode.cn/problems/symmetric-tree/description/">对称二叉树</a>
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        /*
        root 的左子树是否和右子树对称
            p.left = q.right, p.right = q.left
         */
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        return left.val == right.val && dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
