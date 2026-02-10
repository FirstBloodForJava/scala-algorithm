package com.oycm.datastructure.binary_tree.diameter;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 543. <a href="https://leetcode.cn/problems/diameter-of-binary-tree/description/">二叉树的直径</a>
     * <p>
     * 二叉树的直径: 树中任意两个节点之间最长路径的长度
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        /*
        直径就是 node 节点 左子树最长高度 + 右子树最长高度
         */
        dfs(root);
        return ans;
    }
    int ans = 0;

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = dfs(node.left);
        int rightDepth = dfs(node.right);
        ans = Math.max(ans, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
