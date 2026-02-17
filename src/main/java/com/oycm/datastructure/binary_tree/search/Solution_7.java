package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

public class Solution_7 {

    /**
     * 98. <a href="https://leetcode.cn/problems/validate-binary-search-tree/description/">验证二叉搜索树</a>
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }
        long x = node.val;
        return left < x && x < right &&
                isValidBST(node.left, left, x) &&
                isValidBST(node.right, x, right);
    }

}
