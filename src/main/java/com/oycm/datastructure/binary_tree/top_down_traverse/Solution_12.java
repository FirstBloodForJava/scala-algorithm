package com.oycm.datastructure.binary_tree.top_down_traverse;

import com.oycm.TreeNode;

public class Solution_12 {

    /**
     * 1372. <a href="https://leetcode.cn/problems/longest-zigzag-path-in-a-binary-tree/description/">二叉树中的最长交错路径</a> 1713
     * <p>
     * 交错路径: 左右左右切换访问
     *
     * @param root
     * @return 定树中最长 交错路径 的长度
     */
    public int longestZigZag(TreeNode root) {
        /*
        在递归过程中记录上一个路径的方向
         */
        dfs(root.left, true, 1);
        dfs(root.right, false, 1);
        return ans;
    }

    int ans = 0;

    public void dfs(TreeNode node, boolean left, int i) {
        if (node == null) {
            return;
        }
        ans = Math.max(ans, i);
        if (left) {
            if (node.right != null) {
                dfs(node.right, false, i + 1);
            }
            // 当前点作为新起始点
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
