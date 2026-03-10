package com.oycm.datastructure.binary_tree.update;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 669. <a href="https://leetcode.cn/problems/trim-a-binary-search-tree/description/">修剪二叉搜索树</a>
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return root;
        if (root.val < low) {
            // 当前节点小于, root 节点只能在右子树出现, 对右子树按现有规则修剪
            root = trimBST(root.right, low, high);
        } else if (root.val > high) {
            root = trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        }
        return root;
    }

}
