package com.oycm.datastructure.binary_tree.recursion;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 1038. <a href="https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/description/">从二叉搜索树到更大和树</a> 1375
     *
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        dfs(root);
        return root;
    }

    int sum = 0;

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.right);
        sum += node.val;
        node.val = sum;
        dfs(node.left);
    }
}
