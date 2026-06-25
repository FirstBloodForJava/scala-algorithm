package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_49 {

    /**
     * 236. <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/">二叉树的最近公共祖先</a>
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
        最近公共祖先的定义为：对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
        p != q
        p 和 q 均存在于给定的二叉树中。
         */
        /*
        题解思路
        分类讨论:
            当前节点是空, return 当前节点
            当前节点是 p, return 当前节点，不需要再递归，如果 q 是 p 子节点，lca 就是 p，如果不是，递归更没有意义
            当前节点是 q, return 当前节点
            其它:
            查找 p, q 在左右子树的情况
                1.左右子树都找到, 返回当前节点, 当前节点就是答案（左右节点是 p q）;
                2.只有左子树找到, 返回递归左子树结果（说明另外一个未找到的只能在左子树，左子树找到的节点就是最近的节点）;
                3.只有右子树找到, 返回递归右子树结果;
                4.左右子树都没找到, 返回空结点
         */
        // 递归边界
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        // 2,3,4 情况
        return left != null ? left : right;
    }
}
