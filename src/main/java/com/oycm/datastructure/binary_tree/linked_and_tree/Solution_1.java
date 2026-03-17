package com.oycm.datastructure.binary_tree.linked_and_tree;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 114. <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/">二叉树展开为链表</a>
     * 先序遍历 二叉树 转换成全部指向 right 的链表
     *
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

    static class Solution_1_1 {

        public void flatten(TreeNode root) {
            /*
            分治:
             */
            dfs(root);
        }

        /**
         * <img src = "http://47.101.155.205/image-20260317105459818.png" alt = "dfs 过程说明">
         *
         * @param node
         * @return
         */
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1L1 = new TreeNode(2);
        TreeNode node1R1 = new TreeNode(7, new TreeNode(8), new TreeNode(9));
        root.left = node1L1;
        root.right = node1R1;
        TreeNode node2L1 = new TreeNode(3);
        node1L1.left = node2L1;
        TreeNode node3L1 = new TreeNode(4);
        TreeNode node3R1 = new TreeNode(5);
        node2L1.left = node3L1;
        node2L1.right = node3R1;
        node3R1.left = new TreeNode(6);

        new Solution_1_1().flatten(root);
        System.out.println(root);
    }

}
