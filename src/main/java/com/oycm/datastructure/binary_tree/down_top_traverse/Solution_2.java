package com.oycm.datastructure.binary_tree.down_top_traverse;


import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 111. <a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/">二叉树的最小深度</a>
     *
     * @param root
     * @return 根节点到最近叶子节点的最长路径上的节点数
     */
    public int minDepth(TreeNode root) {
        /*
        自底向上-题解思路:
            node 是空节点, 没有节点, return 0;
            node 没有右节点, 深度是左子树的深度 + 1
            node 没有左节点, 深度是右子树的深度 + 1
            node 存在左右节点, 左右子树的最小深度 + 1
         */
        if (root == null) {
            return 0;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftDepth = root.left != null ? minDepth2(root.left) : Integer.MAX_VALUE;
        int rightDepth = root.right != null ? minDepth2(root.right) : Integer.MAX_VALUE;

        return Math.min(leftDepth, rightDepth) + 1;
    }

}
