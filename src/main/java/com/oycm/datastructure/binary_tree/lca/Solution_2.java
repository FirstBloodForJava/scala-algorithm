package com.oycm.datastructure.binary_tree.lca;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 236. <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/submissions/">二叉树的最近公共祖先</a>
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
        题解思路
        分类讨论:
            当前节点是空, return 当前节点
            当前节点是 p, return 当前节点
            当前节点是 q, return 当前节点
            其它:
                左右子树都找到, 返回当前节点, 当前节点就是答案;
                只有左子树找到, 返回递归左子树结果;
                只有右子树找到, 返回递归右子树结果;
                左右子树都没找到, 返回空姐点
         */
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

}
