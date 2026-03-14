package com.oycm.datastructure.binary_tree.linked_and_tree;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 114. <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/">二叉树展开为链表</a>
     * 先序遍历 二叉树 转换成全部指向 right 的链表
     * @param root
     */
    public void flatten(TreeNode root) {
        /*
        题解思路: 头插法构建链表
        右子树 -> 左子树 -> 根 遍历树
         */
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = head;
        head = root;
    }

    private TreeNode head;

    static class Solution_1_1{

        public void flatten(TreeNode root) {
            /*
            分治:
             */
            dfs(root);
        }

        public TreeNode dfs(TreeNode node) {
            if (node == null) return null;
            TreeNode leftTail = dfs(node.left);
            TreeNode rightTail = dfs(node.right);
            if (leftTail != null) {
                leftTail.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            return rightTail != null ? rightTail : leftTail != null ? leftTail : node;
        }

    }

}
