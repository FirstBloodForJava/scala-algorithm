package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_19 {

    /**
     * 1372. <a href="https://leetcode.cn/problems/longest-zigzag-path-in-a-binary-tree/description/">二叉树中的最长交错路径</a> 1713
     *
     * 二叉树中的交错路径: 左右左 ... 右左右能到达的最远距离
     * @param root
     * @return
     */
    public int longestZigZag(TreeNode root) {
        dfs(root.left, true, 1);
        dfs(root.right, false, 1);
        return ans;
    }

    int ans = 0;

    private void dfs(TreeNode node, boolean isLeft, int i) {
        if (node == null) return;
        ans = Math.max(ans, i);
        if (isLeft) {
            if (node.right != null) {
                dfs(node.right, false, i + 1);
            }
            // 以当前节点为起点
            if (node.left != null) {
                dfs(node.left, true, 1);
            }
        } else {
            if (node.left != null) {
                dfs(node.left, true, i + 1);
            }
            if (node.right != null) {
                dfs(node.right, false, 1);
            }
        }
    }

}
