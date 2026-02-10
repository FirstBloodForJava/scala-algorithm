package com.oycm.datastructure.binary_tree.diameter;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 687. <a href="https://leetcode.cn/problems/longest-univalue-path/description/">最长同值路径</a>
     *
     * @param root
     * @return 节点值相同的最长路径
     */
    public int longestUnivaluePath(TreeNode root) {
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
        // 先递归再判断
        if (node.left != null && node.left.val != node.val) leftDepth = 0;
        if (node.right != null && node.right.val != node.val) rightDepth = 0;
        ans = Math.max(ans, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
