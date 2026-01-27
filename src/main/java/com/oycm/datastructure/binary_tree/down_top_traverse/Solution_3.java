package com.oycm.datastructure.binary_tree.down_top_traverse;


import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 965. <a href="https://leetcode.cn/problems/univalued-binary-tree/description/">单值二叉树</a> 1178
     * <p>
     * 单值二叉树: 二叉树每个节点都具有相同的值
     *
     * @param root
     * @return 是否为 单值二叉树
     */
    public boolean isUnivalTree(TreeNode root) {
        /*
        自顶向下会更简单
         */
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }

        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }

    public boolean dfs(TreeNode node, int target) {
        if (node == null) {
            return true;
        }
        boolean leftIsUnival = dfs(node.left, target);
        boolean rightIsUnival = dfs(node.right, target);
        if (!leftIsUnival || !rightIsUnival) {
            return false;
        }
        return node.val == target;
    }

}
