package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_37 {

    /**
     * 104. <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/">二叉树的最大深度</a>
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        /*
        给定一个二叉树 root ，返回其最大深度。
        二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
         */
        /*
        原问题：求二叉树的最大深度
        子问题：左子树的最大深度和右子树的最大深度 + 1
         */
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
