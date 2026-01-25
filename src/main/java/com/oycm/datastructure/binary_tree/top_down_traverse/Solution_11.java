package com.oycm.datastructure.binary_tree.top_down_traverse;

import com.oycm.TreeNode;

public class Solution_11 {

    /**
     * 623. <a href="https://leetcode.cn/problems/add-one-row-to-tree/description/">在二叉树中增加一行</a>
     * <p>
     * 根节点 root 位于深度 1
     *
     * 在给定的深度 depth 处添加一个值为 val 的节点行:
     *  给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
     * @param root
     * @param val
     * @param depth
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        dfs(root, 1, val, depth);
        return root;
    }

    public void dfs(TreeNode node, int i, int val, int depth) {
        if (node == null) {
            return;
        }
        if (i == depth - 1) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = new TreeNode(val, left, null);
            node.right = new TreeNode(val, null, right);
            return;
        }
        dfs(node.left, i + 1, val, depth);
        dfs(node.right, i + 1, val, depth);
    }

}
