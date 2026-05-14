package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_40 {

    /**
     * 543. <a href="https://leetcode.cn/problems/diameter-of-binary-tree/description/">二叉树的直径</a>
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        /*
        给你一棵二叉树的根节点，返回该树的 直径 。
        二叉树的直径：指树中任意两个节点之间最长路径的长度 。这条路径可能经过也可能不经过根节点 root 。
        两节点之间路径的 长度 由它们之间边数表示。
         */
        /*
        左子树的最大深度 + 右子树最大深度，更新答案；
        返回当前子树的最大深度
         */
        dfs(root);
        return ans;
    }
    int ans = 0;

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        ans = Math.max(left + right, ans);
        return Math.max(left, right) + 1;
    }

}
