package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_21 {

    /**
     * 572. <a href="https://leetcode.cn/problems/subtree-of-another-tree/description/">另一棵树的子树</a>
     *
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        /*
        题解
         */
        if (root == null) {
            return false;
        }
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) ||  isSubtree(root.right, subRoot);
    }

    // 判断树是否为 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        /*
        自顶向下
         */
        if (p == null || q == null) {
            return p == q;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right,q.right);
    }
}
