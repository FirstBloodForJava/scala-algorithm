package com.oycm.datastructure.binary_tree.traverse;


import com.oycm.TreeNode;

public class Solution_6 {

    private int ans = 0;

    /**
     * 404. <a href="https://leetcode.cn/problems/sum-of-left-leaves/description/">左叶子之和</a>
     *
     * @param root
     * @return 所有左叶子节点之和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, root);
        return ans;
    }

    public void dfs(TreeNode node, TreeNode prev) {
        if (node == null) return;
        // 当前节点是 左叶子
        if (prev.left == node && node.left == null && node.right == null) {
            ans += node.val;
        }
        dfs(node.left, node);
        dfs(node.right, node);
    }

}
