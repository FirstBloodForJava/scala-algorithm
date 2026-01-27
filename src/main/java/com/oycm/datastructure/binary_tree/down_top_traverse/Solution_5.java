package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_5 {

    /**
     * 101. <a href="https://leetcode.cn/problems/symmetric-tree/description/">对称二叉树</a>
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        /*
        左子树 和 右子树 是否一致
         */
        return isSameTree(root.left, root.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        /*
        自顶向下
         */
        if (p == null || q == null) {
            return p == q;
        }

        return p.val == q.val && isSameTree(p.left, q.right) && isSameTree(p.right,q.left);
    }
}
