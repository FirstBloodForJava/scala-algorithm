package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_8 {

    /**
     * 110. <a href="https://leetcode.cn/problems/balanced-binary-tree/description/">平衡二叉树</a>
     * <p>
     * 平衡二叉树: 该树所有节点的左右子树的高度相差不超过 1
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        /*

         */
        return dfs(root) != -1;
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }


}
