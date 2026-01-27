package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_9 {

    /**
     * 226. <a href="https://leetcode.cn/problems/invert-binary-tree/description/">翻转二叉树</a>
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        /*
        对于根节点, 左右子树需要交换
        根节点的左右子树, 也需要翻转内部节点
         */
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }

}
