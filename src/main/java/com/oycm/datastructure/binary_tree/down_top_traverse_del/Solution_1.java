package com.oycm.datastructure.binary_tree.down_top_traverse_del;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 814. <a href="https://leetcode.cn/problems/binary-tree-pruning/description/">二叉树剪枝</a> 1380
     * root 二叉树值要么是 0, 要么是 1;
     * 移除所有不包含 1 的子树的原二叉树
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        /*
        这里应该有 节点 全部被移除的情况
        怎么删除二叉树的叶子节点?
        怎么删除不包含 1 的叶子节点?
         */
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) {

        }
    }

}
