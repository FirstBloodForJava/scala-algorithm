package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 700. <a href="https://leetcode.cn/problems/search-in-a-binary-search-tree/description/">二叉搜索树中的搜索</a>
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }

}
