package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_38 {


    /**
     * 226. <a href="https://leetcode.cn/problems/invert-binary-tree/description/">翻转二叉树</a>
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        /*
        给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
         */
        if (root == null) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}
