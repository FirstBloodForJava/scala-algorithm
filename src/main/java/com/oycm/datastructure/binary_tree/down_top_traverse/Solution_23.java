package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_23 {

    /**
     * LCP 67. <a href="https://leetcode.cn/problems/KnLfVT/description/">装饰树</a>
     *
     * 在 父节点和 子节点直接插入一个 -1 的节点
     * @param root
     * @return
     */
    public static TreeNode expandBinaryTree(TreeNode root) {
        /*
        在 子节点 和 父节点直接插入一个节点
        自底向上插入
         */
        if (root.left != null) {
            root.left = new TreeNode(-1, expandBinaryTree(root.left), null);
        }
        if (root.right != null) {
            root.right = new TreeNode(-1, null, expandBinaryTree(root.right));
        }
        return root;
    }

    public static void dfs(TreeNode child, TreeNode parent, boolean isLeft) {
        if (child == null) return;
        if (isLeft) {
            parent.left = new TreeNode(-1, parent.left, null);
        } else {
            parent.right = new TreeNode(-1, null, parent.right);
        }
        dfs(child.left, child, true);
        dfs(child.right, child, false);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(1, new TreeNode(3), new TreeNode(8)), new TreeNode(7, null, new TreeNode(4)));
        expandBinaryTree(root);
    }

}
