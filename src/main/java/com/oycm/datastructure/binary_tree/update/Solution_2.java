package com.oycm.datastructure.binary_tree.update;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 450. <a href="https://leetcode.cn/problems/delete-node-in-a-bst/description/">删除二叉搜索树中的节点</a>
     *
     * @param root 二叉搜索树
     * @param key 在二叉搜索树中要删除的节点，可能不存在
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        /*
        空的树，直接返回
        如果删除的节点就是 root 节点, 需要找到新的中间节点
        key == root.val 时, 有三种情况
            root.left 节点为空, 上一个节点指向 root.right 即可
            root.right 节点为空, 上一个节点指向 root.left 即可
            root 左右节点都存在, root 节点删除, 上一个节点指向 root.right, root.right 最左边的节点指向 root.left 删除了 root 节点
         */
        if (root == null) return root;
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // 找到 右子树的最左节点
            TreeNode node = root.right;
            while (node.left != null) {
                node = node.left;
            }
            node.left = root.left;
            root = root.right;
        }

        return root;
    }

}
